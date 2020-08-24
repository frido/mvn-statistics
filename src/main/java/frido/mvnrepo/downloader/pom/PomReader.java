package frido.mvnrepo.downloader.pom;

import frido.mvnrepo.downloader.core.*;
import frido.mvnrepo.downloader.core.io.JsonWriter;
import frido.mvnrepo.downloader.core.io.ListReader;
import org.apache.maven.model.Developer;
import org.apache.maven.model.*;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

public class PomReader implements ResponseHandler, StopHandler {

    private Downloader downloader;
    private Statistics statistics;

    private AtomicInteger counter = new AtomicInteger(0);

    public static void main(String[] args) throws IOException {
        PomReader reader = new PomReader();
        reader.start();
    }

    public void start() throws IOException {
        this.statistics = new Statistics();
        downloader = new Downloader(10);
        downloader.registerStopHandler(this);
        downloader.registerResponseHandler(this);
        // TODO: should be data/pom.list
        new ListReader("pom.txt").lines()
            .map(Link::new)
            .forEach(downloader::download);
    }

    @Override
    public void handleResponse(ResponseBody response) {
        PomBody body = new PomBody(response);
        body.getModel().ifPresent(model -> saveStatistics(model, body.getBase().getUrl()));
    }

    private void saveStatistics(Model model, String url) {
        counter.incrementAndGet();
        // dependencies -> id (count)
        OpionalStream<Dependency> dep = new OpionalStream(model.getDependencies());
        statistics.addDependenciesCount(url, dep.count());
        dep.stream().map(this::getId).forEach(statistics::addDependencies);

        // build -> plugins (count)
        Build build = model.getBuild();
        if (build != null) {
            OpionalStream<Plugin> plugins = new OpionalStream<Plugin>(build.getPlugins());
            statistics.addPluginsCount(url, plugins.count());
            plugins.stream().map(Plugin::getId).forEach(statistics::addPlugins);
        } else {
            statistics.addPluginsCount(url, 0);
        }

        // reporting -> plugins (count)
        Reporting reporting = model.getReporting();
        if (reporting != null) {
            OpionalStream<ReportPlugin> plugins = new OpionalStream<ReportPlugin>(reporting.getPlugins());
            statistics.addReportingPluginCount(url, plugins.count());
            plugins.stream().map(r -> String.format("%s:%s", r.getGroupId(), r.getArtifactId())).forEach(statistics::addReportingPlugin);
        } else {
            statistics.addReportingPluginCount(url, 0);
        }

        // inceptionYear
        String inceptionYear = model.getInceptionYear();
        if (inceptionYear != null) {
            statistics.addInceptionYear(inceptionYear);
        }

        // licenses
        OpionalStream<License> licenses = new OpionalStream(model.getLicenses());
        statistics.addLicensesCount(url, licenses.count());
        licenses.stream().map(License::getName).forEach(statistics::addLicense);

        // developers  (count)
        OpionalStream<Developer> developers = new OpionalStream(model.getDevelopers());
        statistics.addDevelopersCount(url, developers.count());
        developers.stream().map(d -> String.format("%s:%s", d.getName(), d.getEmail())).forEach(statistics::addDeveloper);

        // contributors  (count)
        OpionalStream<Contributor> contributors = new OpionalStream(model.getContributors());
        statistics.addContributorsCount(url, contributors.count());
        contributors.stream().map(d -> String.format("%s:%s", d.getName(), d.getEmail())).forEach(statistics::addContributor);

        // issueManagement -> system
        IssueManagement issue = model.getIssueManagement();
        if (issue != null) {
            statistics.addIssueManagement(issue.getSystem());
        }

        // ciManagement
        CiManagement ciManagement = model.getCiManagement();
        if (ciManagement != null) {
            statistics.addCiManagement(ciManagement.getSystem());
        }

        // scm -> connection
        Scm scm = model.getScm();
        if (scm != null) {
            String payload = url + "@@@" + model.getGroupId() + ":" + model.getArtifactId();
            statistics.addScm(scm.getConnection(), payload);
        }

        // profiles -> name (count)
        OpionalStream<Profile> profiles = new OpionalStream(model.getProfiles());
        statistics.addProfilesCount(url, profiles.count());
        profiles.stream().map(Profile::getId).forEach(statistics::addProfiles);
    }

    private String getId(Dependency x) {
        return x.getGroupId() + ":" + x.getArtifactId() + ":" + x.getVersion();
    }

    @Override
    public void stop() {
        System.out.println("Donwloaded poms: " + counter.get());
        // TODO: add base directory
        new JsonWriter("statistics.json").write(statistics.toJson());
        downloader.shutdown();
    }
}
