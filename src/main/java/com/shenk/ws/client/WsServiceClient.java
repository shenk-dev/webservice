package com.shenk.ws.client;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.io.IOException;
import java.net.URL;

public class WsServiceClient {

    public static void main(String[] args) throws IOException {

        //创建wsdl的url
        URL url = new URL("http://127.0.0.1:8888/weather?wsdl");

        //服务名称 localpart 服务视图名 wsdl中<service name=""/>
        QName qName = new QName("http://service.ws.accrecord.shenk.com/", "WeatherServiceImplService");

        Service service = Service.create(url, qName);

        //服务实现类 wsdl中 <portType name="WeatherServiceImpl">
        WeatherServiceImpl serviceImpl = service.getPort(WeatherServiceImpl.class);

        String res = serviceImpl.queryWeather("南京");

        System.out.println(res);



    }
}
