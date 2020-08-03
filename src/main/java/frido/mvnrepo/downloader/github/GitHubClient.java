package frido.mvnrepo.downloader.github;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import frido.mvnrepo.downloader.core.json.GithubRepoJson;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

public class GitHubClient {

    private final HttpClient client;
    private final String token;

    public GitHubClient(String token) {
        client = HttpClient.newBuilder().build();
        this.token = "token " + token;
    }

    public Optional<GithubRepoJson> repo(String fullName) {
        try {
            HttpRequest request = HttpRequest.newBuilder(new URI("https://api.github.com/repos/" + fullName)).header("Authorization", token).timeout(Duration.of(10, ChronoUnit.SECONDS)).build();
            var response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return Optional.ofNullable(toJson(response.body()));
        } catch (URISyntaxException | IOException | InterruptedException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    private GithubRepoJson toJson(String body) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(body, GithubRepoJson.class);
    }
}
