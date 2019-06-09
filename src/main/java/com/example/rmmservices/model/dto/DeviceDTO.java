package com.example.rmmservices.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeviceDTO {

    private Long id;

    @NotEmpty
    private String systemName;

    @NotEmpty
    private String deviceType;

}
