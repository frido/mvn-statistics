package frido.mvnrepo.downloader.github;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

// TODO: move to ObjectWriter
// delete
public class JsonWrapper {

    private Object data;

    public JsonWrapper(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        try {
            return mapper.writeValueAsString(data);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Unable to serialize data");
        }
    }

    public Object getData() {
        return data;
    }

    // TODO: writeToFile

    // TODO: appendToFile
}
