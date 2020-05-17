package com.shenk.ws.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class HttpUrlClient {

    public static void main(String[] args) throws IOException {
        URL url = new URL("http://127.0.0.1:8888/weather");

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("POST");

        connection.setRequestProperty("content-type", "text/xml;charset=utf-8");

        connection.setDoInput(true);
        connection.setDoOutput(true);

        connection.getOutputStream().write(getSoapXml("合肥").getBytes());

        int responseCode = connection.getResponseCode();

        //结果为xml字符串,难以解析
        if(200 == responseCode) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String res = null;
            while((res= reader.readLine()) != null) {
                System.out.println(res);
            }
        }
    }

    static String getSoapXml(String cityName) {
        /**
         * WsClient请求时tcpdump抓包所得
         * <?xml version="1.0" ?><S:Envelope xmlns:S="http://schemas.xmlsoap.org/soap/envelope/"><S:Body><ns2:queryWeather xmlns:ns2="http://service.ws.accrecord.shenk.com/"><arg0>......</arg0></ns2:queryWeather></S:Body></S:Envelope>
         */
        String soapXML = "<?xml version=\"1.0\" encoding=\"utf-8\"?>"
                +"<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">"
                +"<soap:Body>"
                +"<ns1:queryWeather xmlns:ns1=\"http://service.ws.accrecord.shenk.com/\">"
                +"<arg0>"+cityName+"</arg0>"
                +"</ns1:queryWeather>"
                +"</soap:Body>"
                +"</soap:Envelope>";
        return soapXML;
    }


}
