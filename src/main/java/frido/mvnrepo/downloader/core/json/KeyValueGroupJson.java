package frido.mvnrepo.downloader.core.json;

import frido.mvnrepo.downloader.core.stats.KeyValue;

import java.util.List;

// TODO: use JSON in all storing
public class KeyValueGroupJson {
    private String name;
    private int value;
    private List<KeyValue> childs;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public List<KeyValue> getChilds() {
        return childs;
    }

    public void setChilds(List<KeyValue> childs) {
        this.childs = childs;
    }
}
