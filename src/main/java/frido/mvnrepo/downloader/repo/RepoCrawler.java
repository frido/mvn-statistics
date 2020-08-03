package frido.mvnrepo.downloader.repo;

import frido.mvnrepo.downloader.core.*;
import frido.mvnrepo.downloader.core.io.ListWriter;

import java.io.IOException;

public class RepoCrawler implements ResponseHandler, StopHandler {

    private Downloader downloader;
    private ListWriter file;

    public static void main(String[] args) throws IOException {
        var repoLink = "https://repo1.maven.org/maven2/";
        RepoCrawler repoCrawler = new RepoCrawler();
        repoCrawler.start(repoLink);
    }

    public RepoCrawler() throws IOException {
        downloader = new Downloader(10);
        file = new ListWriter("metadata.list");
    }

    public void start(String repoLink) {
        downloader.registerStopHandler(this);
        downloader.registerResponseHandler(this);
        downloader.download(new Link(repoLink));
    }

    public void handleResponse(ResponseBody response) {
        WebPageBody body = new WebPageBody(response);
        if (body.contains("maven-metadata.xml")) {
            file.append(body.getBase() + "maven-metadata.xml");
        } else {
            body.links().filter(l -> l.isDirectory()).forEach(l -> downloader.download(l));
        }
    }

    public void stop() {
        downloader.shutdown();
        file.close();
    }
}
