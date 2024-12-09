package DomParseB8GU52;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.Scanner;

public class DOMModifyB8GU52 {
    public static void main(String[] args) {
        try {
            File xmlFile = new File("XMLB8GU52.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();

            Scanner sc = new Scanner(System.in);
            System.out.println("Enter the name of the element to modify: ");
            String elementName = sc.nextLine();

            System.out.println("Enter the ID of the element: ");
            String elementID = sc.nextLine();

            System.out.println("Enter the name of the property to modify: ");
            String propertyName = sc.nextLine();

            System.out.println("Enter the new value: ");
            String newValue = sc.nextLine();
            sc.close();

            if (modifyElementByID(doc, elementName, elementID, propertyName, newValue)) {
                saveToFile(doc, "Modified_XML_B8GU52.xml");
                System.out.println("Modification successful. Saved to Modified_XML_B8GU52.xml");
            } else {
                System.out.println("Modification failed.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean modifyElementByID(Document doc, String elementName, String elementID, String propertyName, String newValue) {
        try {
            NodeList nodeList = doc.getElementsByTagName(elementName);

            for (int i = 0; i < nodeList.getLength(); i++) {
                Element element = (Element) nodeList.item(i);
                if (element.getAttribute("id").equals(elementID)) {
                    element.getElementsByTagName(propertyName).item(0).setTextContent(newValue);
                    return true;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void saveToFile(Document doc, String filename) {
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(filename));
            transformer.transform(source, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
