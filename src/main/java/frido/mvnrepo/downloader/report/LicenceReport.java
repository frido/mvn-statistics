package frido.mvnrepo.downloader.report;

import frido.mvnrepo.downloader.stats.KeyValue;
import frido.mvnrepo.downloader.stats.KeyValueGroup;
import frido.mvnrepo.downloader.stats.KeyValueGroupList;

import java.util.List;

public class LicenceReport {

    List<KeyValue> data;
    private KeyValueGroupList output = new KeyValueGroupList();

    public LicenceReport(List<KeyValue> ciManagement) {
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
        if (key.contains("apache")) {
            return "apache";
        }
        if (key.contains("mit")) {
            return "mit";
        }
        if (key.contains("bsd")) {
            return "bsd";
        }
        if (key.contains("apl2")) {
            return "apl2";
        }
        if (key.contains("lgpl")) {
            return "lgpl";
        }
        if (key.contains("gnu")) {
            return "gnu";
        }
        if (key.contains("affero")) {
            return "affero";
        }
        if (key.contains("mozilla")) {
            return "mozilla";
        }
        if (key.contains("isc")) {
            return "isc";
        }
        if (key.contains("agpl")) {
            return "agpl";
        }
        if (key.contains("gpl")) {
            return "gpl";
        }
        if (key.contains("eclipse")) {
            return "eclipse";
        }

        return "other";
    }


    public List<KeyValueGroup> getData() {
        return output.getList();
    }
}
