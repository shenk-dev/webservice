package com.shenk.ws.service;

import com.shenk.ws.util.XmlUtil;

import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public class WeatherServiceImpl implements WeatherService {

    public String queryWeather(@WebParam(name="cityName") String cityName, @WebParam(name="xml") String xml) {
        System.out.println("query weather of " + cityName);
        System.out.println(XmlUtil.getEnergy(xml));
        return "sunny";
    }
}
