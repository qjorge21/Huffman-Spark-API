package dtos;

import com.google.gson.annotations.SerializedName;

public class HuffmanDecompressOutputDTO {
    private String message;
    private long timeElapsed;

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
}
