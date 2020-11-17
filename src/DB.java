import java.util.List;

public class DB {
    DB (String domain, String username, String password) {
        // TODO
        init();
    }

    void storeTestResult () {
        // TODO
    }

    void setTestDeviceHash (String deviceHash, String testHash) {
        // TODO update test row to have the device hash as well
    }

    void storeContact () {
        // TODO
    }

    List<Contact> getContactsOnDate (int date) {
        // TODO
    }

    boolean wasNearPositive (String deviceHash) {
        // TODO
    }

    private void init () {
        // TODO create tables if not exists
    }
}
