package frido.mvnrepo.downloader.report;

import frido.mvnrepo.downloader.core.Artifact;
import frido.mvnrepo.downloader.stats.KeyValue;
import frido.mvnrepo.downloader.stats.KeyValueMap;

import java.util.List;

public class PluginsReport {

    List<KeyValue> data;
    private KeyValueMap output = new KeyValueMap();

    public PluginsReport(List<KeyValue> plugins) {
        data = plugins;
        process();
    }

    private void process() {
        for (KeyValue item : data) {
            Artifact artifact = new Artifact(item.getName());
            output.add(artifact.getGroupAndArtifact(), item.getValue());
        }
    }

    public List<KeyValue> getData() {
        return output.toList();
    }
}
