import java.io.IOException;
import java.util.HashMap;

public class Configuration {
    String filename;
    HashMap<String, String> values;

    boolean readFile (String filename) {
        this.filename = filename;

        try {
            // TODO read file
        } catch (IOException e) {
            return false;
        }

        return true;
    }

    String getValue (String keyword) {
        return values.getOrDefault(keyword, null);
    }
}
