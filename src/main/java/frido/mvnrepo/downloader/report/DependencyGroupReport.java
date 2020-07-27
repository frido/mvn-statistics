package frido.mvnrepo.downloader.report;

import frido.mvnrepo.downloader.core.Artifact;
import frido.mvnrepo.downloader.stats.KeyValue;
import frido.mvnrepo.downloader.stats.KeyValueParent;
import frido.mvnrepo.downloader.stats.KeyValueParentList;

import java.util.List;

public class DependencyGroupReport {

    List<KeyValue> data;
    private KeyValueParentList output;

    public DependencyGroupReport(List<KeyValue> dependencies, int limit) {
        data = dependencies;
        output = new KeyValueParentList(limit); // TODO: set parameter to limit child list
        process();
    }

    private void process() {
        for (KeyValue item : data) {
            Artifact artifact = new Artifact(item.getName());
            output.add(artifact.getGroup(), artifact.getArtifact(), item.getValue());
        }
    }

    public List<KeyValueParent> getData() {
        return output.getList();
    }

}
