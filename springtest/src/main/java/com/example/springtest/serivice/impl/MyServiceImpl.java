package com.example.springtest.serivice.impl;

import com.example.springtest.serivice.IMyService;
import org.springframework.stereotype.Service;


@Service
public class MyServiceImpl implements IMyService {

    @Override
    public String hello() {
        return "heko";
    }
}
