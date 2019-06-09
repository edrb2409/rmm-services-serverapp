package com.example.rmmservices.controller.device;

import com.example.rmmservices.controller.device.mapper.DeviceMapper;
import com.example.rmmservices.model.dto.DeviceDTO;
import com.example.rmmservices.service.device.DeviceCommandService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/devices")
public class DeviceCommandController {

    private final DeviceCommandService service;

    public DeviceCommandController(DeviceCommandService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<DeviceDTO> createDevice(@Valid @RequestBody DeviceDTO deviceDTO) {
        log.info("Device: {}", deviceDTO);

        return ResponseEntity.ok(DeviceMapper.toDeviceDTO(
                service.save(DeviceMapper.toDevice(deviceDTO))));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DeviceDTO> updateDevice(@PathVariable Long id, @Valid @RequestBody DeviceDTO deviceDTO) {
        log.info("Update device: {}, {}", id, deviceDTO );

        return ResponseEntity.ok(DeviceMapper.toDeviceDTO(service.update(id, DeviceMapper.toDevice(deviceDTO))));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DeviceDTO> deleteDevice(@PathVariable Long id) {
        log.info("Delete device {}", id);

        return ResponseEntity.ok(DeviceMapper.toDeviceDTO(service.delete(id)));
    }
}
