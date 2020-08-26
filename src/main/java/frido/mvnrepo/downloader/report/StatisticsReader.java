package frido.mvnrepo.downloader.report;

import frido.mvnrepo.downloader.core.Config;
import frido.mvnrepo.downloader.core.io.JsonReader;
import frido.mvnrepo.downloader.core.io.JsonWriter;
import frido.mvnrepo.downloader.core.json.StatisticsJson;
import frido.mvnrepo.downloader.core.stats.KeyValue;
import frido.mvnrepo.downloader.core.stats.KeyValueList;

import java.io.IOException;
import java.util.List;

public class StatisticsReader {

    private final Config config;

    public StatisticsReader(Config config) {
        this.config = config;
    }

    public static void main(String[] args) throws IOException {
        StatisticsReader reportMain = new StatisticsReader(new Config());
        reportMain.start();
    }

    public void start() throws IOException {
        StatisticsJson statisticsJson = new JsonReader(config.getDataFolder(), "statistics.json").read(StatisticsJson.class);

        List<KeyValue> devsAndContributors = statisticsJson.getDevelopers();
        devsAndContributors.addAll(statisticsJson.getContributors());

        String reportFolder = config.getReportFolder();

        new JsonWriter(reportFolder,"ciManagement.json").write(new CiManagementReport(statisticsJson.getCiManagement()).report());
        new JsonWriter(reportFolder,"dependencyGroup.json").write(new DependencyGroupReport(statisticsJson.getDependencies()).report());
        new JsonWriter(reportFolder,"dependencyArtifact.json").write(new DependencyArtifactReport(statisticsJson.getDependencies()).report());
        new JsonWriter(reportFolder,"plugins.json").write(new PluginsReport(statisticsJson.getPlugins()).report());
        new JsonWriter(reportFolder,"reportingPlugins.json").write(new PluginsReport(statisticsJson.getReportingPlugins()).report());
        new JsonWriter(reportFolder,"inceptionYears.json").write(new KeyValueList(statisticsJson.getInceptionYears()).toJson());
        new JsonWriter(reportFolder,"licenses.json").write(new LicenceReport(statisticsJson.getLicenses()).report());
        new JsonWriter(reportFolder,"developers.json").write(new DeveloperReport(devsAndContributors).report());
        new JsonWriter(reportFolder,"issueManagement.json").write(new IssueReport(statisticsJson.getIssueManagement()).report());
        new JsonWriter(reportFolder,"scm.json").write(new ScmReport(statisticsJson.getScm()).report());

        new JsonWriter(reportFolder,"dependenciesCount.json").write(new CountReport(statisticsJson.getDependenciesCount()).report());
        new JsonWriter(reportFolder,"pluginsCount.json").write(new CountReport(statisticsJson.getPluginsCount()).report());
        new JsonWriter(reportFolder,"reportingPluginsCount.json").write(new CountReport(statisticsJson.getReportingPluginsCount()).report());
        new JsonWriter(reportFolder,"licensesCount.json").write(new CountReport(statisticsJson.getLicensesCount()).report());
        new JsonWriter(reportFolder,"developersCount.json").write(new CountReport(statisticsJson.getDevelopersCount()).report());
        new JsonWriter(reportFolder,"contributorsCount.json").write(new CountReport(statisticsJson.getContributorsCount()).report());
        new JsonWriter(reportFolder,"profilesCount.json").write(new CountReport(statisticsJson.getProfilesCount()).report());
        new JsonWriter(reportFolder,"profiles.json").write(new KeyValueList(statisticsJson.getProfiles()).toJson());
    }
}
