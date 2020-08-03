package frido.mvnrepo.downloader.core.json;

import frido.mvnrepo.downloader.core.stats.KeyValueGroupList;

import java.util.List;
import java.util.stream.Collectors;

public class KeyValueGroupListJson {
    private List<KeyValueGroupJson> list;
    private int sum;

    public KeyValueGroupListJson() {
    }

    public List<KeyValueGroupJson> getList() {
        return list;
    }

    public void setList(List<KeyValueGroupJson> list) {
        this.list = list;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }
}
