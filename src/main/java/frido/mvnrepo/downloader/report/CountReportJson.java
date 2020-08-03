package frido.mvnrepo.downloader.report;

import frido.mvnrepo.downloader.core.stats.KeyValue;

import java.math.BigDecimal;
import java.util.List;

public class CountReportJson {
    private long max;
    private String maxName;
    private long size;
    private long nonZeros;
    private long sum;
    private BigDecimal nonZeroRatio;
    private BigDecimal average;
    private List<KeyValue> mode;

    public void setNonZeroRatio(BigDecimal nonZeroRatio) {
        this.nonZeroRatio = nonZeroRatio;
    }

    public void setAverage(BigDecimal average) {
        this.average = average;
    }

    public void setMode(List<KeyValue> mode) {
        this.mode = mode;
    }

    public BigDecimal getNonZeroRatio() {
        return nonZeroRatio;
    }

    public BigDecimal getAverage() {
        return average;
    }

    public List<KeyValue> getMode() {
        return mode;
    }

    public long getMax() {
        return max;
    }

    public void setMax(long max) {
        this.max = max;
    }

    public String getMaxName() {
        return maxName;
    }

    public void setMaxName(String maxName) {
        this.maxName = maxName;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public long getNonZeros() {
        return nonZeros;
    }

    public void setNonZeros(long nonZeros) {
        this.nonZeros = nonZeros;
    }

    public long getSum() {
        return sum;
    }

    public void setSum(long sum) {
        this.sum = sum;
    }
}
