package frido.mvnrepo.downloader.core.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GithubRepoJson {

    /**
     * description
     * stargazers_count
     * watchers_count
     * language
     * forks_count
     * network_count
     * subscribers_count
     */

    @JsonProperty("full_name")
    private String fullName;
    @JsonProperty("description")
    private String description;
    @JsonProperty("stargazers_count")
    private int stargazersCount;
    @JsonProperty("watchers_count")
    private int watchersCount;
    @JsonProperty("language")
    private String language;
    @JsonProperty("forks_count")
    private int forksCount;
    @JsonProperty("network_count")
    private int networkCount;
    @JsonProperty("subscribers_count")
    private int subscribersCount;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStargazersCount() {
        return stargazersCount;
    }

    public void setStargazersCount(int stargazersCount) {
        this.stargazersCount = stargazersCount;
    }

    public int getWatchersCount() {
        return watchersCount;
    }

    public void setWatchersCount(int watchersCount) {
        this.watchersCount = watchersCount;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getForksCount() {
        return forksCount;
    }

    public void setForksCount(int forksCount) {
        this.forksCount = forksCount;
    }

    public int getNetworkCount() {
        return networkCount;
    }

    public void setNetworkCount(int networkCount) {
        this.networkCount = networkCount;
    }

    public int getSubscribersCount() {
        return subscribersCount;
    }

    public void setSubscribersCount(int subscribersCount) {
        this.subscribersCount = subscribersCount;
    }

    @Override
    public String toString() {
        return "GithubRepoJson{" +
                "fullName='" + fullName + '\'' +
                ", description='" + description + '\'' +
                ", stargazersCount=" + stargazersCount +
                ", watchersCount=" + watchersCount +
                ", language='" + language + '\'' +
                ", forksCount=" + forksCount +
                ", networkCount=" + networkCount +
                ", subscribersCount=" + subscribersCount +
                '}';
    }


}
