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
    public Device findBy(Long id) {
        log.info("Find by {}", id);

        return deviceRepository.findById(id)
                .orElseThrow(DeviceNotFoundException::new);
    }

    @Override
    public List<Device> findAll() {
        log.info("Getting all devices");
        return deviceRepository.findAll();
    }
}
