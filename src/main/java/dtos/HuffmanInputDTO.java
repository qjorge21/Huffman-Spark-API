package dtos;

import com.google.gson.annotations.SerializedName;

public class HuffmanInputDTO {
    @SerializedName("file_name")
    private String fileName;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
