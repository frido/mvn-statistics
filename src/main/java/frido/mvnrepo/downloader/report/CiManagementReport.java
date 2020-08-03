package frido.mvnrepo.downloader.report;

import frido.mvnrepo.downloader.core.json.DataJson;
import frido.mvnrepo.downloader.core.stats.KeyValue;
import frido.mvnrepo.downloader.core.stats.KeyValueGroupList;

import java.util.List;

public class CiManagementReport {

    List<KeyValue> data;
    private KeyValueGroupList output = new KeyValueGroupList();

    public CiManagementReport(List<KeyValue> ciManagement) {
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
        if (key.contains("travis")) {
            return "travis";
        }
        if (key.contains("jenkins")) {
            return "jenkins";
        }
        if (key.contains("teamcity")) {
            return "teamcity";
        }
        if (key.contains("hudson")) {
            return "hudson";
        }
        if (key.contains("bamboo")) {
            return "bamboo";
        }
        if (key.contains("circle")) {
            return "circle";
        }
        if (key.contains("github")) {
            return "github";
        }
        if (key.contains("continuum")) {
            return "continuum";
        }
        if (key.contains("rultor")) {
            return "rultor";
        }
        if (key.contains("gitlab")) {
            return "gitlab";
        }
        return "other";
    }


    public DataJson report() {
        return new DataJson(output.toJson(Integer.MAX_VALUE, Integer.MAX_VALUE));
    }
}
