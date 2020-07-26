package frido.mvnrepo.downloader.pom;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class OpionalStream<T> {

    List<T> list;

    public OpionalStream(List<T> l) {
        this.list = l;
    }

    public void forEach(Consumer<? super T> action) {
        stream().forEach(action);
    }

    public long count() {
        return stream().count();
    }

    public Stream<T> stream() {
        return Optional.ofNullable(list).stream().flatMap(s -> s.stream());
    }

}
