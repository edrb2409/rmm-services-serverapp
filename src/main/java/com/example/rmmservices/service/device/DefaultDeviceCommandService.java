package com.example.rmmservices.service.device;

import com.example.rmmservices.exception.CustomerNotFoundException;
import com.example.rmmservices.exception.DeviceNotFoundException;
import com.example.rmmservices.exception.DeviceTypeNotFoundException;
import com.example.rmmservices.model.Customer;
import com.example.rmmservices.model.Device;
import com.example.rmmservices.model.DeviceType;
import com.example.rmmservices.repository.CustomerRepository;
import com.example.rmmservices.repository.DeviceRepository;
import com.example.rmmservices.repository.DeviceTypeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DefaultDeviceCommandService implements DeviceCommandService {

    private final DeviceRepository deviceRepository;

    private final DeviceTypeRepository deviceTypeRepository;

    private final CustomerRepository customerRepository;

    public DefaultDeviceCommandService(DeviceRepository deviceRepository,
                                       DeviceTypeRepository deviceTypeRepository,
                                       CustomerRepository customerRepository) {
        this.deviceRepository = deviceRepository;
        this.deviceTypeRepository = deviceTypeRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public Device save(Device device) {
        log.info("Saving device: {}", device);

        Customer customer = customerRepository.findById(device.getCustomer().getId())
                .orElseThrow(CustomerNotFoundException::new);

        DeviceType deviceType = deviceTypeRepository.findByName(device.getDeviceType().getName())
                .orElseThrow(DeviceTypeNotFoundException::new);


        device.setDeviceType(deviceType);
        device.setCustomer(customer);

        return deviceRepository.save(device);
    }

    @Override
    public Device update(Long id, Device device) {
        log.info("Updating device: {}", device);

        deviceRepository.findById(id)
                .orElseThrow(DeviceNotFoundException::new);

        device.setId(id);

        return save(device);
    }

    @Override
    public Device delete(Long customerId, Long deviceId) {
        log.info("Deleting device: {}", deviceId);

        customerRepository.findById(customerId)
                .orElseThrow(CustomerNotFoundException::new);

        Device device = deviceRepository.findById(deviceId)
                .orElseThrow(DeviceNotFoundException::new);

        deviceRepository.deleteById(deviceId);

        return device;
    }
}
