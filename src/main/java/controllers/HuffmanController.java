package controllers;

import dtos.HuffmanCompressOutputDTO;
import dtos.HuffmanDecompressOutputDTO;
import dtos.HuffmanInputDTO;
import services.HuffmanService;
import services.HuffmanValidatorService;
import spark.Request;
import spark.Response;
import org.apache.commons.httpclient.HttpStatus;
import utils.JsonTransformer;

public class HuffmanController {
    HuffmanService huffmanService = new HuffmanService();
    HuffmanValidatorService huffmanValidatorService = new HuffmanValidatorService();

    public Object compressHuffman(Request request, Response response){
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

            huffmanService.compressHuffmanFile(huffmanInputDTO.getFileName(), huffmanCompressOutputDTO);

            huffmanCompressOutputDTO.setMessage("Huffman compress success!");
            response.status(HttpStatus.SC_CREATED);
        } catch (Exception e){
            System.out.println("Error compress huffman: " + e.getMessage());
            huffmanCompressOutputDTO.setMessage(e.getMessage());
        } finally {
            return JsonTransformer.toJson(huffmanCompressOutputDTO);
        }
    }

    public Object decompressHuffman(Request request, Response response){
        HuffmanDecompressOutputDTO huffmanDecompressOutputDTO = new HuffmanDecompressOutputDTO();

        try{
            String body = request.body();
            // TODO: ver como manejar mejor la excepcion ante formato no valido del body DTO
            HuffmanInputDTO huffmanInputDTO = JsonTransformer.fromJson(body, HuffmanInputDTO.class);

            if (!huffmanValidatorService.isValidInput(huffmanInputDTO)) {
                response.status(HttpStatus.SC_BAD_REQUEST);
                huffmanDecompressOutputDTO.setMessage("Invalid input file name");
                throw new Exception("Invalid input file name");
            }
            huffmanService.decompressHuffmanFile(huffmanInputDTO.getFileName());

            huffmanDecompressOutputDTO.setMessage("Huffman compress success!");
        } catch (Exception e){
            System.out.println("Error decompress huffman: " + e.getMessage());
            huffmanDecompressOutputDTO.setMessage(e.getMessage());
        } finally {
            return JsonTransformer.toJson(huffmanDecompressOutputDTO);
        }
    }
}
