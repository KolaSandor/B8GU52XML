package domB8GU521105;


import java.io.File;
import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public class DomQueryB8GU52 {

    public static void main(String[] args) {
        try {
        	
            File xmlFile = new File("C:\\Users\\Kola Sándor\\Desktop\\XML\\1105\\B8GU52hallgato.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);


            doc.getDocumentElement().normalize();


            NodeList hallgatoList = doc.getElementsByTagName("hallgato");

            System.out.println("Hallgatók vezetéknevei:");

         
            for (int i = 0; i < hallgatoList.getLength(); i++) {
                Element hallgato = (Element) hallgatoList.item(i);
                NodeList vezeteknevList = hallgato.getElementsByTagName("vezetknev");

                if (vezeteknevList.getLength() > 0) {
                    Element vezeteknev = (Element) vezeteknevList.item(0);
                    System.out.println("Vezetéknév: " + vezeteknev.getTextContent());
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
