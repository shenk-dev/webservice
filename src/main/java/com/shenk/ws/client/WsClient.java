package com.shenk.ws.client;

public class WsClient {

    public static void main(String[] args) {
        WeatherServiceImplService service = new WeatherServiceImplService();
        WeatherServiceImpl weatherService = service.getWeatherServiceImplPort();
        String res = weatherService.queryWeather("芜湖");
        System.out.println(res);
    }
}
