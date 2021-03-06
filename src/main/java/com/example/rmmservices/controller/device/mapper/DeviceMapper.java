package com.example.rmmservices.controller.device.mapper;

import com.example.rmmservices.model.Customer;
import com.example.rmmservices.model.Device;
import com.example.rmmservices.model.DeviceType;
import com.example.rmmservices.model.dto.DeviceDTO;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public final class DeviceMapper {

    private DeviceMapper() { }

    public static Device toDevice(Long customerId, DeviceDTO deviceDTO) {
        return Device.builder()
                .customer(Customer.builder().id(customerId).build())
                .systemName(deviceDTO.getSystemName())
                .deviceType(DeviceType.builder().name(deviceDTO.getDeviceType()).build())
                .build();
    }

    public static DeviceDTO toDeviceDTO(Device device) {
        return DeviceDTO.builder()
                .id(device.getId())
                .deviceType(device.getDeviceType().getName())
                .systemName(device.getSystemName())
                .build();
    }

    public static List<DeviceDTO> toDeviceDTO(List<Device> devices) {
        return devices.stream()
                .map(DeviceMapper::toDeviceDTO)
                .collect(Collectors.toList());
    }
}
