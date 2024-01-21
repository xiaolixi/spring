package com.forezp.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

/**
 * @author
 */
@RestController
public class MyController {
    @PostMapping("/test")
    public String handleFileUpload(HttpServletRequest request) {
        System.out.println("handleFileUpload");
        Map<String, String[]> parameterMap = request.getParameterMap();
        parameterMap.forEach((key, value) -> System.out.println(key + ": " + Arrays.asList(value).toString()));
        System.out.println("handleFileUpload");
        return JSON.toJSONString(request.getParameterMap());
    }

    @GetMapping("/")
    public String listUploadedFiles(HttpServletRequest request) {
        System.out.println("listUploadedFiles");
        Map<String, String[]> parameterMap = request.getParameterMap();
        parameterMap.forEach((key, value) -> System.out.println(key + ": " + Arrays.asList(value).toString()));
        System.out.println("listUploadedFiles");
        return JSON.toJSONString(request.getParameterMap());
    }
}