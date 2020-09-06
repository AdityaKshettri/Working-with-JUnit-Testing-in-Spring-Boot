package com.aditya.demo.mapper;

import com.aditya.demo.dto.BasicDto;
import com.aditya.demo.model.Basic;
import com.aditya.demo.request.CreateRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BasicMapper {

    public BasicDto map(Basic basic) {
        BasicDto basicDto = new BasicDto();
        basicDto.setId(basic.getId());
        basicDto.setName(basic.getName());
        return basicDto;
    }

    public Basic map(BasicDto basicDto) {
        Basic basic = new Basic();
        basic.setId(basicDto.getId());
        basic.setName(basicDto.getName());
        return basic;
    }

    public Basic map(CreateRequest createRequest) {
        Basic basic = new Basic();
        basic.setName(createRequest.getName());
        return basic;
    }

    public List<BasicDto> map(List<Basic> basics) {
        return basics
                .stream()
                .map(basic -> map(basic))
                .collect(Collectors.toList());
    }
}
