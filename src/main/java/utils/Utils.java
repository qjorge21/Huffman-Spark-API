package utils;

import java.io.File;
import java.math.BigDecimal;

public class Utils {
    public static long getFileSize(String fileName){
        File file = new File(fileName);
        if (!file.exists() || !file.isFile()) {
            System.out.println("El archivo no existe");
            return -1;
        }
        return file.length();
    }

    public static BigDecimal calculatePercent(long total, long partial){
        BigDecimal percent = BigDecimal.ZERO;

        percent = BigDecimal.valueOf((partial * 100) / total, 2);

        return percent;
    }
}
