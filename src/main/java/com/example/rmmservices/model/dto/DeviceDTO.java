package com.example.rmmservices.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeviceDTO {

    private Long id;

    @NotNull
    @Min(1)
    private Long customerId;

    @NotEmpty
    private String systemName;

    @NotEmpty
    private String deviceType;

}
