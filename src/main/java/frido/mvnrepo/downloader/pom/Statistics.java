package frido.mvnrepo.downloader.pom;

import frido.mvnrepo.downloader.core.json.StatisticsJson;
import frido.mvnrepo.downloader.core.stats.KeyValueGroupList;
import frido.mvnrepo.downloader.core.stats.KeyValueMap;
import frido.mvnrepo.downloader.core.stats.ValueList;

public class Statistics {

    private KeyValueMap ciManagement = new KeyValueMap();
    private KeyValueMap dependencies = new KeyValueMap();
    private ValueList dependenciesCount = new ValueList();
    private KeyValueMap plugins = new KeyValueMap();
    private ValueList pluginsCount = new ValueList();
    private KeyValueMap reportingPlugins = new KeyValueMap();
    private ValueList reportingPluginsCount = new ValueList();
    private KeyValueMap inceptionYears = new KeyValueMap();
    private ValueList licensesCount = new ValueList();
    private KeyValueMap licenses = new KeyValueMap();
    private ValueList developersCount = new ValueList();
    private KeyValueMap developers = new KeyValueMap();
    private ValueList contributorsCount = new ValueList();
    private KeyValueMap contributors = new KeyValueMap();
    private KeyValueMap issueManagement = new KeyValueMap();
    private KeyValueGroupList scm = new KeyValueGroupList();
    private ValueList profilesCount = new ValueList();
    private KeyValueMap profiles = new KeyValueMap();


    public void addCiManagement(String system) {
        ciManagement.add(system);
    }

    public void addDependencies(String artifactId) {
        dependencies.add(artifactId);
    }

    public void addDependenciesCount(String url, long size) {
        dependenciesCount.add(url, size);
    }

    public void addPlugins(String id) {
        plugins.add(id);
    }

    public void addPluginsCount(String url, long size) {
        pluginsCount.add(url, size);
    }

    public void addReportingPlugin(String id) {
        reportingPlugins.add(id);
    }

    public void addReportingPluginCount(String url, long size) {
        reportingPluginsCount.add(url, size);
    }

    public void addInceptionYear(String inceptionYear) {
        inceptionYears.add(inceptionYear);
    }

    public void addLicensesCount(String url, long count) {
        licensesCount.add(url, count);
    }

    public void addLicense(String name) {
        licenses.add(name);
    }

    public void addDevelopersCount(String url, long count) {
        developersCount.add(url, count);
    }

    public void addDeveloper(String developer) {
        developers.add(developer);
    }

    public void addContributorsCount(String url, long count) {
        contributorsCount.add(url, count);
    }

    public void addContributor(String developer) {
        contributors.add(developer);
    }

    public void addIssueManagement(String system) {
        issueManagement.add(system);
    }

    public void addScm(String connection, String pomLink) {
        scm.add(connection, pomLink, 1);
    }

    public void addProfilesCount(String url, long count) {
        profilesCount.add(url, count);
    }

    public void addProfiles(String profile) {
        profiles.add(profile);
    }

    public StatisticsJson toJson() {
        StatisticsJson json = new StatisticsJson();
        json.setCiManagement(ciManagement.toList());
        json.setContributors(contributors.toList());
        json.setContributorsCount(contributorsCount);
        json.setDependencies(dependencies.toList());
        json.setDependenciesCount(dependenciesCount);
        json.setDevelopers(developers.toList());
        json.setDevelopersCount(developersCount);
        json.setInceptionYears(inceptionYears.toList());
        json.setIssueManagement(issueManagement.toList());
        json.setLicenses(licenses.toList());
        json.setLicensesCount(licensesCount);
        json.setPlugins(plugins.toList());
        json.setPluginsCount(pluginsCount);
        json.setProfiles(profiles.toList());
        json.setProfilesCount(profilesCount);
        json.setReportingPlugins(reportingPlugins.toList());
        json.setReportingPluginsCount(reportingPluginsCount);
        json.setScm(scm.toJson(Integer.MAX_VALUE, Integer.MAX_VALUE));
        return json;
    }
}
