package com.study.servicedemo;

import com.study.servicedemo.service.WeatherService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceDemoApplicationTests {
    @Autowired
    private WeatherService weatherService;

    @Test
    public void contextLoads() {
        weatherService.get7DaysWeatherService("北京");
    }

}
