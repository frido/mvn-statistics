package frido.mvnrepo.downloader.core.io;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

// TODO: Join with ObjectWriter
public class JsonWriter {
    private final Path file;

    public JsonWriter(String fileName) {
        file = Paths.get(fileName);
    }

    public JsonWriter(String reportFolder, String fileName) {
        file = Paths.get(reportFolder, fileName);
    }

    public void write(Object toJson) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        try {
            mapper.writeValue(file.toFile(), toJson);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
