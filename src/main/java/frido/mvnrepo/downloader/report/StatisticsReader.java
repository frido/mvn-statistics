package frido.mvnrepo.downloader.report;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import frido.mvnrepo.downloader.stats.StatisticsJson;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class StatisticsReader {

    private Report report;

    public static void main(String[] args) throws IOException {
        StatisticsReader reportMain = new StatisticsReader();
        reportMain.start();
    }

    public void start() throws IOException {
        StatisticsJson statisticsJson = readFile();
        report = new Report(statisticsJson);
        createReportDir();
        print("ciManagement.json", report.ciManagement());
        print("dependencyGroup.json", report.dependencyGroup());
        print("dependencyArtifact.json", report.dependencyArtifact());
        print("plugins.json", report.plugins());
        print("reportingPlugins.json", report.reportingPlugins());
        print("inceptionYears.json", statisticsJson.getInceptionYears());
        print("licenses.json", report.getLicenses());
        print("developers.json", report.getDevelopers());
        print("issueManagement.json", report.getIssues());
        print("scm.json", report.getScm());
        print("dependenciesCount.json", new CountReport(statisticsJson.getDependenciesCount()));
        print("pluginsCount.json", new CountReport(statisticsJson.getPluginsCount()));
        print("reportingPluginsCount.json", new CountReport(statisticsJson.getReportingPluginsCount()));
        print("licensesCount.json", new CountReport(statisticsJson.getLicensesCount()));
        print("developersCount.json", new CountReport(statisticsJson.getDevelopersCount()));
        print("contributorsCount.json", new CountReport(statisticsJson.getContributorsCount()));
        print("profilesCount.json", new CountReport(statisticsJson.getProfilesCount()));
        print("profiles.json", statisticsJson.getProfiles());

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

    private StatisticsJson readFile() throws IOException {
        String inputString = Files.readString(Paths.get("statistics.json"));
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        return mapper.readValue(inputString, StatisticsJson.class);
    }
}
