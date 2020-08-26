package frido.mvnrepo.downloader.core;

import java.util.concurrent.LinkedBlockingQueue;

public interface TickHandler {
    void tick(LinkedBlockingQueue<Runnable> queue);
}
