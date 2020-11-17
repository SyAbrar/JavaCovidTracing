import java.io.IOException;

public class Government {
    Configuration config;
    DB db;

    Government (String configurationFile) {
        config.readFile(configurationFile);
        db = new DB(config.getValue("addr"), config.getValue("username")));
    }

    boolean mobileContact (String initiator, String contactInfo) {
        // TODO for each contact
        Contact.fromXML(contactInfo);
        DB.storeContact(initiator, contact);
        return true;
    }

    // Separate method for communicating a test result, in this demo, called
    // directly by MobileDevice.synchronizeData
    boolean mobileTest (String initiator, String testInfo) {
        // Government only knows the test hash so far, update the record to store the correct device id as well
        DB.setTestDeviceHash(initiator, testHash);
        return true;
    }

    void recordTestResult (String testHash, int date, boolean result) {
        DB.storeTestResult(testHash, date, result);
    }

    int findGatherings (int date, int minSize, int minTime, float density) {
        DB.getContactsOnDate(date);
        return 0;
    }
}
