import controllers.HuffmanController;

import static spark.Spark.*;

public class main {
    public static void main(String[] args) {
        // routes
        get("/hello", (req, res) -> "Hello World");
        post("/runHuffmanCode", HuffmanController.runHuffmanCode);
        post("/compressHuffman", HuffmanController.compressHuffman);
        post("/decompressHuffman", HuffmanController.decompressHuffman);
        
    }
}
