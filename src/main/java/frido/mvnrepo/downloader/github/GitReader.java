package frido.mvnrepo.downloader.github;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import frido.mvnrepo.downloader.report.ScmReport;
import frido.mvnrepo.downloader.stats.KeyValue;
import frido.mvnrepo.downloader.stats.KeyValueGroup;
import frido.mvnrepo.downloader.stats.StatisticsJson;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

public class GitReader {

    FileWriter fileWriter;
    PrintWriter printWriter;

    public static void main(String[] args) throws IOException {
        GitReader reportMain = new GitReader();
        reportMain.start();
    }

    public void start() throws IOException {
        fileWriter = new FileWriter("github.list");
        printWriter = new PrintWriter(fileWriter);

        StatisticsJson statisticsJson = readFile();
        ScmReport report = new ScmReport(statisticsJson.getScm());
        createReportDir();
        getGithub(report).stream()
            .map(KeyValue::getName)
            .map(GithubLink::new)
            .filter(GithubLink::isValid)
            .forEach(this::print);

        printWriter.close();
    }

    private List<KeyValue> getGithub(ScmReport report) {
        for (KeyValueGroup x : report.getData().getList()) {
            if (x.getName().equals("github.com")) {
                return x.getChilds();
            }
        }
        return Collections.EMPTY_LIST;
    }

    private void createReportDir() {
        Paths.get("report").toFile().mkdir();
    }

    private void print(GithubLink value) {
        printWriter.println(value);
        printWriter.flush();
    }

    private StatisticsJson readFile() throws IOException {
        String inputString = Files.readString(Paths.get("statistics.json"));
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        return mapper.readValue(inputString, StatisticsJson.class);
    }
}
