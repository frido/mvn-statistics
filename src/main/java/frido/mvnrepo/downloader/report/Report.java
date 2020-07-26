package frido.mvnrepo.downloader.report;

import frido.mvnrepo.downloader.stats.*;

import java.util.List;

public class Report {

    StatisticsJson s;

    public Report(StatisticsJson statisticsJson) {
        this.s = statisticsJson;
    }

    public CiManagementReport ciManagement() {
        return new CiManagementReport(s.getCiManagement());
    }

    public DependencyGroupReport dependencyGroup() {
        return new DependencyGroupReport(s.getDependencies(), 20);
    }

    public DependencyArtifactReport dependencyArtifact() {
        return new DependencyArtifactReport(s.getDependencies());
    }

    public PluginsReport plugins() {
        return new PluginsReport(s.getPlugins());
    }

    public PluginsReport reportingPlugins() {
        return new PluginsReport(s.getReportingPlugins());
    }

    public DeveloperReport getDevelopers() {
        List<KeyValue> devsAndContributors = s.getDevelopers();
        devsAndContributors.addAll(s.getContributors());
        DeveloperReport developerReport = new DeveloperReport(devsAndContributors);
        return developerReport;
    }

    public IssueReport getIssues() {
        return new IssueReport(s.getIssueManagement());
    }

    public ScmReport getScm() {
        return new ScmReport(s.getScm());
    }

    public LicenceReport getLicenses() {
        return new LicenceReport(s.getLicenses());
    }
}
