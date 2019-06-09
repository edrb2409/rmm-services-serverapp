package com.example.rmmservices.repository;

import com.example.rmmservices.model.Device;
import com.example.rmmservices.model.DeviceType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ExtendWith(SpringExtension.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DeviceRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired private DeviceTypeRepository deviceTypeRepository;

    @Test void shouldStoreADevice() {
        DeviceType macType = deviceTypeRepository.findByName("Mac")
                .orElse(DeviceType.builder().name("Mac").build());

        Device device = Device.builder()
                .customerId(1L)
                .systemName("Daniel-PC")
                .deviceType(macType)
                .build();

        deviceRepository.save(device);

        assertAll(() -> assertNotNull(device.getId()),
                () -> assertEquals(1, deviceRepository.findAll().size()));
    }

}
