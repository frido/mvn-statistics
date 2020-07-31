package frido.mvnrepo.downloader.github;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.fluent.Request;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;

public class GitHubClient {

//    private final HttpClient client;
    private final String token;

    public GitHubClient(String token) {
//        client = HttpClient.newBuilder().build();
        this.token = "token " + token;
    }

    public GithubRepoJson repo(String fullName) {
        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            var response = Request.Get("https://api.github.com/repos/" + fullName).addHeader("Authorization", token).execute().returnContent();
            return toJson(response.asString());
        } catch (IOException e) {
            e.printStackTrace();
            return new GithubRepoJson();
        }

//        try {
//            HttpRequest request = HttpRequest.newBuilder(new URI("https://api.github.com/" + fullName)).header("Authorization", token).timeout(Duration.of(10, ChronoUnit.SECONDS)).build();
//            var response = client.send(request, HttpResponse.BodyHandlers.ofString());
//            return toJson(response.body());
//        } catch (URISyntaxException | IOException | InterruptedException e) {
//            e.printStackTrace();
//            return new GithubRepoJson();
//        }
    }

    private GithubRepoJson toJson(String body) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(body, GithubRepoJson.class);
    }
}
