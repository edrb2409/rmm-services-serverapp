package com.example.rmmservices.service.device;

import com.example.rmmservices.exception.DeviceNotFoundException;
import com.example.rmmservices.exception.DeviceTypeNotFoundException;
import com.example.rmmservices.model.Device;
import com.example.rmmservices.model.DeviceType;
import com.example.rmmservices.repository.DeviceRepository;
import com.example.rmmservices.repository.DeviceTypeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DefaultDeviceCommandService implements DeviceCommandService {

    private final DeviceRepository deviceRepository;

    private final DeviceTypeRepository deviceTypeRepository;

    public DefaultDeviceCommandService(DeviceRepository deviceRepository,
                                       DeviceTypeRepository deviceTypeRepository) {
        this.deviceRepository = deviceRepository;
        this.deviceTypeRepository = deviceTypeRepository;
    }

    @Override
    public Device save(Device device) {
        log.info("Saving device: {}", device);

        DeviceType deviceType = deviceTypeRepository.findByName(device.getDeviceType().getName())
                .orElseThrow(DeviceTypeNotFoundException::new);

        device.setDeviceType(deviceType);

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
    public Device delete(Long id) {
        log.info("Deleting device: {}", id);

        Device device = deviceRepository.findById(id)
                .orElseThrow(DeviceNotFoundException::new);

        deviceRepository.deleteById(id);

        return device;
    }
}
