package frido.mvnrepo.downloader.core;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.TimeUnit;

public class DownloadTask implements Runnable {
    private final Link link;
    private final HttpClient client;
    private final ResponseHandler handler;

    public DownloadTask(HttpClient client, Link link, ResponseHandler handler) {
        this.client = client;
        this.link = link;
        this.handler = handler;
    }

    @Override
    public void run() {
        try {
            var request = HttpRequest.newBuilder(new URI(link.getUrl())).timeout(Duration.of(10, ChronoUnit.SECONDS)).build();
            var responseFuture = client.sendAsync(request, HttpResponse.BodyHandlers.ofString());
            var response = responseFuture.get(10, TimeUnit.SECONDS);
            var body = new ResponseBody(link, response.body());
            handler.handleResponse(body);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
