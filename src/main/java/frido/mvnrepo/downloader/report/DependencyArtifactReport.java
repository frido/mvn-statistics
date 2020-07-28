package frido.mvnrepo.downloader.report;

import frido.mvnrepo.downloader.core.Artifact;
import frido.mvnrepo.downloader.stats.KeyValue;
import frido.mvnrepo.downloader.stats.KeyValueGroup;
import frido.mvnrepo.downloader.stats.KeyValueGroupList;

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
            output.add(artifact.getArtifact(), artifact.getArtifact(), item.getValue());
        }
    }

    public List<KeyValueGroup> getData() {
        return output.getList();
    }
}
