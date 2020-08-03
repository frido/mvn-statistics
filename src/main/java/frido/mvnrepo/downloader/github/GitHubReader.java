package frido.mvnrepo.downloader.github;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class GitHubReader implements Runnable {

    private final GitHubClient client;
    private final ObjectWriter writer;
    private ScheduledExecutorService schedule;
    private LinkedBlockingQueue<String> queue;

    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
        GitHubReader reportMain = new GitHubReader();
        reportMain.start();
    }

    public GitHubReader() throws IOException {
        schedule = Executors.newScheduledThreadPool(1);
        client = new GitHubClient("7d58eae226e2a6163cf44ad9a650c8f4fb37836e");
        writer = new ObjectWriter("report/github.json", false);
        queue = new LinkedBlockingQueue<String>();
    }

    public void start() throws IOException {
        writer.println("{ \"data\" : [");
        readFile().stream().forEach(fullName -> {
            queue.add(fullName);
        });
        schedule.scheduleAtFixedRate(this, 0, 750, TimeUnit.MILLISECONDS);
    }

//    schedule.shutdown();

    private List<String> readFile() throws IOException {
        return Files.readAllLines(Paths.get("github.list"));
    }

    @Override
    public void run() {
        String fullName = queue.poll();
        if(fullName == null) {
            schedule.shutdown();
            return;
        }
        System.out.println(fullName + " : " + queue.size());
        client.repo(fullName).map(JsonWrapper::new).ifPresent(writer::printPart);
    }
}
