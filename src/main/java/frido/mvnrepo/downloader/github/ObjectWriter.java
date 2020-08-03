package frido.mvnrepo.downloader.github;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

// TODO: extends Writer
public class ObjectWriter implements AutoCloseable {

    private final PrintWriter printWriter;

    // TODO: static create/open
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
        printWriter.flush();
    }

    @Override
    public void close() throws Exception {
        printWriter.close();
    }
}
