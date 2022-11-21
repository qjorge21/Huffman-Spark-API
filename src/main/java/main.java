import controllers.HuffmanController;
import spark.Spark;

import static spark.Spark.*;

public class main {
    public static void main(String[] args) {
        Spark.options("/*", (request, response) -> {

            String accessControlRequestHeaders = request.headers("Access-Control-Request-Headers");
            if (accessControlRequestHeaders != null) {
                response.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
            }

            String accessControlRequestMethod = request.headers("Access-Control-Request-Method");
            if (accessControlRequestMethod != null) {
                response.header("Access-Control-Allow-Methods", accessControlRequestMethod);
            }
            return "OK";
        });

        Spark.before((request, response) -> {
            response.header("Access-Control-Allow-Origin", "*");
        });

        HuffmanController huffmanController = new HuffmanController();
        // routes
        get("/hello", (req, res) -> "Hello World");
        post("/compressHuffman", huffmanController::compressHuffman);
        post("/decompressHuffman", huffmanController::decompressHuffman);

    }
}
