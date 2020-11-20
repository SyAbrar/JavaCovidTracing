import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

public class Government {
    Configuration config;
    DB db;

    Government (String configurationFile) {
        config = new Configuration();
        config.readFile(configurationFile);

        String addr, user, password;

        addr = config.getValue("database");
        user = config.getValue("user");
        password = config.getValue("password");

        if (addr == null) {
            System.err.println("No database address in config (database=....");
            return;
        }

        if (user == null) {
            System.err.println("No database user in config (user=....");
            return;
        }

        if (password == null) {
            System.err.println("No database password in config (password=....");
            return;
        }

        db = new DB(addr, user, password);
    }

    boolean mobileContact (String initiator, String contactInfo) {
        // TODO for each contact
        List<Contact> contacts = null;
        List<Test> tests = null;

        try {
            contacts = Contact.fromXML(contactInfo);
            tests = Test.fromXML(contactInfo);
        } catch (IOException e) {
            return false;
        }

        for (Contact contact : contacts) {
            db.storeContact(initiator, contact);
        }

        for (Test test : tests) {
            db.setTestDeviceHash(test.deviceHash, test.testHash);
        }

        // TODO
        boolean wasClose = false;

        return wasClose;
    }

    void recordTestResult (String testHash, int date, boolean result) {
        db.storeTestResult(testHash, date, result);
        // TODO
    }

    int findGatherings (int date, int minSize, int minTime, float density) {
        db.getContactsOnDate(date);
        // TODO
        return 0;
    }
}
