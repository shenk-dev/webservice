package com.shenk.ws.service;

import javax.jws.WebService;

@WebService
public class WeatherServiceImpl implements WeatherService {

    @Override
    public String queryWeather(String cityName) {
        System.out.println("query weather of " + cityName);
        return "sunny";
    }
}
