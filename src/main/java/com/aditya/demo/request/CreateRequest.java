package com.aditya.demo.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateRequest {

    private String alias;
    private String name;
}
