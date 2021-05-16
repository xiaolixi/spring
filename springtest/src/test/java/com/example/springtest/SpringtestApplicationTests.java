package com.example.springtest;

import com.example.springtest.serivice.IMyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringtestApplicationTests {

    @Autowired
    private IMyService myService;
    @Test
    void contextLoads() {
        myService.hello();
    }

}
