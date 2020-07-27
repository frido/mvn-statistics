package frido.mvnrepo.downloader.stats;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class KeyValueMap {

    private final int limit;
    private Map<String, KeyValue> map = new HashMap<>();

    public KeyValueMap(int limit) {
        this.limit = limit;
    }

    public KeyValueMap() {
        this(Integer.MAX_VALUE);
    }

    public void add(String index) {
        add(index, 1);
    }

    public void add(String index, int diff) {
        if (index != null) {
            synchronized (map) {
                String key = index.toLowerCase();
                KeyValue value = map.getOrDefault(key, new KeyValue(key, 0));
                value.add(diff);
                map.put(key, value);
            }
        }
    }

    public List<KeyValue> toList() {
        return map.values().stream().sorted().limit(limit).collect(Collectors.toUnmodifiableList());
    }
}
