import java.io.IOException;
import java.util.List;

public class Government {
    Configuration config;
    DB db;

    Government (String configurationFile) {
        config.readFile(configurationFile);
        db = new DB(config.getValue("database"), config.getValue("user"),
                config.getValue("password"));
    }

    boolean mobileContact (String initiator, String contactInfo) {
        // TODO for each contact
        List<Contact> contacts = Contact.fromXML(contactInfo);

        for (Contact contact : contacts) {
            db.storeContact(initiator, contact);
        }

        return true;
    }

    // Separate method for communicating a test result, in this demo, called
    // directly by MobileDevice.synchronizeData
    boolean mobileTest (String initiator, String testInfo) {
        // Government only knows the test hash so far, update the record to store the correct device id as well

        db.setTestDeviceHash(initiator, testInfo);
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
