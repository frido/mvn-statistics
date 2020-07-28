package frido.mvnrepo.downloader.report;

import frido.mvnrepo.downloader.core.Artifact;
import frido.mvnrepo.downloader.stats.KeyValue;
import frido.mvnrepo.downloader.stats.KeyValueGroup;
import frido.mvnrepo.downloader.stats.KeyValueGroupList;

import java.util.List;

public class DependencyGroupReport {

    List<KeyValue> data;
    private KeyValueGroupList output;

    public DependencyGroupReport(List<KeyValue> dependencies, int limit) {
        data = dependencies;
        output = new KeyValueGroupList(limit); // TODO: set parameter to limit child list
        process();
    }

    private void process() {
        for (KeyValue item : data) {
            Artifact artifact = new Artifact(item.getName());
            output.add(artifact.getGroup(), artifact.getArtifact(), item.getValue());
        }
    }

    public List<KeyValueGroup> getData() {
        return output.getList();
    }

}
