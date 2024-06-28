package com.fileservice.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class FileReqDto {
    @NotNull(message = "User email is required")
    private String userEmail;
    @NotNull(message = "Type code is required")
    private String typeCode;
}
