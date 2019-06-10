package com.example.rmmservices.service.billing;

import com.example.rmmservices.model.*;
import com.example.rmmservices.repository.CustomerServiceRepository;
import com.example.rmmservices.repository.DeviceRepository;
import com.example.rmmservices.repository.ServiceCostPerDeviceTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BillingQueryServiceTest {

    private BillingQueryService billingQueryService;

    @Mock DeviceRepository deviceRepository;

    @Mock CustomerServiceRepository customerServiceRepository;

    @Mock ServiceCostPerDeviceTypeRepository serviceCostPerDeviceTypeRepository;

    @BeforeEach void init_() {
        billingQueryService = new DefaultBillingQueryService(deviceRepository,
                customerServiceRepository, serviceCostPerDeviceTypeRepository);
    }

    @Test void shouldReturnZeroIfCustomerDoesNotHaveDevices() {
        when(deviceRepository.findAllByCustomer_Id(1L)).thenReturn(new ArrayList<>());

        assertEquals(BigDecimal.ZERO, billingQueryService.monthlyCost(1L));
    }

    @Test void shouldReturnZeroIfCustomerDoesNotHaveServices() {
        when(deviceRepository.findAllByCustomer_Id(1L)).thenReturn(devices());
        when(customerServiceRepository.findByCustomer_Id(1L)).thenReturn(new ArrayList<>());

        assertEquals(BigDecimal.ZERO, billingQueryService.monthlyCost(1L));
    }

    @Test void shouldReturn4WhenHas2PSAService() {
        List<CustomerService> customerServices = List.of(customerService(psaService()));
        List<ServiceCostPerDeviceType> servicesCost = List.of(serviceCostPerDeviceType(psaService()));

        when(deviceRepository.findAllByCustomer_Id(1L)).thenReturn(devices());
        when(customerServiceRepository.findByCustomer_Id(1L)).thenReturn(customerServices);
        when(serviceCostPerDeviceTypeRepository.findAll()).thenReturn(servicesCost);

        assertEquals(new BigDecimal(4), billingQueryService.monthlyCost(1L));
    }

    @Test void shouldReturn12ForAntivirusCostWithMicrosoftAndMac() {
        List<CustomerService> customerServices = List.of(customerService(antivirusService()));
        List<ServiceCostPerDeviceType> servicesCost = serviceCostAntivirus();

        when(deviceRepository.findAllByCustomer_Id(1L)).thenReturn(devices());
        when(customerServiceRepository.findByCustomer_Id(1L)).thenReturn(customerServices);
        when(serviceCostPerDeviceTypeRepository.findAll()).thenReturn(servicesCost);

        assertEquals(new BigDecimal(12), billingQueryService.monthlyCost(1L));
    }

    @Test void shouldReturn16ForAntivirusAndPSAService() {
        List<CustomerService> customerServices = List.of(customerService(antivirusService()), customerService(psaService()));
        List<ServiceCostPerDeviceType> servicesCost = new ArrayList<>(serviceCostAntivirus());
        servicesCost.add(serviceCostPerDeviceType(psaService()));

        when(deviceRepository.findAllByCustomer_Id(1L)).thenReturn(devices());
        when(customerServiceRepository.findByCustomer_Id(1L)).thenReturn(customerServices);
        when(serviceCostPerDeviceTypeRepository.findAll()).thenReturn(servicesCost);

        assertEquals(new BigDecimal(16), billingQueryService.monthlyCost(1L));
    }

    private Customer systemCustomer() {
        return Customer.builder()
                .id(1L)
                .name("System")
                .build();
    }

    private Service psaService() {
        return Service.builder()
                .id(1L)
                .name("PSA")
                .build();
    }

    private Service antivirusService() {
        return Service.builder()
                .id(2L)
                .name("Antivirus")
                .build();
    }

    private List<ServiceCostPerDeviceType> serviceCostAntivirus() {
        return List.of(ServiceCostPerDeviceType.builder()
                    .id(1L)
                    .service(antivirusService())
                    .deviceType(microsoftServer())
                    .cost(new BigDecimal(5))
                    .build(),
                ServiceCostPerDeviceType.builder()
                    .id(1L)
                    .service(antivirusService())
                    .deviceType(mac())
                    .cost(new BigDecimal(7))
                    .build());
    }

    private ServiceCostPerDeviceType serviceCostPerDeviceType(Service service) {
        return ServiceCostPerDeviceType.builder()
                .id(1L)
                .service(service)
                .cost(new BigDecimal(2))
                .build();
    }

    private CustomerService customerService(Service service) {
        return CustomerService.builder()
                .service(service)
                .customer(systemCustomer())
                .build();
    }

    private List<Device> devices() {
        return List.of(
                Device.builder().id(1L).deviceType(mac()).customer(systemCustomer()).systemName("my-Mac").build(),
                Device.builder().id(2L).deviceType(microsoftServer()).customer(systemCustomer()).systemName("my-Server").build()
        );
    }

    private DeviceType mac() {
        return DeviceType.builder().id(3l).name("Mac").build();
    }

    private DeviceType microsoftServer() {
        return DeviceType.builder().id(2l).name("Microsoft Server").build();
    }
}
