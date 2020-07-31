package frido.mvnrepo.downloader.github;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GithubLink {

    private static Pattern linkPattern = Pattern.compile("github\\.com[\\/\\:](.*?)\\/(.*)");
    private static Pattern validPattern = Pattern.compile("[${}]");


    private final String link;
    private String owner;
    private String repo;

    public GithubLink(String link) {
        this.link = link;
        String txt = link;
        if (txt.endsWith(".git")) {
            txt = txt.substring(0, txt.length() - 4);
        }
        Matcher matcher = linkPattern.matcher(txt);
        if(matcher.find()) {
            setOwnerIfValid(matcher.group(1));
            setRepoIfValid(matcher.group(2));
        }
    }

    public boolean isValid() {
        return owner != null && repo != null;
    }

    private void setRepoIfValid(String group) {
        if (valid(group)) {
            repo = group;
        }
    }

    private void setOwnerIfValid(String group) {
        if (valid(group)) {
            owner = group;
        }
    }

    private boolean valid(String group) {
        if (group == null) {
            return false;
        }
        boolean b = validPattern.matcher(group).find();
        return !b;
    }

    @Override
    public String toString() {
        return owner + "/" + repo;
    }
}
