import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;

public class Configuration {
    String filename;
    HashMap<String, String> values;

    boolean readFile (String filename) {
        this.filename = filename;

        try {
            Path path = Path.of(filename);
            String[] lines = Files.readAllLines(path).toArray(new String[0]);

            for (String line : lines) {
                processLine(line);
            }
        } catch (IOException e) {
            return false;
        }

        return true;
    }

    void processLine (String line) throws IOException {
        String[] parts = line.split("=");

        if (parts.length != 2) {
            throw new IOException();
        }

        values.put(parts[0], parts[1]);

        System.out.println("load "+parts[0]+" "+parts[1]);
    }

    String getValue (String keyword) {
        return values.getOrDefault(keyword, null);
    }
}
