package com.shenk.ws.service;

import javax.xml.ws.Endpoint;

public class WsPublish {

    public static void main(String[] args) {
        Endpoint.publish("http://127.0.0.1:8888/weather", new WeatherServiceImpl());
    }
}
