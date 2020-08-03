package frido.mvnrepo.downloader.report;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import frido.mvnrepo.downloader.core.io.JsonReader;
import frido.mvnrepo.downloader.core.io.JsonWriter;
import frido.mvnrepo.downloader.core.json.StatisticsJson;
import frido.mvnrepo.downloader.core.stats.KeyValue;
import frido.mvnrepo.downloader.core.stats.KeyValueList;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

public class StatisticsReader {

    public static void main(String[] args) throws IOException {
        StatisticsReader reportMain = new StatisticsReader();
        reportMain.start();
    }

    public void start() throws IOException {
        StatisticsJson statisticsJson = new JsonReader("statistics.json").read(StatisticsJson.class);
        createReportDir();

        List<KeyValue> devsAndContributors = statisticsJson.getDevelopers();
        devsAndContributors.addAll(statisticsJson.getContributors());

        new JsonWriter("ciManagement.json").write(new CiManagementReport(statisticsJson.getCiManagement()).report());
        new JsonWriter("dependencyGroup.json").write(new DependencyGroupReport(statisticsJson.getDependencies()).report());
        new JsonWriter("dependencyArtifact.json").write(new DependencyArtifactReport(statisticsJson.getDependencies()).report());
        new JsonWriter("plugins.json").write(new PluginsReport(statisticsJson.getPlugins()).report());
        new JsonWriter("reportingPlugins.json").write(new PluginsReport(statisticsJson.getReportingPlugins()).report());
        new JsonWriter("inceptionYears.json").write(new KeyValueList(statisticsJson.getInceptionYears()).toJson());
        new JsonWriter("licenses.json").write(new LicenceReport(statisticsJson.getLicenses()).report());
        new JsonWriter("developers.json").write(new DeveloperReport(devsAndContributors).report());
        new JsonWriter("issueManagement.json").write(new IssueReport(statisticsJson.getIssueManagement()).report());
        new JsonWriter("scm.json").write(new ScmReport(statisticsJson.getScm()).report());

        new JsonWriter("dependenciesCount.json").write(new CountReport(statisticsJson.getDependenciesCount()).report());
        new JsonWriter("pluginsCount.json").write(new CountReport(statisticsJson.getPluginsCount()).report());
        new JsonWriter("reportingPluginsCount.json").write(new CountReport(statisticsJson.getReportingPluginsCount()).report());
        new JsonWriter("licensesCount.json").write(new CountReport(statisticsJson.getLicensesCount()).report());
        new JsonWriter("developersCount.json").write(new CountReport(statisticsJson.getDevelopersCount()).report());
        new JsonWriter("contributorsCount.json").write(new CountReport(statisticsJson.getContributorsCount()).report());
        new JsonWriter("profilesCount.json").write(new CountReport(statisticsJson.getProfilesCount()).report());
        new JsonWriter("profiles.json").write(new KeyValueList(statisticsJson.getProfiles()).toJson());
    }

    private void createReportDir() {
        Paths.get("report").toFile().mkdir();
    }

    private void print(String fileName, Object object) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        String base = "report";
        mapper.writeValue(Paths.get(base, fileName).toFile(), object);
    }
}
