package frido.mvnrepo.downloader.core.io;

import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;

public class ListWriter implements Closeable {

    private BufferedWriter writer;

    public ListWriter(String folder, String fileName) throws IOException {
        writer = new BufferedWriter(new FileWriter(Paths.get(folder, fileName).toFile()));
    }

    public synchronized void append(String str) {
        try {
            writer.write(str);
            writer.write("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
