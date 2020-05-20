package com.shenk.ws.service;

import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface WeatherService {

    public String queryWeather(@WebParam(name="cityName") String cityName, @WebParam(name="xml") String xml);
}
