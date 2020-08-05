package frido.mvnrepo.downloader.core.json;

import java.util.ArrayList;
import java.util.List;

public class GithubJson {
    private String fullName;
    private List<GithubPomJson> poms;

    public GithubJson() {
        poms = new ArrayList<>();
    }

    public GithubJson(String name) {
        this();
        fullName = name;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public List<GithubPomJson> getPoms() {
        return poms;
    }

    public void setPoms(List<GithubPomJson> poms) {
        this.poms = poms;
    }

    public void add(GithubPomJson newPom) {
        poms.add(newPom);
    }
}
