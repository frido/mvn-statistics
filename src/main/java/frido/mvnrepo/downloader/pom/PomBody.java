package frido.mvnrepo.downloader.pom;

import frido.mvnrepo.downloader.core.ResponseBody;
import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.apache.maven.shared.utils.xml.pull.XmlPullParserException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.regex.Pattern;

public class PomBody extends ResponseBody {

    private static String LINK_PATTERN = "<a href=\"(.*?)\"";
    private static Pattern p = Pattern.compile(LINK_PATTERN);

    private Model model;

    public PomBody(ResponseBody responseBody) {
        super(responseBody.getBase(), responseBody.getBody());
        parseBody();
    }

    private void parseBody() {
        MavenXpp3Reader reader = new MavenXpp3Reader();
        try {
            model = reader.read(new ByteArrayInputStream(body.getBytes(StandardCharsets.UTF_8)));
        } catch (IOException | XmlPullParserException | org.codehaus.plexus.util.xml.pull.XmlPullParserException e) {
            Exception ex = new Exception(base.getUrl(), e);
            // TODO: ignored?
//            ex.printStackTrace();
        }
    }

    public Optional<Model> getModel() {
        return Optional.ofNullable(model);
    }
}
