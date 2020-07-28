package frido.mvnrepo.downloader.report;

import frido.mvnrepo.downloader.core.Developer;
import frido.mvnrepo.downloader.stats.KeyValue;
import frido.mvnrepo.downloader.stats.KeyValueGroup;
import frido.mvnrepo.downloader.stats.KeyValueGroupList;

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

    public List<KeyValueGroup> getData() {
        return output.getList();
    }
}
