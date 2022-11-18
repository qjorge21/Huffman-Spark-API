package dtos;

import java.math.BigDecimal;

public class HuffmanDecompressOutputDTO {
    private String message;
    private BigDecimal time = BigDecimal.ZERO;

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
}
