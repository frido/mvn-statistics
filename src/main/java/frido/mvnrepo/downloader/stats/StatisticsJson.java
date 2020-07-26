package frido.mvnrepo.downloader.stats;

import java.util.List;

public class StatisticsJson {

    private List<KeyValue> ciManagement = null;
    private List<KeyValue> dependencies = null;
    private ValueList dependenciesCount = null;
    private List<KeyValue> plugins = null;
    private ValueList pluginsCount = null;
    private List<KeyValue> reportingPlugins = null;
    private ValueList reportingPluginsCount = null;
    private List<KeyValue> inceptionYears = null;
    private ValueList licensesCount = null;
    private List<KeyValue> licenses = null;
    private ValueList developersCount = null;
    private List<KeyValue> developers = null;
    private ValueList contributorsCount = null;
    private List<KeyValue> contributors = null;
    private List<KeyValue> issueManagement = null;
    private List<KeyValue> scm =null;
    private ValueList profilesCount = null;
    private List<KeyValue> profiles = null;

    public List<KeyValue> getCiManagement() {
        return ciManagement;
    }

    public void setCiManagement(List<KeyValue> ciManagement) {
        this.ciManagement = ciManagement;
    }

    public List<KeyValue> getDependencies() {
        return dependencies;
    }

    public void setDependencies(List<KeyValue> dependencies) {
        this.dependencies = dependencies;
    }

    public ValueList getDependenciesCount() {
        return dependenciesCount;
    }

    public void setDependenciesCount(ValueList dependenciesCount) {
        this.dependenciesCount = dependenciesCount;
    }

    public List<KeyValue> getPlugins() {
        return plugins;
    }

    public void setPlugins(List<KeyValue> plugins) {
        this.plugins = plugins;
    }

    public ValueList getPluginsCount() {
        return pluginsCount;
    }

    public void setPluginsCount(ValueList pluginsCount) {
        this.pluginsCount = pluginsCount;
    }

    public List<KeyValue> getReportingPlugins() {
        return reportingPlugins;
    }

    public void setReportingPlugins(List<KeyValue> reportingPlugins) {
        this.reportingPlugins = reportingPlugins;
    }

    public ValueList getReportingPluginsCount() {
        return reportingPluginsCount;
    }

    public void setReportingPluginsCount(ValueList reportingPluginsCount) {
        this.reportingPluginsCount = reportingPluginsCount;
    }

    public List<KeyValue> getInceptionYears() {
        return inceptionYears;
    }

    public void setInceptionYears(List<KeyValue> inceptionYears) {
        this.inceptionYears = inceptionYears;
    }

    public ValueList getLicensesCount() {
        return licensesCount;
    }

    public void setLicensesCount(ValueList licensesCount) {
        this.licensesCount = licensesCount;
    }

    public List<KeyValue> getLicenses() {
        return licenses;
    }

    public void setLicenses(List<KeyValue> licenses) {
        this.licenses = licenses;
    }

    public ValueList getDevelopersCount() {
        return developersCount;
    }

    public void setDevelopersCount(ValueList developersCount) {
        this.developersCount = developersCount;
    }

    public List<KeyValue> getDevelopers() {
        return developers;
    }

    public void setDevelopers(List<KeyValue> developers) {
        this.developers = developers;
    }

    public ValueList getContributorsCount() {
        return contributorsCount;
    }

    public void setContributorsCount(ValueList contributorsCount) {
        this.contributorsCount = contributorsCount;
    }

    public List<KeyValue> getContributors() {
        return contributors;
    }

    public void setContributors(List<KeyValue> contributors) {
        this.contributors = contributors;
    }

    public List<KeyValue> getIssueManagement() {
        return issueManagement;
    }

    public void setIssueManagement(List<KeyValue> issueManagement) {
        this.issueManagement = issueManagement;
    }

    public List<KeyValue> getScm() {
        return scm;
    }

    public void setScm(List<KeyValue> scm) {
        this.scm = scm;
    }

    public ValueList getProfilesCount() {
        return profilesCount;
    }

    public void setProfilesCount(ValueList profilesCount) {
        this.profilesCount = profilesCount;
    }

    public List<KeyValue> getProfiles() {
        return profiles;
    }

    public void setProfiles(List<KeyValue> profiles) {
        this.profiles = profiles;
    }
}
