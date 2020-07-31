package frido.mvnrepo.downloader.github;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public class GitHubReader {

    FileWriter fileWriter;
    PrintWriter printWriter;

    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
        GitHubReader reportMain = new GitHubReader();
        reportMain.start();
    }

    public void start() throws IOException {
        createReportDir();
        fileWriter = new FileWriter("report/github.json");
        printWriter = new PrintWriter(fileWriter);
        GitHubClient client = new GitHubClient("ed13ee8fc89d12d3dfa8f074586fd3938d82c71c");
        writeIntro();
        readFile().stream().limit(10).forEach(fullName -> {
            printWriter.println(client.repo(fullName).toString());
        });
        writeOutro();

        printWriter.close();
    }

    private void writeOutro() {
        printWriter.println("]}");
    }

    private void writeIntro() {
        printWriter.println("{");
        printWriter.println("\"data\" : [");
    }

    private void createReportDir() {
        Paths.get("report").toFile().mkdir();
    }

    private void print(GithubLink value) {
        printWriter.println(value);
        printWriter.flush();
    }

    private List<String> readFile() throws IOException {
        return Files.readAllLines(Paths.get("github.list"));
    }
}
