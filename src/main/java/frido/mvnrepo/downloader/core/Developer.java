package frido.mvnrepo.downloader.core;

public class Developer {
    private String name;
    private String email;

    public Developer(String id) {
        int split = id.indexOf(":");
        name = id.substring(0, split);
        email = id.substring(split + 1);
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "Developer{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
