package frido.mvnrepo.downloader.github;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import frido.mvnrepo.downloader.core.json.*;
import frido.mvnrepo.downloader.core.stats.KeyValue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class GitReader {

    public static void main(String[] args) throws IOException {
        GitReader reportMain = new GitReader();
        reportMain.start();
    }

    public void start() throws IOException {
        List<GithubJson> output = new ArrayList<>();
        StatisticsJson statisticsJson = readFile();
        for (KeyValueGroupJson gitRepo : statisticsJson.getScm().getList()) {
            String gitLink = gitRepo.getName().toLowerCase();
            if (gitLink.contains("github.com")) {
                GithubLink githubLink = new GithubLink(gitLink);
                if (githubLink.isValid()) {
                    GithubJson githubJsonItem = new GithubJson();
                    githubJsonItem.setFullName(githubLink.toString());
                    githubJsonItem.setPoms(gitRepo.getChilds().stream().map(s -> new GithubPomJson(s)).collect(Collectors.toList()));
                    output.add(githubJsonItem);
                }
            }
        }
        print("github.json", new JsonWrapper(output));
    }

    private List<KeyValue> getGithub(KeyValueGroupListJson report) {
        for (KeyValueGroupJson x : report.getList()) {
            if (x.getName().equals("github.com")) {
                return x.getChilds();
            }
        }
        return Collections.EMPTY_LIST;
    }

    // TODO: Object Writer
    private void print(String fileName, Object object) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.writeValue(Paths.get(fileName).toFile(), object);
    }

    // TODO: Object Reader
    private StatisticsJson readFile() throws IOException {
        String inputString = Files.readString(Paths.get("statistics.json"));
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        return mapper.readValue(inputString, StatisticsJson.class);
    }
}
