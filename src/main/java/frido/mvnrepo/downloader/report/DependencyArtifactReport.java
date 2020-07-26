package frido.mvnrepo.downloader.report;

import frido.mvnrepo.downloader.core.Artifact;
import frido.mvnrepo.downloader.stats.KeyValue;
import frido.mvnrepo.downloader.stats.KeyValueParent;
import frido.mvnrepo.downloader.stats.KeyValueParentList;

import java.util.List;

public class DependencyArtifactReport {

    List<KeyValue> data;
    private KeyValueParentList output = new KeyValueParentList();

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

    public List<KeyValueParent> getData() {
        return output.toList();
    }
}
