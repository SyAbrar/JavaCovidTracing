import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class Contact {
    String deviceA;
    String deviceB;
    int date;
    int duration;

    Contact (String deviceA, String deviceB, int date, int duration) {
        this.deviceA = deviceA;
        this.deviceB = deviceB;
        this.date = date;
        this.duration = duration;
    }

    String toXML() {
        return "<contact>"  +
                "<device_a>" + deviceA + "</device_a>" +
                "<device_b>" + deviceB + "</device_b>" +
                "<date>"    + date + "</date>" +
                "<duration>" + duration + "</duration>" +
                "</contact>";
    }

    static List<Contact> fromXML (String xmlData) throws IOException {
        List<Contact> result = new ArrayList<>();
        Document doc = null;

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = null;
            builder = factory.newDocumentBuilder();
            InputSource is = new InputSource(new StringReader(xmlData));
            doc = builder.parse(is);
        } catch (SAXException | IOException | ParserConfigurationException e) {
            throw new IOException();
        }

        NodeList nodes = doc.getElementsByTagName("contact");

        for (int i = 0; i < nodes.getLength(); i++) {
            Element elem = (Element)nodes.item(i);
            String deviceA = elem.getElementsByTagName("device_a").item(0).getNodeValue();
            String deviceB = elem.getElementsByTagName("device_b").item(0).getNodeValue();
            String date = elem.getElementsByTagName("date").item(0).getNodeValue();
            String duration = elem.getElementsByTagName("duration").item(0).getNodeValue();
            Contact c = new Contact(deviceA, deviceB, Integer.parseInt(date), Integer.parseInt(duration));
            result.add(c);
        }

        return result;
    }
}
