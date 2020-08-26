package frido.mvnrepo.downloader.core;

import java.net.http.HttpClient;
import java.util.concurrent.*;

public class Downloader {

    private ExecutorService executor;
    private ScheduledExecutorService schedule;
    private HttpClient client;
    private LinkedBlockingQueue<Runnable> queue;
    private ResponseHandler responseHandler;

    public Downloader (int nThreads) {
        queue = new LinkedBlockingQueue<Runnable>();
        executor = new ThreadPoolExecutor(nThreads, nThreads, 0L, TimeUnit.MILLISECONDS, queue);
        schedule = Executors.newScheduledThreadPool(1);
        client = HttpClient.newBuilder().build();
    }

    public void download(Link link) {
        executor.execute(new DownloadTask(client, link, responseHandler));
    }

    public void shutdown() {
        executor.shutdown();
        schedule.shutdown();
    }

    public void registerStopHandler(TickHandler tickHandler, StopHandler stopHandler) {
        schedule.scheduleAtFixedRate(new ScheduleEnd(queue, tickHandler, stopHandler), 0, 1, TimeUnit.SECONDS);
    }

    public void registerResponseHandler(ResponseHandler responseHandler) {
        this.responseHandler = responseHandler;
    }
}
