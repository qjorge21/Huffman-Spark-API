import controllers.HuffmanController;

import static spark.Spark.*;

public class main {
    public static void main(String[] args) {
        HuffmanController huffmanController = new HuffmanController();
        // routes
        get("/hello", (req, res) -> "Hello World");
        post("/compressHuffman", huffmanController::compressHuffman);
        post("/decompressHuffman", huffmanController::decompressHuffman);

    }
}
