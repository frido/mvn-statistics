package frido.mvnrepo.downloader.report;

import frido.mvnrepo.downloader.core.stats.ValueList;
import org.junit.jupiter.api.Test;

class CountReportTest {

    @Test
    public void xxx() {
        ValueList data = new ValueList();
        data.add("a", 11);
        data.add("b", 20);
        data.add("c", 30);
        data.add("d", 40);
        data.add("e", 0);
        data.add("f", 0);
        data.add("g", 0);
        CountReport report = new CountReport(data);
        System.out.println(report);
    }

}