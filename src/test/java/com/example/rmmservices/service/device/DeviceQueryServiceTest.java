package com.example.rmmservices.service.device;

import com.example.rmmservices.exception.DeviceNotFoundException;
import com.example.rmmservices.model.Customer;
import com.example.rmmservices.model.Device;
import com.example.rmmservices.model.DeviceType;
import com.example.rmmservices.repository.DeviceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DeviceQueryServiceTest {

    private DeviceQueryService deviceQueryService;

    @Mock DeviceRepository deviceRepository;

    @BeforeEach void init_() {
        deviceQueryService = new DefaultDeviceQueryService(deviceRepository);
    }

    @Test void shouldGetADeviceById() {
        when(deviceRepository.findByIdAndCustomer_Id(1L, 1L)).thenReturn(Optional.of(aDevice()));

        assertEquals(1L, deviceQueryService.findBy(1L, 1L).getId().longValue());
    }

    @Test void shouldRaiseAnExceptionWhenDeviceIsNotFound() {
        when(deviceRepository.findByIdAndCustomer_Id(1L, 1L)).thenReturn(Optional.empty());

        assertThrows(DeviceNotFoundException.class,
                () -> deviceQueryService.findBy(1L, 1L));
    }

    @Test void shouldGetDevices() {
        when(deviceRepository.findAllByCustomer_Id(1L)).thenReturn(devices());

        assertEquals(2, deviceQueryService.findAll(1L).size());
    }

    @Test void shouldGetAnEmptyListIfDeviceTableIsEmpty() {
        when(deviceRepository.findAllByCustomer_Id(1L)).thenReturn(new ArrayList<>());

        assertTrue(deviceQueryService.findAll(1L).isEmpty());
    }

    private Device aDevice() {
        return deviceFactory("Daniel-PC");
    }

    private List<Device> devices() {
        return List.of(
                deviceFactory("PC-001"),
                deviceFactory("PC-002"));
    }


    private Device deviceFactory(String name) {
        return Device.builder()
                .id(1L)
                .customer(Customer.builder().id(1L).build())
                .systemName(name)
                .deviceType(DeviceType.builder().id(1L).name("Mac").build())
                .build();
    }

}
