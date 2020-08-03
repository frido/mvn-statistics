package frido.mvnrepo.downloader.report;

import frido.mvnrepo.downloader.core.json.GithubRepoJson;

import java.util.List;

public class DataWrapper {
    private List<GithubRepoJson> data;

    public List<GithubRepoJson> getData() {
        return data;
    }

    public void setData(List<GithubRepoJson> data) {
        this.data = data;
    }
}
