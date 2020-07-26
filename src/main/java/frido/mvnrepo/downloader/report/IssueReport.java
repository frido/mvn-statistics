package frido.mvnrepo.downloader.report;

import frido.mvnrepo.downloader.stats.KeyValue;
import frido.mvnrepo.downloader.stats.KeyValueParent;
import frido.mvnrepo.downloader.stats.KeyValueParentList;

import java.util.List;

public class IssueReport {

    List<KeyValue> data;
    private KeyValueParentList output = new KeyValueParentList();

    public IssueReport(List<KeyValue> ciManagement) {
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
        if (key.contains("github")) {
            return "github";
        }
        if (key.contains("jira")) {
            return "jira";
        }
        if (key.contains("bitbucket")) {
            return "bitbucket";
        }
        if (key.contains("redmine")) {
            return "redmine";
        }
        if (key.contains("sourceforge")) {
            return "sourceforge";
        }
        if (key.contains("google")) {
            return "google";
        }
        if (key.contains("gitlab")) {
            return "gitlab";
        }
        if (key.contains("bugzilla")) {
            return "bugzilla";
        }
        if (key.contains("trac")) {
            return "trac";
        }
        if (key.contains("gitee")) {
            return "gitee";
        }
        if (key.contains("gitea")) {
            return "gitea";
        }

        return "other";
    }


    public List<KeyValueParent> getData() {
        return output.toList();
    }
}
