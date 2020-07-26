package frido.mvnrepo.downloader.stats;

import java.util.LinkedList;

public class ValueList {
    private LinkedList<Long> list = new LinkedList<>();
    private long max = 0;
    private String maxName;


    public void add(String name, long size) {
        synchronized (list) {
            list.add(size);
            if (size > max) {
                max = size;
                maxName = name;
            }
        }
    }

    public long getMax() {
        return max;
    }

    public String getMaxName() {
        return maxName;
    }

    public LinkedList<Long> getList() {
        return list;
    }
}
