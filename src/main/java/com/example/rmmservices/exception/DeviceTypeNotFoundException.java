package com.example.rmmservices.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Device type not found")
public class DeviceTypeNotFoundException extends RuntimeException {
}
