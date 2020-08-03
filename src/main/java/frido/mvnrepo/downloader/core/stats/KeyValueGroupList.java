package frido.mvnrepo.downloader.core.stats;

import frido.mvnrepo.downloader.core.json.KeyValueGroupJson;
import frido.mvnrepo.downloader.core.json.KeyValueGroupListJson;

import java.util.*;
import java.util.stream.Collectors;

public class KeyValueGroupList {

    private Map<String, KeyValueGroup> map = new HashMap<>();

    public void add(String groupId, String artifactId, int diff) {
        if (groupId != null) {
            String key = groupId.toLowerCase();
            KeyValueGroup value = map.getOrDefault(key, new KeyValueGroup(key));
            value.add(artifactId, diff);
            map.put(key, value);
        }
    }

    public KeyValueGroupListJson toJson(int groupLimit, int childsLimit) {
        KeyValueGroupListJson json = new KeyValueGroupListJson();
        json.setSum(getSum());
        json.setList(getList(groupLimit, childsLimit));
        return json;
    }

    private List<KeyValueGroupJson> getList(int groupLimit, int childsLimit) {
        List<KeyValueGroup> sorted = map.values().stream().sorted().collect(Collectors.toList());
        return sorted.stream()
                .limit(groupLimit)
                .map(x -> x.toJson(childsLimit))
                .collect(Collectors.toList());
    }

    private int getSum() {
        return map.values().stream().mapToInt(KeyValueGroup::getValue).sum();
    }

}
