package com.study.clientdemo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/test")
public class TestController {
    protected static final Logger log= LoggerFactory.getLogger(TestController.class);
    @Autowired
    private RestTemplate restTemplate;
    @RequestMapping("/testWeather")
    public Object getWeatherInfo(String cityName){
       return restTemplate.getForObject("http://weather-service/weather/v1?cityName="+cityName,Object.class);

    }
}
