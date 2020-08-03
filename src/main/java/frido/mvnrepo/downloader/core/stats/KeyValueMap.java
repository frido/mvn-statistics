package frido.mvnrepo.downloader.core.stats;

import java.util.*;

public class KeyValueMap {

    private Map<String, KeyValue> map = new HashMap<>();

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
        ArrayList<KeyValue> list = new ArrayList<>(map.values());
        Collections.sort(list);
        return list;
    }
}
