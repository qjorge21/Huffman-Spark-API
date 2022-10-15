package controllers;

import services.HuffmanService;
import spark.Request;
import spark.Response;
import spark.Route;
import org.codehaus.jackson.map.ObjectMapper;

import java.util.HashMap;

public class HuffmanController {
    public static Route runHuffmanCode = (Request req, Response res) -> {
        HashMap<Object, Object> model = new HashMap<>();
        ObjectMapper mapperObj = new ObjectMapper();
        try {
            HuffmanService.runHuffmanCode();
        } catch (Exception e){
            System.out.println("Error run huffman code: " + e.getMessage());
            model.put("Response", e.getMessage());
        } finally {
            return mapperObj.writeValueAsString(model);
        }
    };

    public static Route compressHuffman = (Request req, Response res) -> {
        HashMap<Object, Object> model = new HashMap<>();
        ObjectMapper mapperObj = new ObjectMapper();

        try{
            HuffmanService.compressHuffmanFile("test");
        } catch (Exception e){
            System.out.println("Error compress huffman: " + e.getMessage());
            model.put("Response", e.getMessage());
        } finally {
            return mapperObj.writeValueAsString(model);
        }
    };

    public static Route decompressHuffman = (Request req, Response res) -> {
        HashMap<Object, Object> model = new HashMap<>();
        ObjectMapper mapperObj = new ObjectMapper();

        try{
            HuffmanService.decompressHuffmanFile("test");
        } catch (Exception e){
            System.out.println("Error decompress huffman: " + e.getMessage());
            model.put("Response", e.getMessage());
        } finally {
            return mapperObj.writeValueAsString(model);
        }
    };
}
