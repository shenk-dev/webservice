package com.shenk.ws.util;

import com.shenk.ws.domian.Item;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayOutputStream;
import java.io.StringReader;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by gavin on 16/3/21.
 */

public class XmlUtil {

    public static String getStripIdFromXml(String string) {
        return null;
    }

    /**
     * 查询能量值
     *
     * @parma xml 待解析xml字符
     * @return 能量值
     */
    public static String getEnergy(String xml) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();

            Document document = builder.parse(new InputSource(new StringReader(xml)));
            NodeList list = document.getElementsByTagName("item");
            for (int i = 0; i < list.getLength(); i++) {
                Element element = (Element) list.item(i);
                if ("ENERGY".equals(element.getAttribute("tagCode"))) {
                    String energy =  element.getAttribute("tagValue");
                }
            }
        }catch (Exception e) {
        }
        return null;
    }


    public static String createXmlStr(String rootName, Map<String, String> rootAttr, List<Item> items, boolean timeStamp) throws Exception {
        Date now = new Date();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.newDocument();
        document.setXmlStandalone(true);
        Element root = document.createElement(rootName);
        root.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
        root.setAttribute("xmlns:xsd", "http://www.w3.org/2001/XMLSchema");
        //add attr for root element
        for (Map.Entry<String, String> entry : rootAttr.entrySet()) {
            root.setAttribute(entry.getKey(), entry.getValue());
        }
        if(timeStamp){
            root.setAttribute("timeStamp", DateUtil.convertDate2Str(now, "yyyy-MM-dd HH:mm:ss"));
        }
        document.appendChild(root);

        //add item element
        if(items != null) {
            for (Item item : items) {
                Element itemNode = document.createElement("item");
                itemNode.setAttribute("tagCode", item.getItemCode());
                itemNode.setAttribute("tagValue", item.getItemValue());
                itemNode.setAttribute("timeStamp", DateUtil.convertDate2Str(now, "yyyy-MM-dd HH:mm:ss"));
                root.appendChild(itemNode);
            }
        }

        TransformerFactory transFactory = TransformerFactory.newInstance();
        Transformer transformer = transFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        DOMSource domSource = new DOMSource(document);

        // xml transform String
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        transformer.transform(domSource, new StreamResult(bos));
        return bos.toString();
    }

}
