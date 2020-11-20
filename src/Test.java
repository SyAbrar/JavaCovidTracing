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
                "" +
                "</test>";
    }

    static List<Test> fromXML () {
        // TODO;
        List<Test> result = new ArrayList<>();

        return result;
    }
}
