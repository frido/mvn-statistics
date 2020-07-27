package frido.mvnrepo.downloader.stats;

import java.util.List;

public class KeyValueParent implements Comparable<KeyValueParent>{
    private final int limit;
    private String name;
    private int value;
    private KeyValueMap childs;

    public KeyValueParent(String name, int limit) {
        this.name = name;
        this.value = 0;
        this.limit = limit;
        this.childs = new KeyValueMap(limit);
    }

    public void add(String artifactId, int diff) {
        value = value + diff;
        childs.add(artifactId, diff);
    }

    @Override
    public int compareTo(KeyValueParent o) {
        return o.getValue() - this.value;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    public List<KeyValue> getChilds() {
        return childs.toList();
    }
}
