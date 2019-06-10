package com.example.rmmservices.service.billing;

import com.example.rmmservices.model.CustomerService;
import com.example.rmmservices.model.Device;
import com.example.rmmservices.model.ServiceCostPerDeviceType;
import com.example.rmmservices.repository.CustomerServiceRepository;
import com.example.rmmservices.repository.DeviceRepository;
import com.example.rmmservices.repository.ServiceCostPerDeviceTypeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@Service
public class DefaultBillingQueryService implements BillingQueryService {

    private final DeviceRepository deviceRepository;

    private final CustomerServiceRepository customerServiceRepository;

    private final ServiceCostPerDeviceTypeRepository serviceCostPerDeviceTypeRepository;

    public DefaultBillingQueryService(DeviceRepository deviceRepository,
                                      CustomerServiceRepository customerServiceRepository,
                                      ServiceCostPerDeviceTypeRepository serviceCostPerDeviceTypeRepository) {
        this.deviceRepository = deviceRepository;
        this.customerServiceRepository = customerServiceRepository;
        this.serviceCostPerDeviceTypeRepository = serviceCostPerDeviceTypeRepository;
    }

    @Override
    public BigDecimal monthlyCost(Long customerId) {
        List<Device> customerDevices = deviceRepository.findAllByCustomer_Id(customerId);

        if(customerDevices.isEmpty()) return BigDecimal.ZERO;

        List<CustomerService> customerServices = customerServiceRepository.findByCustomer_Id(customerId);

        if(customerServices.isEmpty()) return BigDecimal.ZERO;

        List<ServiceCostPerDeviceType> servicesCost = serviceCostPerDeviceTypeRepository.findAll();

        return servicesCost.stream()
                .filter(it -> customerServices.stream()
                        .anyMatch(at -> it.getService().getId().equals(at.getService().getId())))
                .map(sc -> {
                    if(sc.getDeviceType() == null) {
                        return sc.getCost().multiply(new BigDecimal(customerDevices.size()));
                    } else {
                        Long deviceForCost =  customerDevices.stream()
                                .filter(device -> device.getDeviceType().getId().equals(sc.getDeviceType().getId()))
                                .count();

                        return sc.getCost().multiply(new BigDecimal(deviceForCost));
                    }
                })
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }
}
