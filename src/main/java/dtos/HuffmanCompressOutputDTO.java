package dtos;

import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;

public class HuffmanCompressOutputDTO {
    private String message;
    @SerializedName("time_elapsed")
    private long timeElapsed = 0;

    @SerializedName("size_bytes_input")
    private long sizeBytesInput;

    @SerializedName("size_bytes_output")
    private long sizeBytesOutput;

    @SerializedName("compress_percent")
    private BigDecimal compressPercent = BigDecimal.ZERO;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getTimeElapsed() {
        return timeElapsed;
    }

    public void setTimeElapsed(long timeElapsed) {
        this.timeElapsed = timeElapsed;
    }

    public BigDecimal getCompressPercent() {
        return compressPercent;
    }

    public void setCompressPercent(BigDecimal compressPercent) {
        this.compressPercent = compressPercent;
    }

    public long getSizeBytesInput() {
        return sizeBytesInput;
    }

    public void setSizeBytesInput(long sizeBytesInput) {
        this.sizeBytesInput = sizeBytesInput;
    }

    public long getSizeBytesOutput() {
        return sizeBytesOutput;
    }

    public void setSizeBytesOutput(long sizeBytesOutput) {
        this.sizeBytesOutput = sizeBytesOutput;
    }
}
