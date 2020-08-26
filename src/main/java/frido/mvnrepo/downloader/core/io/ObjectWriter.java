package frido.mvnrepo.downloader.core.io;

import frido.mvnrepo.downloader.github.JsonWrapper;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class ObjectWriter implements AutoCloseable {

    private final PrintWriter printWriter;

    public ObjectWriter(String fileName, boolean append) throws IOException {
        FileWriter fileWriter = new FileWriter(fileName, append);
        printWriter = new PrintWriter(fileWriter);
    }

    public void println(JsonWrapper jsonWrapper) {
        printWriter.println(jsonWrapper);
    }

    public void println(String s) {
        printWriter.println(s);
    }


    public void printPart(JsonWrapper jsonWrapper) {
        println(jsonWrapper);
        println(",");
    }

    @Override
    public void close() throws Exception {
        printWriter.close();
    }
}
