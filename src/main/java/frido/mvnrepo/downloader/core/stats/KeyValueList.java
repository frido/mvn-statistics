package frido.mvnrepo.downloader.core.stats;

import frido.mvnrepo.downloader.core.json.DataJson;

import java.util.List;
import java.util.stream.Collectors;

public class KeyValueList {
    private final List<KeyValue> list;

    public KeyValueList(List<KeyValue> list) {
        this.list = list;
    }

    private List<KeyValue> getList() {
        return list;
    }

    private long getSum() {
        return list.stream().mapToLong(KeyValue::getValue).sum();
    }

    public DataJson toJson() {
        return new DataJson(list.stream().sorted().collect(Collectors.toList()));
    }
}
