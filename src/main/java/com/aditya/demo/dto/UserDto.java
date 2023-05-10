package com.aditya.demo.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDto {

    private int id;
    private String alias;
    private String name;
}
