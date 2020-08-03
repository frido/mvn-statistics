package frido.mvnrepo.downloader.metadata;

import frido.mvnrepo.downloader.core.*;
import frido.mvnrepo.downloader.core.io.ListReader;
import frido.mvnrepo.downloader.core.io.ListWriter;

import java.io.IOException;

public class MetadataReader implements ResponseHandler, StopHandler {

    private ListWriter file;
    private Downloader downloader;

    public static void main(String[] args) throws IOException {
        MetadataReader reader = new MetadataReader();
        reader.start();
    }

    public MetadataReader() throws IOException {
        file = new ListWriter("pom.txt");
        downloader = new Downloader(10);
        downloader.registerStopHandler(this);
        downloader.registerResponseHandler(this);
    }

    public void start() {
        new ListReader("metadata.list")
            .lines()
            .map(Link::new)
            .forEach(downloader::download);
    }

    @Override
    public void handleResponse(ResponseBody response) {
        MetadataBody body = new MetadataBody(response);
        if (body.isValid()) {
            var pomLink = body.getPomLink();
            file.append(pomLink);
        }
    }

    @Override
    public void stop() {
        downloader.shutdown();
        file.close();
    }
}
