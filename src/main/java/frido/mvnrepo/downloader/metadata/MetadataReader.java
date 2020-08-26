package frido.mvnrepo.downloader.metadata;

import frido.mvnrepo.downloader.core.*;
import frido.mvnrepo.downloader.core.io.ListReader;
import frido.mvnrepo.downloader.core.io.ListWriter;

import java.io.IOException;
import java.util.concurrent.LinkedBlockingQueue;

public class MetadataReader implements ResponseHandler, StopHandler, TickHandler {

    private final Config config;
    private ListWriter file;
    private Downloader downloader;

    public static void main(String[] args) throws IOException {
        MetadataReader reader = new MetadataReader(new Config());
        reader.start();
    }

    public MetadataReader(Config config) throws IOException {
        this.config = config;
        file = new ListWriter(config.getDataFolder(), "pom.txt");
        downloader = new Downloader(10);
        downloader.registerStopHandler(this, this);
        downloader.registerResponseHandler(this);
    }

    public void start() {
        new ListReader(config.getDataFolder(), "metadata.list")
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

    @Override
    public void tick(LinkedBlockingQueue<Runnable> queue) {
        System.out.printf("%d\n", queue.size());
    }
}
