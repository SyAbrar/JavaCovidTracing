import java.util.ArrayList;
import java.util.List;

public class DB {
    DB (String domain, String username, String password) {
        // TODO
        init();
    }

    void storeTestResult (String testHash, int date, boolean result) {
        // TODO
    }

    void setTestDeviceHash (String deviceHash, String testHash) {
        // TODO update test row to have the device hash as well
    }

    void storeContact (String initiator, Contact contact) {
        // TODO
    }

    List<Contact> getContactsOnDate (int date) {
        List<Contact> result = new ArrayList<>();
        // TODO
        return result;
    }

    boolean wasNearPositive (String deviceHash) {
        // TODO
        return false;
    }

    private void init () {
        // TODO create tables if not exists
    }
}
