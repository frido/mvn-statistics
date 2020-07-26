package frido.mvnrepo.downloader.collector;

import java.io.IOException;

public class MetadataMain {
    public static void main(String[] args) throws IOException {
        var repoLink = "https://repo1.maven.org/maven2/";
        MetadataCrawler metadataCrawler = new MetadataCrawler();
        metadataCrawler.start(repoLink);
    }
}
