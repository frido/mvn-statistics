package frido.mvnrepo.downloader.stats;

import java.util.Map;

public class KeyValue implements Comparable<KeyValue> {
    private String name;
    private Integer value;

    public KeyValue() {
    }

    public KeyValue(String name, Integer value) {
        this.name = name;
        this.value = value;
    }

    public KeyValue(Map.Entry<String, Integer> s) {
        this.name = s.getKey();
        this.value = s.getValue();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public void add(int diff) {
        value = value + diff;
    }

    @Override
    public int compareTo(KeyValue o) {
        return o.getValue() - this.value;
    }

    @Override
    public String toString() {
        return "KeyValue{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }
}
