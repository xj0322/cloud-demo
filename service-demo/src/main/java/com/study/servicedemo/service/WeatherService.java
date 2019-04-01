package com.study.servicedemo.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class WeatherService {
    protected static final Logger log= LoggerFactory.getLogger(WeatherService.class);
    //城市列表缓存
    private HashMap<String,String> cityCache=new HashMap<>();
    //天气缓存
    private HashMap<String,Object> weatherCache=new HashMap<>();
    @Autowired
    private RestTemplate restTemplate;
    @Value("${city.url}")
    private String cityUrl;
    @Value("${weather.url}")
    private String weatherUrl;


    public Object get7DaysWeatherService(String cityName){
        String cityId=null;
        if (cityCache.containsKey(cityName)){
            cityId=cityCache.get(cityName);
            log.info("命中缓存:"+cityName);
        }else {
            String cityList = restTemplate.getForObject(cityUrl, String.class);
            List<Map<String, String>> objects = new Gson().fromJson(cityList, new MyListTypeToken().getType());
            for (Map<String, String> object:objects){
                cityCache.put(object.get("cityZh"),object.get("id"));
            }
            cityId=cityCache.get(cityName);
            log.info("未命中缓存:"+cityName);
        }
        if (weatherCache.containsKey(cityId)){
            log.info("天气命中缓存:"+cityName);
            return weatherCache.get(cityId);
        }else {
            Object weatherInfo = restTemplate.getForObject(weatherUrl+"&cityid="+cityId, Object.class);
            weatherCache.put(cityId,weatherInfo);
            log.info("天气未命中缓存:"+cityName);
            return weatherCache.get(cityId);
        }

    }
    private static final class MyListTypeToken extends TypeToken<List<Map<String, String>>> {

    }

}
