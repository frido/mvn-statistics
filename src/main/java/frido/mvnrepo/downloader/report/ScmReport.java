package frido.mvnrepo.downloader.report;

import frido.mvnrepo.downloader.core.json.DataJson;
import frido.mvnrepo.downloader.core.json.KeyValueGroupJson;
import frido.mvnrepo.downloader.core.json.KeyValueGroupListJson;
import frido.mvnrepo.downloader.core.stats.KeyValueGroupList;

public class ScmReport {

    KeyValueGroupListJson data;
    private KeyValueGroupList output = new KeyValueGroupList();

    public ScmReport(KeyValueGroupListJson scm) {
        this.data = scm;
        process();
    }

    private void process() {
        for (KeyValueGroupJson item : data.getList()) {
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


    public DataJson report() {
        return new DataJson(output.toJson(Integer.MAX_VALUE, Integer.MAX_VALUE));
    }
}
