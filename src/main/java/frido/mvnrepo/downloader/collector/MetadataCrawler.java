package frido.mvnrepo.downloader.collector;

import frido.mvnrepo.downloader.core.Link;
import frido.mvnrepo.downloader.core.ResponseBody;
import frido.mvnrepo.downloader.core.Downloader;
import frido.mvnrepo.downloader.core.FileLogger;
import frido.mvnrepo.downloader.core.ResponseHandler;
import frido.mvnrepo.downloader.core.StopHandler;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

public class MetadataCrawler implements ResponseHandler, StopHandler {

    private Downloader downloader;
    private FileLogger file;
    private AtomicInteger founded = new AtomicInteger();

    public MetadataCrawler() throws IOException {
        downloader = new Downloader(10);
        this.file = new FileLogger("metadata.txt");
        this.founded.set(0);
    }

    public void start(String repoLink) {
        downloader.registerStopHandler(this);
        downloader.download(new Link(repoLink), this);
    }

    public void handleResponse(ResponseBody response) {
        WebPageBody body = new WebPageBody(response);
        if (body.contains("maven-metadata.xml")) {
            file.append(body.getBase() + "maven-metadata.xml");
            this.founded.incrementAndGet();
        } else {
            body.links().filter(l -> l.isDirectory()).forEach(l -> downloader.download(l, this));
        }
    }

    public void stop() {
        downloader.shutdown();
        file.close();
    }
}
