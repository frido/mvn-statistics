package frido.mvnrepo.downloader.github;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GithubRepoJson {

    @JsonProperty("full_name")
    private String fullName;


    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public String toString() {
        return "GithubRepoJson{" +
                "fullName='" + fullName + '\'' +
                '}';
    }
}
