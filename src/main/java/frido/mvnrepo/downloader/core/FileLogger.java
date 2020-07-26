package frido.mvnrepo.downloader.core;

import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.FileWriter;
import java.io.IOException;

public class FileLogger implements Closeable {

    private BufferedWriter writer;

    public FileLogger(String fileName) throws IOException {
        writer = new BufferedWriter(new FileWriter(fileName));
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

    // TODO: close writer
}
