package frido.mvnrepo.downloader.metadata;

import java.io.IOException;

public class ArtifactMain {

    public static void main(String[] args) throws IOException {
        MetadataReader reader = new MetadataReader();
        reader.start();
    }
}
