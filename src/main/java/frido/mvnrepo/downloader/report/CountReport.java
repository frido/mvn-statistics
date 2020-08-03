package frido.mvnrepo.downloader.report;

import frido.mvnrepo.downloader.core.json.DataJson;
import frido.mvnrepo.downloader.core.stats.KeyValueMap;
import frido.mvnrepo.downloader.core.stats.ValueList;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CountReport {

    private ValueList data;

    public CountReport(ValueList data) {
        this.data = data;
    }

    public DataJson report() {
        CountReportJson json = new CountReportJson();
        json.setMax(data.getMax());
        json.setMaxName(data.getMaxName());
        json.setSize(data.getList().size());
        json.setNonZeros(data.getList().stream().filter(x -> x > 0).count());
        json.setSum(data.getList().stream().mapToLong(x -> x).sum());
        json.setNonZeroRatio(BigDecimal.valueOf(json.getNonZeros()).divide(BigDecimal.valueOf(json.getSize()), 4, RoundingMode.HALF_DOWN).multiply(BigDecimal.valueOf(100)).setScale(2));
        json.setAverage(BigDecimal.valueOf(json.getSum()).divide(BigDecimal.valueOf(json.getNonZeros()), 2, RoundingMode.HALF_DOWN));
        KeyValueMap map = new KeyValueMap();
        data.getList().stream().map(String::valueOf).forEach(map::add);
        json.setMode(map.toList());
        return new DataJson(json);
    }

}
