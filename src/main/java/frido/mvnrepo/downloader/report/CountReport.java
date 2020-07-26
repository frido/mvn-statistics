package frido.mvnrepo.downloader.report;

import frido.mvnrepo.downloader.stats.KeyValue;
import frido.mvnrepo.downloader.stats.KeyValueList;
import frido.mvnrepo.downloader.stats.ValueList;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class CountReport {

    private ValueList data;

    private long max;
    private String maxName;
    private long size;
    private long nonZeros;
    private long sum;

    public CountReport(ValueList data) {
        this.data = data;
        this.max = data.getMax();
        this.maxName = data.getMaxName();
        this.size = data.getList().size();
        this.nonZeros = data.getList().stream().filter(x -> x > 0).count();
        this.sum = data.getList().stream().mapToLong(x -> x).sum();
    }

    public BigDecimal getNonZeroRatio() {
        return BigDecimal.valueOf(nonZeros).divide(BigDecimal.valueOf(size), 4, RoundingMode.HALF_DOWN).multiply(BigDecimal.valueOf(100)).setScale(2);
    }

    public BigDecimal getAverage() {
        return BigDecimal.valueOf(sum).divide(BigDecimal.valueOf(nonZeros), 2, RoundingMode.HALF_DOWN);
    }

    public List<KeyValue> getMode() {
        KeyValueList map = new KeyValueList();
        data.getList().stream().map(String::valueOf).forEach(map::add);
        return map.toList();
    }

    public long getMax() {
        return max;
    }

    public String getMaxName() {
        return maxName;
    }

    public long getSize() {
        return size;
    }

    public long getNonZeros() {
        return nonZeros;
    }

    public long getSum() {
        return sum;
    }

    @Override
    public String toString() {
        return "CountReport{" +
                "max=" + max +
                ", maxName='" + maxName + '\'' +
                ", size=" + size +
                ", nonZeros=" + nonZeros +
                ", sum=" + sum +
                ", nonZeroRatio=" + getNonZeroRatio() +
                ", average=" + getAverage() +
                ", mode=" + getMode() +
                '}';
    }
}
