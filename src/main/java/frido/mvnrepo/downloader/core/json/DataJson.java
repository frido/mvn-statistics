package frido.mvnrepo.downloader.core.json;

// TODO: I dont need data at the beginning of json
@Deprecated
public class DataJson {
    private Object data;

    public DataJson(Object data) {
        this.data = data;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
