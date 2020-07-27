package frido.mvnrepo.downloader.stats;

import java.util.List;

public class KeyValueList {
    private final List<KeyValue> list;

    public KeyValueList(List<KeyValue> list) {
        this.list = list;
    }

    public List<KeyValue> getList() {
        return list;
    }

    public long getSum() {
        return list.stream().mapToLong(KeyValue::getValue).sum();
    }
}
