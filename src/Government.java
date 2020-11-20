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
        List<Contact> contacts = Contact.fromXML(contactInfo);

        for (Contact contact : contacts) {
            db.storeContact(initiator, contact);
        }

        // db.setTestDeviceHash(initiator, testInfo);

        return true;
    }

    void recordTestResult (String testHash, int date, boolean result) {
        db.storeTestResult(testHash, date, result);
    }

    int findGatherings (int date, int minSize, int minTime, float density) {
        db.getContactsOnDate(date);
        return 0;
    }
}
