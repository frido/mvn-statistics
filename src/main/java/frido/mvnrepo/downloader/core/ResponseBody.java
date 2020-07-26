package frido.mvnrepo.downloader.core;

public class ResponseBody {

    protected final Link base;
    protected final String body;

    public ResponseBody(Link base, String body) {
        this.base = base;
        this.body = body;
    }

    public Link getBase() {
        return base;
    }

    public String getBody() {
        return body;
    }

    @Override
    public String toString() {
        return "ResponseBody{" +
                "base=" + base +
                '}';
    }
}
