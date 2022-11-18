package controllers;

import dtos.HuffmanCompressOutputDTO;
import dtos.HuffmanInputDTO;
import services.HuffmanService;
import services.HuffmanValidatorService;
import spark.Request;
import spark.Response;
import org.codehaus.jackson.map.ObjectMapper;
import org.apache.commons.httpclient.HttpStatus;
import utils.JsonTransformer;

import java.io.IOException;
import java.util.HashMap;

public class HuffmanController {
    HuffmanService huffmanService = new HuffmanService();
    HuffmanValidatorService huffmanValidatorService = new HuffmanValidatorService();

    public Object runHuffmanCode(Request request, Response response) throws IOException {
        HashMap<Object, Object> model = new HashMap<>();
        ObjectMapper mapperObj = new ObjectMapper();
        try {
            String body = request.body();

            HuffmanInputDTO huffmanInputDTO = JsonTransformer.fromJson(body, HuffmanInputDTO.class);

            huffmanService.runHuffmanCode();
        } catch (Exception e){
            System.out.println("Error run huffman code: " + e.getMessage());
            model.put("Response", e.getMessage());
        } finally {
            response.status(HttpStatus.SC_OK);
            return mapperObj.writeValueAsString(model);
        }
    };

    public Object compressHuffman(Request request, Response response) throws IOException {
        HuffmanCompressOutputDTO huffmanCompressOutputDTO = new HuffmanCompressOutputDTO();

        try{
            String body = request.body();
            // TODO: ver como manejar mejor la excepcion ante formato no valido del body DTO
            HuffmanInputDTO huffmanInputDTO = JsonTransformer.fromJson(body, HuffmanInputDTO.class);

            if (!huffmanValidatorService.isValidInput(huffmanInputDTO)) {
                response.status(HttpStatus.SC_BAD_REQUEST);
                huffmanCompressOutputDTO.setMessage("Invalid input file name");
                throw new Exception("Invalid input file name");
            }

            huffmanService.compressHuffmanFile(huffmanInputDTO.getFileName());

            huffmanCompressOutputDTO.setMessage("Huffman compress success!");
            // agregar tiempo de respuesta y tamaño de compresion...
        } catch (Exception e){
            System.out.println("Error compress huffman: " + e.getMessage());
            huffmanCompressOutputDTO.setMessage(e.getMessage());
        } finally {
            return JsonTransformer.toJson(huffmanCompressOutputDTO);
        }
    }

    public Object decompressHuffman(Request request, Response response) throws IOException {
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
