package frido.mvnrepo.downloader.core;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class ScheduleEnd implements Runnable {

    private StopHandler handler;
    private AtomicInteger nInactive = new AtomicInteger();
    private LinkedBlockingQueue<Runnable> queue;

    public ScheduleEnd(LinkedBlockingQueue<Runnable> queue, StopHandler handler) {
        this.queue = queue;
        this.handler = handler;
        nInactive.set(0);
    }

    @Override
    public void run() {
        System.out.printf("%d\n", queue.size());
        if (nInactive.get() > 5) {
            handler.stop();
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
