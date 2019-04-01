package com.study.servicedemo.controller;

import com.study.servicedemo.service.WeatherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/weather")
public class WeatherCrontroller {
    protected static final Logger log= LoggerFactory.getLogger(WeatherCrontroller.class);
    @Autowired
    private WeatherService weatherService;
    @RequestMapping("/v1")
    public Object get7daysWeather(String cityName){
        long startTime = System.currentTimeMillis();
        Object daysWeatherInfo = weatherService.get7DaysWeatherService(cityName);
        long endTime = System.currentTimeMillis();
        log.info("耗时"+(endTime-startTime)+"ms");
        return daysWeatherInfo;
    }
}
