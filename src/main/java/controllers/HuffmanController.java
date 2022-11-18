package controllers;

import services.HuffmanService;
import services.HuffmanValidatorService;
import spark.Request;
import spark.Response;
import spark.Route;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;

public class HuffmanController {
    HuffmanService huffmanService = new HuffmanService();
    HuffmanValidatorService huffmanValidatorService = new HuffmanValidatorService();

    public String runHuffmanCode(Request request, Response response) throws IOException {
        HashMap<Object, Object> model = new HashMap<>();
        ObjectMapper mapperObj = new ObjectMapper();
        try {
            huffmanService.runHuffmanCode();
        } catch (Exception e){
            System.out.println("Error run huffman code: " + e.getMessage());
            model.put("Response", e.getMessage());
        } finally {
            return mapperObj.writeValueAsString(model);
        }
    };

    public String compressHuffman(Request request, Response response) throws IOException {
        HashMap<Object, Object> model = new HashMap<>();
        ObjectMapper mapperObj = new ObjectMapper();

        try{
            huffmanService.compressHuffmanFile("test");
        } catch (Exception e){
            System.out.println("Error compress huffman: " + e.getMessage());
            model.put("Response", e.getMessage());
        } finally {
            return mapperObj.writeValueAsString(model);
        }
    }

    public String decompressHuffman(Request request, Response response) throws IOException {
        HashMap<Object, Object> model = new HashMap<>();
        ObjectMapper mapperObj = new ObjectMapper();

        try{
            huffmanService.decompressHuffmanFile("test");
        } catch (Exception e){
            System.out.println("Error decompress huffman: " + e.getMessage());
            model.put("Response", e.getMessage());
        } finally {
            return mapperObj.writeValueAsString(model);
        }
    }
}
