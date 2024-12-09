package DomParseB8GU52;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.File;

public class DOMReadB8GU52 {
    public static void main(String[] args) {
        try {
            File xmlFile = new File("XMLB8GU52.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();

            System.out.println("Root element: " + doc.getDocumentElement().getNodeName());
            printElementContent(doc.getDocumentElement(), "");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void printElementContent(Node node, String indent) {
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            // Kiírja az aktuális elem nevét
            System.out.println(indent + "Element: " + node.getNodeName());

            // Kiírja az attribútumokat (ha vannak)
            if (node.hasAttributes()) {
                NamedNodeMap attributes = node.getAttributes();
                for (int i = 0; i < attributes.getLength(); i++) {
                    Node attribute = attributes.item(i);
                    System.out.println(indent + "  Attribute - " + attribute.getNodeName() + ": " + attribute.getNodeValue());
                }
            }

            // Gyermekelemek feldolgozása
            NodeList children = node.getChildNodes();
            for (int i = 0; i < children.getLength(); i++) {
                Node child = children.item(i);

                if (child.getNodeType() == Node.ELEMENT_NODE) {
                    printElementContent(child, indent + "  ");
                } else if (child.getNodeType() == Node.TEXT_NODE) {
                    String textContent = child.getTextContent().trim();
                    if (!textContent.isEmpty()) {
                        System.out.println(indent + "  Text: " + textContent);
                    }
                }
            }
        }
    }
}
