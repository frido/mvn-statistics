package frido.mvnrepo.downloader.metadata;

import frido.mvnrepo.downloader.core.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MetadataReader implements ResponseHandler, StopHandler {

    private FileLogger file;
    private Downloader downloader;

    public void start() throws IOException {
        this.file = new FileLogger("pom.txt");
        downloader = new Downloader(10);
        downloader.registerStopHandler(this);
        try (BufferedReader buffer = Files.newBufferedReader(Paths.get("metadata.txt"))) {
            buffer.lines().forEach(l -> downloader.download(new Link(l), this));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void handleResponse(ResponseBody response) {
        MetadataBody body = new MetadataBody(response);
        if (body.isValid()) {
            var pomLink = body.getPomLink();
//            System.out.println(pomLink);
            file.append(pomLink);
        }
    }

    @Override
    public void stop() {
        downloader.shutdown();
        file.close();
    }
}
