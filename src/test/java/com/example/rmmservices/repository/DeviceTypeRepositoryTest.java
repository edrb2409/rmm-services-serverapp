package com.example.rmmservices.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ExtendWith(SpringExtension.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DeviceTypeRepositoryTest {

    @Autowired
    private DeviceTypeRepository deviceTypeRepository;

    @Test void shouldReturnADeviceTypeWhenGivenAName() {
        assertTrue(deviceTypeRepository.findByName("Mac").isPresent());
    }

    @Test void shouldNotReturnADeviceIfItIsNotFound() {
        assertFalse(deviceTypeRepository.findByName("Linux").isPresent());
    }

}
