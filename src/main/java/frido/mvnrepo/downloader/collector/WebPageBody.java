package frido.mvnrepo.downloader.collector;

import frido.mvnrepo.downloader.core.Link;
import frido.mvnrepo.downloader.core.ResponseBody;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class WebPageBody extends ResponseBody {

    private static String LINK_PATTERN = "<a href=\"(.*?)\"";
    private static Pattern p = Pattern.compile(LINK_PATTERN);

    private List<Link> links;

    public WebPageBody(ResponseBody responseBody) {
        super(responseBody.getBase(), responseBody.getBody());
        parseLinks();
    }

    public Stream<Link> links() {
        return links.stream();
    }

    public boolean contains(String str) {
        return links.stream().anyMatch(x -> x.getUrl().endsWith(str));
    }

    private List<Link> parseLinks() {
        links = new LinkedList<>();
        Matcher m = p.matcher(body);
        while (m.find()) {
            String link = m.group(1);
            if (isValid(link)) {
                links.add(new Link(base.getUrl(), link));
            }
        }
        return links;
    }

    private boolean isValid(String link) {
        return link != null && !link.isEmpty() && !link.startsWith(".");
    }


}
