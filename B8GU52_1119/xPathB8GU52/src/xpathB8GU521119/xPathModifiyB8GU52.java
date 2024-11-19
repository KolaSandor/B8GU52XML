package xpathB8GU521119;

import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class xPathModifiyB8GU52 {

    public static void main(String[] args) {
        try {
            // XML fájl betöltése
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse("orarendB8GU52.xml");
            doc.getDocumentElement().normalize();

            // 1. Módosítás: szak nevének módosítása
            NodeList szakNodes = doc.getElementsByTagName("szak");
            for (int i = 0; i < szakNodes.getLength(); i++) {
                szakNodes.item(i).setTextContent("Szabad Bölcsész");
            }

            // 2. Módosítás: tárgy nevéhez monogram hozzáfűzése
            NodeList targyNodes = doc.getElementsByTagName("targy");
            for (int i = 0; i < targyNodes.getLength(); i++) {
                Element targy = (Element) targyNodes.item(i);
                String updatedText = targy.getTextContent() + " (KS)";
                targy.setTextContent(updatedText);
            }

            // 3. Módosítás: id=3 példány - helyszín: XXXVII
            NodeList oraNodes = doc.getElementsByTagName("ora");
            for (int i = 0; i < oraNodes.getLength(); i++) {
                Element ora = (Element) oraNodes.item(i);
                if (ora.hasAttribute("id") && ora.getAttribute("id").equals("MesInt")) {
                    NodeList helyszinNodes = ora.getElementsByTagName("helyszin");
                    for (int j = 0; j < helyszinNodes.getLength(); j++) {
                        helyszinNodes.item(j).setTextContent("XXXVII");
                    }
                }
            }

            // Módosított XML kiírása konzolra
            System.out.println("Módosított XML:");
            printDocument(doc);

            // Módosított XML mentése fájlba
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("orarendB8GU521.xml"));
            transformer.transform(source, result);

            System.out.println("Módosított XML mentve az 'orarendB8GU521.xml' fájlba.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // XML kiírása konzolra
    private static void printDocument(Document doc) throws TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
        DOMSource source = new DOMSource(doc);
        StreamResult consoleResult = new StreamResult(System.out);
        transformer.transform(source, consoleResult);
    }
}
