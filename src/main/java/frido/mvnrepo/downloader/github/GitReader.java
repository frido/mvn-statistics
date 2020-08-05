package frido.mvnrepo.downloader.github;

import frido.mvnrepo.downloader.core.io.JsonReader;
import frido.mvnrepo.downloader.core.io.JsonWriter;
import frido.mvnrepo.downloader.core.json.GithubJson;
import frido.mvnrepo.downloader.core.json.GithubPomJson;
import frido.mvnrepo.downloader.core.json.KeyValueGroupJson;
import frido.mvnrepo.downloader.core.json.StatisticsJson;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class GitReader {

    public static void main(String[] args) throws IOException {
        GitReader reportMain = new GitReader();
        reportMain.start();
    }

    public void start() throws IOException {
        StatisticsJson statisticsJson = new JsonReader("data", "statistics.json").read(StatisticsJson.class);

        Map<String, GithubJson> outputMap = new HashMap<>();
        for (KeyValueGroupJson gitRepo : statisticsJson.getScm().getList()) {
            String gitLink = gitRepo.getName().toLowerCase();
            if (gitLink.contains("github.com")) {
                GithubLink githubLink = new GithubLink(gitLink);
                if (githubLink.isValid()) {
                    GithubJson githubJsonItem = outputMap.getOrDefault(githubLink.toString(), new GithubJson(githubLink.toString()));
                    gitRepo.getChilds().stream().map(GithubPomJson::new).forEach(githubJsonItem::add);
                    outputMap.put(githubLink.toString(), githubJsonItem);
                }
            }
        }

        new JsonWriter("data","github.json").write(new JsonWrapper(outputMap.values()));
    }
}
