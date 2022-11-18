package dtos;

import java.math.BigDecimal;

public class HuffmanCompressOutputDTO {
    private String message;
    private BigDecimal time = BigDecimal.ZERO;
    private BigDecimal compressPercent = BigDecimal.ZERO;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public BigDecimal getTime() {
        return time;
    }

    public void setTime(BigDecimal time) {
        this.time = time;
    }

    public BigDecimal getCompressPercent() {
        return compressPercent;
    }

    public void setCompressPercent(BigDecimal compressPercent) {
        this.compressPercent = compressPercent;
    }
}
