package frido.mvnrepo.downloader.pom;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import frido.mvnrepo.downloader.stats.ValueList;
import frido.mvnrepo.downloader.stats.KeyValueList;
import frido.mvnrepo.downloader.stats.StatisticsJson;

public class Statistics {

    private KeyValueList ciManagement = new KeyValueList();
    private KeyValueList dependencies = new KeyValueList();
    private ValueList dependenciesCount = new ValueList();
    private KeyValueList plugins = new KeyValueList();
    private ValueList pluginsCount = new ValueList();
    private KeyValueList reportingPlugins = new KeyValueList();
    private ValueList reportingPluginsCount = new ValueList();
    private KeyValueList inceptionYears = new KeyValueList();
    private ValueList licensesCount = new ValueList();
    private KeyValueList licenses = new KeyValueList();
    private ValueList developersCount = new ValueList();
    private KeyValueList developers = new KeyValueList();
    private ValueList contributorsCount = new ValueList();
    private KeyValueList contributors = new KeyValueList();
    private KeyValueList issueManagement = new KeyValueList();
    private KeyValueList scm = new KeyValueList();
    private ValueList profilesCount = new ValueList();
    private KeyValueList profiles = new KeyValueList();


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

    public void addScm(String connection) {
        scm.add(connection);
    }

    public void addProfilesCount(String url, long count) {
        profilesCount.add(url, count);
    }

    public void addProfiles(String profile) {
        profiles.add(profile);
    }

    public String toJson() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

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
        json.setScm(scm.toList());

        return mapper.writeValueAsString(json);
    }
}
