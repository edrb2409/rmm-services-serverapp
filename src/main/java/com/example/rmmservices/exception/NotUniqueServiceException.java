package com.example.rmmservices.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Service is not unique")
public class NotUniqueServiceException extends RuntimeException {
}
