package com.example.rmmservices.service.device;

import com.example.rmmservices.exception.DeviceNotFoundException;
import com.example.rmmservices.model.Device;
import com.example.rmmservices.repository.DeviceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class DefaultDeviceQueryService implements DeviceQueryService {

    private final DeviceRepository deviceRepository;

    public DefaultDeviceQueryService(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    @Override
    public Device findBy(Long customerId, Long deviceId) {
        log.info("Find by {} and customer {}", deviceId, customerId);

        return deviceRepository.findByIdAndCustomer_Id(deviceId, customerId)
                .orElseThrow(DeviceNotFoundException::new);
    }

    @Override
    public List<Device> findAll(Long customerId) {
        log.info("Getting all devices for {}", customerId);
        return deviceRepository.findAllByCustomer_Id(customerId);
    }
}
