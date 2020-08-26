package frido.mvnrepo.downloader.core;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class ScheduleEnd implements Runnable {

    private final TickHandler tickHandler;
    private StopHandler stopHandler;
    private AtomicInteger nInactive = new AtomicInteger();
    private LinkedBlockingQueue<Runnable> queue;

    public ScheduleEnd(LinkedBlockingQueue<Runnable> queue, TickHandler tickHandler, StopHandler stopHandler) {
        this.queue = queue;
        this.stopHandler = stopHandler;
        this.tickHandler = tickHandler;
        nInactive.set(0);
    }

    @Override
    public void run() {
        tickHandler.tick(queue);
        if (nInactive.get() > 5) {
            stopHandler.stop();
        }
        refreshInactiveCount();
    }

    private void refreshInactiveCount() {
        if (queue.size() == 0) {
            nInactive.incrementAndGet();
        } else {
            nInactive.set(0);
        }
    }
}
