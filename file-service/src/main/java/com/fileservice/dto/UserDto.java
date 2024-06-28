package com.fileservice.dto;

import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String email;
    private Integer roleId;
}
