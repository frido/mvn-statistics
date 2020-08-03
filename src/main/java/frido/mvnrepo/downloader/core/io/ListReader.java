package frido.mvnrepo.downloader.core.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class ListReader {
    private final Path file;

    public ListReader(String fileName) {
        file = Paths.get("metadata.list");
    }

    public Stream<String> lines() {
        try {
            return Files.lines(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Stream.empty();
    }
}
