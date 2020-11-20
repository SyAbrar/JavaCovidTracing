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

public class Test {
    String deviceHash;
    String testHash;

    Test (String deviceHash, String testHash) {
        this.deviceHash = deviceHash;
        this.testHash = testHash;
    }

    String toXML() {
        return "<test>" +
                "<device_hash>" + deviceHash + "</device_hash>" +
                "<test_hash>" + testHash + "</test_hash>" +
                "</test>";
    }

    static List<Test> fromXML (String xmlData) throws IOException {
        List<Test> result = new ArrayList<>();
        Document doc;

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = null;
            builder = factory.newDocumentBuilder();
            InputSource is = new InputSource(new StringReader(xmlData));
            doc = builder.parse(is);
        } catch (IOException | ParserConfigurationException | SAXException e) {
            throw new IOException();
        }

        NodeList nodes = doc.getElementsByTagName("test");

        for (int i = 0; i < nodes.getLength(); i++) {
            Element elem = (Element)nodes.item(i);
            String deviceHash = elem.getElementsByTagName("device_hash").item(0).getNodeValue();
            String testHash = elem.getElementsByTagName("test_hash").item(0).getNodeValue();
            Test t = new Test(deviceHash, testHash);
            result.add(t);
        }

        return result;
    }
}
