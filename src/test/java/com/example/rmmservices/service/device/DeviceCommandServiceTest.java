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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DeviceCommandServiceTest {

    private DeviceCommandService deviceCommandService;

    @Mock DeviceRepository deviceRepository;

    @Mock DeviceTypeRepository deviceTypeRepository;

    @Mock CustomerRepository customerRepository;

    @BeforeEach void init_() {
        deviceCommandService = new DefaultDeviceCommandService(deviceRepository,
                deviceTypeRepository, customerRepository);
    }

    @Test void shouldSaveADevice() {
        Device device = Device.builder()
                .customer(Customer.builder().id(1L).build())
                .systemName("Daniel-PC")
                .deviceType(DeviceType.builder().name("Mac").build())
                .build();

        when(customerRepository.findById(1L)).thenReturn(Optional.of(systemCustomer()));

        when(deviceTypeRepository.findByName("Mac"))
                .thenReturn(Optional.of(DeviceType.builder().id(1L).name("Mac").build()));

        when(deviceRepository.save(device)).thenReturn(expectedDevice());

        assertEquals(1L, deviceCommandService.save(device).getId().longValue());
    }

    @Test void shouldRaiseAnExceptionWhenADeviceTypeWasNotFoundOnCreate() {
        Device device = Device.builder()
                .customer(Customer.builder().id(1L).build())
                .systemName("Daniel-PC")
                .deviceType(DeviceType.builder().name("Mac").build())
                .build();

        when(customerRepository.findById(1L))
                .thenReturn(Optional.of(systemCustomer()));

        when(deviceTypeRepository.findByName("Mac"))
                .thenReturn(Optional.empty());

        assertThrows(DeviceTypeNotFoundException.class,
                () -> deviceCommandService.save(device));
    }

    @Test void shouldRaiseAnExceptionWhenCustomerIsNotFoundOnCreate() {
        Device device = Device.builder()
                .customer(Customer.builder().id(1L).build())
                .systemName("Daniel-PC")
                .deviceType(DeviceType.builder().name("Mac").build())
                .build();

        when(customerRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(CustomerNotFoundException.class,
                () -> deviceCommandService.save(device));
    }

    @Test void shouldUpdateADevice() {
        Device device = Device.builder()
                .customer(Customer.builder().id(1L).build())
                .systemName("Daniel-PC-Changed")
                .deviceType(DeviceType.builder().name("Mac").build())
                .build();

        when(deviceRepository.findById(1L)).thenReturn(Optional.of(expectedDevice()));

        when(customerRepository.findById(1L)).thenReturn(Optional.of(systemCustomer()));

        when(deviceTypeRepository.findByName("Mac"))
                .thenReturn(Optional.of(DeviceType.builder().id(1L).name("Mac").build()));

        when(deviceRepository.save(device)).thenReturn(expectedDevice("Daniel-PC-Changed"));

        assertEquals("Daniel-PC-Changed", deviceCommandService.update(1L, device).getSystemName());
    }

    @Test void shouldRaiseAnExceptionWhenADeviceTypeWasNotFoundOnUpdate() {
        Device device = Device.builder()
                .customer(Customer.builder().id(1L).build())
                .systemName("Daniel-PC")
                .deviceType(DeviceType.builder().name("Mac").build())
                .build();

        when(customerRepository.findById(1L))
                .thenReturn(Optional.of(systemCustomer()));

        when(deviceRepository.findById(1L))
                .thenReturn(Optional.of(expectedDevice()));

        when(deviceTypeRepository.findByName("Mac"))
                .thenReturn(Optional.empty());

        assertThrows(DeviceTypeNotFoundException.class,
                () -> deviceCommandService.update(1L, device));
    }

    @Test void shouldRaiseAnExceptionWhenCustomerIsNotFoundOnUpdate() {
        Device device = Device.builder()
                .customer(Customer.builder().id(1L).build())
                .systemName("Daniel-PC")
                .deviceType(DeviceType.builder().name("Mac").build())
                .build();

        when(deviceRepository.findById(1L)).thenReturn(Optional.of(expectedDevice()));

        when(customerRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(CustomerNotFoundException.class,
                () -> deviceCommandService.update(1L, device));
    }

    @Test void shouldRaiseAnExceptionWhenDeviceIsNotFoundOnUpdate() {
        Device device = Device.builder()
                .customer(Customer.builder().id(1L).build())
                .systemName("Daniel-PC")
                .deviceType(DeviceType.builder().name("Mac").build())
                .build();

        when(deviceRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(DeviceNotFoundException.class,
                () -> deviceCommandService.update(1L, device));
    }

    @Test void shouldDeleteADevice() {
        when(customerRepository.findById(1L)).thenReturn(Optional.of(systemCustomer()));
        when(deviceRepository.findById(1L)).thenReturn(Optional.of(expectedDevice()));

        assertEquals(1L, deviceCommandService.delete(1L, 1L).getId().longValue());
    }

    @Test void shouldRaiseAnExceptionWhenDeviceIsNotFoundOnDelete() {
        when(customerRepository.findById(1L)).thenReturn(Optional.of(systemCustomer()));
        when(deviceRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(DeviceNotFoundException.class,
                () -> deviceCommandService.delete(1L, 1L));
    }

    @Test void shouldRaiseAnExceptionWhenCustomerIsNotFoundOnDelete() {
        when(customerRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(CustomerNotFoundException.class,
                () -> deviceCommandService.delete(1L, 1L));
    }

    private Device expectedDevice() {
        return expectedDevice("Daniel-PC");
    }

    private Device expectedDevice(String name) {
        return Device.builder()
                .id(1L)
                .customer(Customer.builder().id(1L).build())
                .systemName(name)
                .deviceType(DeviceType.builder().id(1L).name("Mac").build())
                .build();
    }

    private Customer systemCustomer() {
        return Customer.builder()
                .id(1L)
                .name("system")
                .build();
    }

}
