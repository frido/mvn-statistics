package frido.mvnrepo.downloader.core.stats;

import frido.mvnrepo.downloader.core.json.KeyValueGroupJson;

import java.util.stream.Collectors;

public class KeyValueGroup implements Comparable<KeyValueGroup>{
    private String name;
    private int value;
    private KeyValueMap childs;

    public KeyValueGroup(String name) {
        this.name = name;
        this.value = 0;
        this.childs = new KeyValueMap();
    }

    public void add(String artifactId, int diff) {
        value = value + diff;
        childs.add(artifactId, diff);
    }

    public KeyValueGroupJson toJson(int childsLimit) {
        KeyValueGroupJson json = new KeyValueGroupJson();
        json.setName(name);
        json.setValue(value);
        json.setChilds(childs.toList().stream().limit(childsLimit).collect(Collectors.toList()));
        return json;
    }

    public int getValue() {
        return value;
    }

    @Override
    public int compareTo(KeyValueGroup o) {
        return o.getValue() - this.value;
    }
}
