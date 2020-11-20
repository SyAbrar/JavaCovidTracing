import java.util.ArrayList;
import java.util.List;

public class Contact {
    String deviceA;
    String deviceB;
    int date;
    int duration;

    Contact (String deviceA, String deviceB, int date, int duration) {
        this.deviceA = deviceA;
        this.deviceB = deviceB;
        this.date = date;
        this.duration = duration;
    }

    String toXML() {
        return "<contact>" +
                "" +
                "</contact>";
    }

    static List<Contact> fromXML (String xmlData) {
        List<Contact> result = new ArrayList<>();
        // TODO;
        return result;
    }
}
