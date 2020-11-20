import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MobileDevice {
    Configuration config;
    List<Contact> recentContacts = new ArrayList<>();
    List<Test> positiveTests = new ArrayList<>();
    String myHash;
    Government gov;

    MobileDevice (String configurationFile, Government contactTracer) {
        if (configurationFile == null || contactTracer == null) {
            System.err.println("Invalid device arguments.");
            return;
        }

        gov = contactTracer;

        if (!config.readFile(configurationFile)) {
            System.err.println("Cannot read mobile configuration file.");
            return;
        }

        String addr = config.getValue("address");
        String deviceName = config.getValue("deviceName");

        if (addr == null || deviceName == null) {
            System.err.println("Cannot compute device hash.");
            return;
        }

        myHash = Integer.toString((addr + deviceName).hashCode());
    }

    /*
     * individual: device hash
     */
    void recordContact (String individual, int date, int duration) {
        Contact newContact = new Contact(myHash, individual, date, duration);
        recentContacts.add(newContact);
    }

    void positiveTest (String testHash) {
        Test newTest = new Test(myHash, testHash);
        positiveTests.add(newTest);
    }

    boolean synchronizeData () {
        StringBuilder allData = new StringBuilder();
        boolean nearCovid;

        for (Contact c : recentContacts) {
            allData.append(c.toXML());
        }

        for (Test t : positiveTests) {
            allData.append(t.toXML());
        }

        nearCovid = gov.mobileContact(myHash, allData.toString());

        return nearCovid;
    }

}
