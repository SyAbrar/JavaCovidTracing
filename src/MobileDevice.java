import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MobileDevice {
    Configuration config;
    List<Contact> recentContacts = new ArrayList<>();

    MobileDevice (String configurationFile, Government contactTracer) {
        config.readFile(configurationFile);
    }

    void recordContact (String individual, int date, int duration) {

    }

    void positiveTest (String testHash) {

    }

    boolean synchronizeData () {
        return true;
    }
}
