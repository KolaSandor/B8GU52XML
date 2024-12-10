package DomParseB8GU52;

import java.io.File;
import java.io.StringWriter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class DOMWriteB8GU52 {

    public static void main(String[] args) throws ParserConfigurationException, TransformerException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.newDocument();

        // Gyökérelem
        Element root = doc.createElement("B8GU52_Étterem");
        doc.appendChild(root);

        // Éttermek hozzáadása
        root.appendChild(createÉtterem(doc, "E001", "Gourmet Bistro", "06301234567", "12345", "Elm Avenue", "34"));
        root.appendChild(createÉtterem(doc, "E002", "Pepper's Grill", "06309876543", "54321", "Oak Street", "12"));
        root.appendChild(createÉtterem(doc, "E003", "Sunrise Café", "06701239876", "11223", "Willow Lane", "89"));

        // Alkalmazottak hozzáadása
        root.appendChild(createAlkalmazott(doc, "A001", "E001", "Emily Watson", "M789", "DEF987654", "1985-03-10", "Nem"));
        root.appendChild(createAlkalmazott(doc, "A002", "E002", "Michael Brown", "M456", "DEF123456", "1992-11-20", "Igen"));
        root.appendChild(createAlkalmazott(doc, "A003", "E003", "Sarah Johnson", "M789", "DEF678901", "2000-06-15", "Nem"));

        // Rendelések hozzáadása
        root.appendChild(createRendelés(doc, "R001", "E001", "V001", "3000", "2024.01.01", "Aktív"));
        root.appendChild(createRendelés(doc, "R002", "E002", "V002", "12000", "2024.02.20", "Kiszállítva"));
        root.appendChild(createRendelés(doc, "R003", "E003", "V003", "5800", "2024.03.15", "Kiszállítva"));

        // Termékek hozzáadása
        root.appendChild(createTermék(doc, "T001", "Deluxe Pizza", "Főétel", new String[]{"pepperoni", "mozzarella", "olívabogyó"}));
        root.appendChild(createTermék(doc, "T002", "Veggie Delight", "Előétel", new String[]{"spenót", "paradicsom"}));
        root.appendChild(createTermék(doc, "T003", "Tropical Salad", "Köret", new String[]{"saláta", "mangó", "uborka"}));

        // Vendégek hozzáadása
        root.appendChild(createVendég(doc, "V001", "Laura Smith", "laura@example.com", "06305556677"));
        root.appendChild(createVendég(doc, "V002", "Daniel Carter", "daniel@example.com", "06701233445"));
        root.appendChild(createVendég(doc, "V003", "Sophia White", "sophia@example.com", "06307778899"));

        // Termékek-Rendelések kapcsolatok
        root.appendChild(createTermékek(doc, "T001", "R001", "3200"));
        root.appendChild(createTermékek(doc, "T002", "R002", "2800"));
        root.appendChild(createTermékek(doc, "T003", "R003", "4500"));

        // Konzolra kiírás
        System.out.println("XML fa:");
        printDocument(doc);

        // XML mentése fájlba
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");

        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File("Output_B8GU52.xml"));

        transformer.transform(source, result);

        System.out.println("XML létrehozva: Output_B8GU52.xml");
    }

    private static void printDocument(Document doc) throws TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");

        StringWriter writer = new StringWriter();
        StreamResult console = new StreamResult(writer);
        DOMSource source = new DOMSource(doc);

        transformer.transform(source, console);
        System.out.println(writer.toString());
    }

    private static Element createÉtterem(Document doc, String id, String név, String telefonszám, String irsz, String utca, String házszám) {
        Element étterem = doc.createElement("Étterem");
        étterem.setAttribute("id", id);
        étterem.appendChild(createElement(doc, "Név", név));
        étterem.appendChild(createElement(doc, "Telefonszám", telefonszám));
        Element cím = doc.createElement("Cím");
        cím.appendChild(createElement(doc, "Irányítószám", irsz));
        cím.appendChild(createElement(doc, "Utca", utca));
        cím.appendChild(createElement(doc, "Házszám", házszám));
        étterem.appendChild(cím);
        return étterem;
    }

    private static Element createAlkalmazott(Document doc, String id, String munka, String név, String taj, String bankszámlaszám, String születésiIdő, String beosztás) {
        Element alkalmazott = doc.createElement("Alkalmazott");
        alkalmazott.setAttribute("id", id);
        alkalmazott.setAttribute("Munka", munka);
        alkalmazott.appendChild(createElement(doc, "Név", név));
        alkalmazott.appendChild(createElement(doc, "TAJ", taj));
        alkalmazott.appendChild(createElement(doc, "Bankszámlaszám", bankszámlaszám));
        alkalmazott.appendChild(createElement(doc, "SzületésiIdő", születésiIdő));
        alkalmazott.appendChild(createElement(doc, "Beosztás", beosztás));
        return alkalmazott;
    }

    private static Element createRendelés(Document doc, String id, String felszolgálva, String rendelve, String ár, String idő, String státusz) {
        Element rendelés = doc.createElement("Rendelés");
        rendelés.setAttribute("id", id);
        rendelés.setAttribute("Felszolgálva", felszolgálva);
        rendelés.setAttribute("Rendelve", rendelve);
        rendelés.appendChild(createElement(doc, "Ár", ár));
        rendelés.appendChild(createElement(doc, "Idő", idő));
        rendelés.appendChild(createElement(doc, "Státusz", státusz));
        return rendelés;
    }

    private static Element createTermék(Document doc, String id, String név, String típus, String[] alapanyagok) {
        Element termék = doc.createElement("Termék");
        termék.setAttribute("id", id);
        termék.appendChild(createElement(doc, "Név", név));
        termék.appendChild(createElement(doc, "Típus", típus));
        for (String alapanyag : alapanyagok) {
            termék.appendChild(createElement(doc, "Alapanyag", alapanyag));
        }
        return termék;
    }

    private static Element createVendég(Document doc, String id, String név, String email, String telefonszám) {
        Element vendég = doc.createElement("Vendég");
        vendég.setAttribute("id", id);
        vendég.appendChild(createElement(doc, "Név", név));
        vendég.appendChild(createElement(doc, "Email", email));
        vendég.appendChild(createElement(doc, "Telefonszám", telefonszám));
        return vendég;
    }

    private static Element createTermékek(Document doc, String termékId, String rendelésId, String ár) {
        Element termékek = doc.createElement("Termékek");
        termékek.setAttribute("termék_id", termékId);
        termékek.setAttribute("rendelés_id", rendelésId);
        termékek.appendChild(createElement(doc, "Ár", ár));
        return termékek;
    }

    private static Element createElement(Document doc, String name, String value) {
        Element element = doc.createElement(name);
        element.appendChild(doc.createTextNode(value));
        return element;
    }
}
