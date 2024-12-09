package DomParseB8GU52;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.xpath.*;
import java.io.File;
import java.util.Scanner;

public class DOMQueryB8GU52 {
    public static void main(String[] args) {
        try {
            File xmlFile = new File("XMLB8GU52.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();

            Scanner sc = new Scanner(System.in);
            System.out.println("Enter the name of the element: ");
            String elementName = sc.nextLine();

            System.out.println("Enter the ID of the element: ");
            String elementID = sc.nextLine();
            sc.close();

            queryElementByID(doc, elementName, elementID);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void queryElementByID(Document doc, String elementName, String elementID) {
        try {
            XPathFactory xPathfactory = XPathFactory.newInstance();
            XPath xpath = xPathfactory.newXPath();
            String xpathQuery = "//" + elementName + "[@id='" + elementID + "']";

            XPathExpression expr = xpath.compile(xpathQuery);
            Node node = (Node) expr.evaluate(doc, XPathConstants.NODE);

            if (node != null) {
                System.out.println("Found element: " + node.getNodeName());
                NodeList children = node.getChildNodes();
                for (int i = 0; i < children.getLength(); i++) {
                    Node child = children.item(i);
                    if (child.getNodeType() == Node.ELEMENT_NODE) {
                        System.out.println(child.getNodeName() + ": " + child.getTextContent());
                    }
                }
            } else {
                System.out.println("Element not found.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
