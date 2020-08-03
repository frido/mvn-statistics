package frido.mvnrepo.downloader.report;

import frido.mvnrepo.downloader.core.Artifact;
import frido.mvnrepo.downloader.core.json.DataJson;
import frido.mvnrepo.downloader.core.stats.KeyValue;
import frido.mvnrepo.downloader.core.stats.KeyValueGroupList;

import java.util.List;

public class DependencyArtifactReport {

    List<KeyValue> data;
    private KeyValueGroupList output = new KeyValueGroupList();

    public DependencyArtifactReport(List<KeyValue> dependencies) {
        data = dependencies;
        process();
    }

    private void process() {
        for (KeyValue item : data) {
            Artifact artifact = new Artifact(item.getName());
            output.add(artifact.getGroupAndArtifact(), artifact.getVersion(), item.getValue());
        }
    }

    public DataJson report() {
        return new DataJson(output.toJson(Integer.MAX_VALUE, Integer.MAX_VALUE));
    }
}
