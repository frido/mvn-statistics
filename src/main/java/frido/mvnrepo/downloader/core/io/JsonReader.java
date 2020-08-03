package frido.mvnrepo.downloader.core.io;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class JsonReader {
    private final Path file;

    public JsonReader(String fileName) {
        file = Paths.get(fileName);
    }

    public <T> T read(Class<T> clazz) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        try {
            return mapper.readValue(file.toFile(), clazz);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null; // TODO: return null???
    }
}