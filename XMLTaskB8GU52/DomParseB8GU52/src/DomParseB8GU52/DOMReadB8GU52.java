package DomParseB8GU52;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

public class DOMReadB8GU52 {
    public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException {

        File xmlFile = new File("XMLB8GU52.xml");

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = factory.newDocumentBuilder();
        Document doc = dBuilder.parse(xmlFile);

        doc.getDocumentElement().normalize();

        System.out.println("Gyökér elem: " + doc.getDocumentElement().getNodeName());

        // Étterem elemek feldolgozása
        NodeList ettermek = doc.getElementsByTagName("Étterem");
        for (int i = 0; i < ettermek.getLength(); i++) {
            Node etteremNode = ettermek.item(i);
            if (etteremNode.getNodeType() == Node.ELEMENT_NODE) {
                Element etteremElem = (Element) etteremNode;
                System.out.println("\nÉtterem ID: " + etteremElem.getAttribute("id"));
                System.out.println("Név: " + etteremElem.getElementsByTagName("Név").item(0).getTextContent());
                System.out.println("Telefonszám: " + etteremElem.getElementsByTagName("Telefonszám").item(0).getTextContent());
                Element cimElem = (Element) etteremElem.getElementsByTagName("Cím").item(0);
                System.out.println("Cím: " +
                        cimElem.getElementsByTagName("Irányítószám").item(0).getTextContent() + ", " +
                        cimElem.getElementsByTagName("Utca").item(0).getTextContent() + " " +
                        cimElem.getElementsByTagName("Házszám").item(0).getTextContent());
            }
        }

        // Alkalmazott elemek feldolgozása
        NodeList alkalmazottak = doc.getElementsByTagName("Alkalmazott");
        for (int i = 0; i < alkalmazottak.getLength(); i++) {
            Node alkalmazottNode = alkalmazottak.item(i);
            if (alkalmazottNode.getNodeType() == Node.ELEMENT_NODE) {
                Element alkalmazottElem = (Element) alkalmazottNode;
                System.out.println("\nAlkalmazott ID: " + alkalmazottElem.getAttribute("id"));
                System.out.println("Munkahely: " + alkalmazottElem.getAttribute("Munka"));
                System.out.println("Név: " + alkalmazottElem.getElementsByTagName("Név").item(0).getTextContent());
                System.out.println("TAJ: " + alkalmazottElem.getElementsByTagName("TAJ").item(0).getTextContent());
                System.out.println("Bankszámlaszám: " + alkalmazottElem.getElementsByTagName("Bankszámlaszám").item(0).getTextContent());
                System.out.println("Születési Idő: " + alkalmazottElem.getElementsByTagName("SzületésiIdő").item(0).getTextContent());
                System.out.println("Beosztás: " + alkalmazottElem.getElementsByTagName("Beosztás").item(0).getTextContent());
            }
        }

        // Rendelés elemek feldolgozása
        NodeList rendelesek = doc.getElementsByTagName("Rendelés");
        for (int i = 0; i < rendelesek.getLength(); i++) {
            Node rendelesNode = rendelesek.item(i);
            if (rendelesNode.getNodeType() == Node.ELEMENT_NODE) {
                Element rendelesElem = (Element) rendelesNode;
                System.out.println("\nRendelés ID: " + rendelesElem.getAttribute("id"));
                System.out.println("Felszolgálva: " + rendelesElem.getAttribute("Felszolgálva"));
                System.out.println("Rendelve: " + rendelesElem.getAttribute("Rendelve"));
                System.out.println("Ár: " + rendelesElem.getElementsByTagName("Ár").item(0).getTextContent());
                System.out.println("Idő: " + rendelesElem.getElementsByTagName("Idő").item(0).getTextContent());
                System.out.println("Státusz: " + rendelesElem.getElementsByTagName("Státusz").item(0).getTextContent());
            }
        }

     // Termék elemek feldolgozása
        NodeList termekekList = doc.getElementsByTagName("Termék");
        for (int i = 0; i < termekekList.getLength(); i++) {
            Node termekNode = termekekList.item(i);
            if (termekNode.getNodeType() == Node.ELEMENT_NODE) {
                Element termekElem = (Element) termekNode;
                System.out.println("\nTermék ID: " + termekElem.getAttribute("id"));
                System.out.println("Név: " + termekElem.getElementsByTagName("Név").item(0).getTextContent());
                System.out.println("Típus: " + termekElem.getElementsByTagName("Típus").item(0).getTextContent());

                // Alapanyagok feldolgozása
                NodeList alapanyagok = termekElem.getElementsByTagName("Alapanyag");
                System.out.print("Alapanyagok: ");
                for (int j = 0; j < alapanyagok.getLength(); j++) {
                    System.out.print(alapanyagok.item(j).getTextContent());
                    if (j < alapanyagok.getLength() - 1) {
                        System.out.print(", ");
                    }
                }
                System.out.println();
            }
        }


     // Vendég elemek feldolgozása
        NodeList vendegList = doc.getElementsByTagName("Vendég");
        for (int i = 0; i < vendegList.getLength(); i++) {
            Node vendegNode = vendegList.item(i);
            if (vendegNode.getNodeType() == Node.ELEMENT_NODE) {
                Element vendegElem = (Element) vendegNode;
                System.out.println("\nVendég ID: " + vendegElem.getAttribute("id"));
                System.out.println("Név: " + vendegElem.getElementsByTagName("Név").item(0).getTextContent());
                System.out.println("Email: " + vendegElem.getElementsByTagName("Email").item(0).getTextContent());
                System.out.println("Telefonszám: " + vendegElem.getElementsByTagName("Telefonszám").item(0).getTextContent());
            }
        }

    }
}
