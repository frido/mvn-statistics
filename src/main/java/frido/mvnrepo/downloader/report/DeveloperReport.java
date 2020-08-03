package frido.mvnrepo.downloader.report;

import frido.mvnrepo.downloader.core.Developer;
import frido.mvnrepo.downloader.core.json.DataJson;
import frido.mvnrepo.downloader.core.stats.KeyValue;
import frido.mvnrepo.downloader.core.stats.KeyValueGroupList;

import java.util.List;

public class DeveloperReport {

    List<KeyValue> data;
    private KeyValueGroupList output = new KeyValueGroupList();

    public DeveloperReport(List<KeyValue> ciManagement) {
        this.data = ciManagement;
        process();
    }

    private void process() {
        for (KeyValue item : data) {
            Developer developer = new Developer(item.getName());
            output.add(developer.getEmail(), developer.getName(), item.getValue());
        }
    }

    public DataJson report() {
        return new DataJson(output.toJson(Integer.MAX_VALUE, Integer.MAX_VALUE));
    }
}
