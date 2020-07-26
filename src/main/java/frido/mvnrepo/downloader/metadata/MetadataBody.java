package frido.mvnrepo.downloader.metadata;

import frido.mvnrepo.downloader.core.ResponseBody;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class MetadataBody extends ResponseBody {

    private static String LINK_PATTERN = "<a href=\"(.*?)\"";
    private static Pattern p = Pattern.compile(LINK_PATTERN);

    private String group;
    private String artifact;
    private String version;

    public MetadataBody(ResponseBody responseBody) {
        super(responseBody.getBase(), responseBody.getBody());
        parseBody();
    }

    private void parseBody() {
        try {
            DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            org.w3c.dom.Document doc = docBuilder
                    .parse(new InputSource(new ByteArrayInputStream(body.getBytes("utf-8"))));
            doc.getDocumentElement().normalize();

            NodeList metadata = doc.getElementsByTagName("metadata");
            if (metadata.getLength() > 0) {
                Element elementM = (Element) metadata.item(0);
                group = getContent(elementM, "groupId");
                artifact = getContent(elementM, "artifactId");

                NodeList versioning = doc.getElementsByTagName("versioning");
                if (versioning.getLength() > 0) {
                    Element elementV = (Element) versioning.item(0);
                    String latest = getContent(elementV, "latest");
                    String release = getContent(elementV, "release");

                    List<String> versions = new ArrayList<>();
                    NodeList versionsElement = elementV.getElementsByTagName("version");
                    for (int j = 0; j < versionsElement.getLength(); j++) {
                        Element versionElement = (Element) versionsElement.item(j);
                        version = versionElement.getTextContent();
                    }

                    if (version == null) {
                        version = latest;
                    }

                    if (version == null) {
                        version = release;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getContent(Element element, String tag) {
        if (element.getElementsByTagName(tag).getLength() > 0) {
            return element.getElementsByTagName(tag).item(0).getTextContent();
        }
        return null;
    }

    @Override
    public String toString() {
        return "MetadataBody{" +
                "base=" + base +
                ", group='" + group + '\'' +
                ", artifact='" + artifact + '\'' +
                ", version='" + version + '\'' +
                '}';
    }

    public String getPomLink() {
        String repo = base.getUrl().replace("maven-metadata.xml", "");
        return repo + version + "/" + artifact + "-" + version + ".pom";
    }

    public boolean isValid() {
        return group != null && artifact != null && version != null;
    }
}
