package frido.mvnrepo.downloader.github;

import frido.mvnrepo.downloader.core.stats.KeyValue;

public class GithubPomJson {
    private String pomLink;
    private String groupId;
    private String artifactId;

    public GithubPomJson() {
    }

    public GithubPomJson(KeyValue s) {
        // TODO: continue
        String[] parts = s.getName().split("@@@");
        pomLink = parts[0];
        String[] subParts = parts[1].split(":");
        groupId = subParts[0];
        artifactId = subParts[1];
    }

    public String getPomLink() {
        return pomLink;
    }

    public void setPomLink(String pomLink) {
        this.pomLink = pomLink;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getArtifactId() {
        return artifactId;
    }

    public void setArtifactId(String artifactId) {
        this.artifactId = artifactId;
    }
}
