package com.example.rmmservices.controller.device;

import com.example.rmmservices.controller.device.mapper.DeviceMapper;
import com.example.rmmservices.model.dto.DeviceDTO;
import com.example.rmmservices.service.device.DeviceQueryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("customers/{customerId}/devices")
public class DeviceQueryController {

    private final DeviceQueryService service;

    public DeviceQueryController(DeviceQueryService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<DeviceDTO>> findAll(@PathVariable Long customerId) {
        log.info("Find all devices for customer: {}", customerId);

        return ResponseEntity.ok(DeviceMapper.toDeviceDTO(service.findAll(customerId)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DeviceDTO> findById(@PathVariable Long customerId, @PathVariable Long id) {
        log.info("Getting device {}", id);

        return ResponseEntity.ok(DeviceMapper.toDeviceDTO(service.findBy(customerId, id)));
    }
}
