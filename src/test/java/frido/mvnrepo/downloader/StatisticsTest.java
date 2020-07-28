package frido.mvnrepo.downloader;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import frido.mvnrepo.downloader.report.CiManagementReport;
import frido.mvnrepo.downloader.report.CountReport;
import frido.mvnrepo.downloader.report.DeveloperReport;
import frido.mvnrepo.downloader.report.ScmReport;
import frido.mvnrepo.downloader.stats.KeyValue;
import frido.mvnrepo.downloader.stats.KeyValueGroup;
import frido.mvnrepo.downloader.stats.StatisticsJson;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

class StatisticsTest {


    private static StatisticsJson stats;

    private static Long POM_SIZE = 229267l;

    @BeforeAll
    static void before() throws IOException {
        String inputString = Files.readString(Paths.get("statistics.json"));
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        stats = mapper.readValue(inputString, StatisticsJson.class);
    }

    @Test
    void githubLinks() throws IOException {
        ScmReport report = new ScmReport(stats.getScm());
        KeyValueGroup github = report.getData().getList().get(0);
        System.out.println(github.getName());
        System.out.println(github.getValue());
        System.out.println(github.getChilds().size());
    }

    @Test
    void getDeveloperCount() throws IOException {
        List<KeyValue> devsAndContributors = stats.getDevelopers();
        devsAndContributors.addAll(stats.getContributors());
        DeveloperReport developerReport = new DeveloperReport(devsAndContributors);
        System.out.println("Number of developer emails: " + developerReport.getData().size());
    }

    @Test
    void getContributorsMode() throws IOException {
        CountReport report = new CountReport(stats.getContributorsCount());
        System.out.println(": " + report);
    }

    @Test
    void getCount() throws IOException {
        CiManagementReport report = new CiManagementReport(stats.getCiManagement());
        long number = report.getData().getList().stream().mapToLong(KeyValueGroup::getValue).sum();
        System.out.println("number : " + number);
        System.out.println("percent : " + (number / (POM_SIZE / 100)));
    }



}