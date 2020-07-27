package frido.mvnrepo.downloader.stats;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class KeyValueParentList {

    private final int limit;
    private Map<String, KeyValueParent> map = new HashMap<>();

    public KeyValueParentList(int limit) {
        this.limit = limit;
    }

    public KeyValueParentList() {
        this(Integer.MAX_VALUE);
    }

    public void add(String groupId, String artifactId, int diff) {
        if (groupId != null) {
            String key = groupId.toLowerCase();
            KeyValueParent value = map.getOrDefault(key, new KeyValueParent(key, limit));
            value.add(artifactId, diff);
            map.put(key, value);
        }
    }

    public List<KeyValueParent> getList() {
        List<KeyValueParent> out = map.values().stream().sorted().limit(limit).collect(Collectors.toList());
        KeyValueParent otherKeyValue = new KeyValueParent("other", limit);
        map.values().stream().sorted().skip(limit).forEach(x -> otherKeyValue.add(x.getName(), x.getValue()));
        out.add(otherKeyValue);
        return out;
    }

    public long getSum() {
        return map.values().stream().mapToLong(KeyValueParent::getValue).sum();
    }

}
