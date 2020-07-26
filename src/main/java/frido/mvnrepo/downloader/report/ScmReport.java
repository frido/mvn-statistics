package frido.mvnrepo.downloader.report;

import frido.mvnrepo.downloader.stats.KeyValue;
import frido.mvnrepo.downloader.stats.KeyValueParent;
import frido.mvnrepo.downloader.stats.KeyValueParentList;

import java.util.List;

public class ScmReport {

    List<KeyValue> data;
    private KeyValueParentList output = new KeyValueParentList(Integer.MAX_VALUE);

    public ScmReport(List<KeyValue> ciManagement) {
        this.data = ciManagement;
        process();
    }

    private void process() {
        for (KeyValue item : data) {
            output.add(getGroupId(item.getName()), item.getName(), item.getValue());
        }
    }

    private String getGroupId(String name) {
        String key = name.toLowerCase();
        if (key.contains("github.com")) {
            return "github.com";
        }
        if (key.contains("eclipse.org")) {
            return "git.eclipse.org";
        }
        if (key.contains(".apache.org")) {
            return "apache.org";
        }
        if (key.contains("opendaylight.org")) {
            return "opendaylight.org";
        }

        return "other";
    }


    public List<KeyValueParent> getData() {
        return output.toList();
    }
}
