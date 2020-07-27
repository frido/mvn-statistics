package frido.mvnrepo.downloader.report;

import frido.mvnrepo.downloader.core.Developer;
import frido.mvnrepo.downloader.stats.KeyValue;
import frido.mvnrepo.downloader.stats.KeyValueParent;
import frido.mvnrepo.downloader.stats.KeyValueParentList;

import java.util.List;

public class DeveloperReport {

    List<KeyValue> data;
    private KeyValueParentList output = new KeyValueParentList();

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

    public List<KeyValueParent> getData() {
        return output.getList();
    }
}
