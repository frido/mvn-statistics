package frido.mvnrepo.downloader.core;

public class Artifact {

    private String id;
    private String group;
    private String artifact;
    private String version;

    public Artifact(String id) {
        int first = id.indexOf(":");
        int second = id.lastIndexOf(":");
        group = validate(id.substring(0, first));
        if (first == second) {
            artifact = validate(id.substring(second + 1));
            version = "null";
        } else {
            artifact = validate(id.substring(first + 1, second));
            version = validate(id.substring(second + 1));
        }
    }

    private String validate(String str) {
        if (str.isEmpty()) {
            return "null";
        }
        if (str.startsWith("${")) {
            return "null";
        }
        return str;
    }

    public String getGroup() {
        return group;
    }

    public String getArtifact() {
        return artifact;
    }

    public String getVersion() {
        return version;
    }

    @Override
    public String toString() {
        return "Artifact{" +
                "group='" + group + '\'' +
                ", artifact='" + artifact + '\'' +
                ", version='" + version + '\'' +
                '}';
    }

    public String getGroupAndArtifact() {
        return group + ":" + artifact;
    }
}
