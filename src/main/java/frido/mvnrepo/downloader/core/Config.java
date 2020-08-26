package frido.mvnrepo.downloader.core;

public class Config {
    private String dataFolder;
    private String reportFolder;

    public Config() {
        dataFolder = "data";
        reportFolder = "report";
    }

    public String getDataFolder() {
        return dataFolder;
    }

    public String getReportFolder() {
        return reportFolder;
    }
}
