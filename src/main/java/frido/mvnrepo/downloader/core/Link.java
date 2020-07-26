package frido.mvnrepo.downloader.core;

public class Link {
    private final String url;

    public Link(String link) {
        this.url = link;
    }

    public Link(String base, String link) {
        if (link.startsWith("https://") || link.startsWith("http://")) {
            this.url = link;
        } else{
            this.url = base + link;
        }
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return url;
    }

    public boolean isDirectory() {
        return url.endsWith("/");
    }
}
