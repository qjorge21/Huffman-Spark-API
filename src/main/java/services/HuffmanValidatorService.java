package services;

import dtos.HuffmanInputDTO;

public class HuffmanValidatorService {
    public boolean isValidInput(HuffmanInputDTO huffmanInputDTO){
        if (huffmanInputDTO.getFileName() == null) {
            return false;
        }

        if (huffmanInputDTO.getFileName() == ""){
            return false;
        }

        if (isNumeric(huffmanInputDTO.getFileName())){
            return false;
        }

        return true;
    }

    private boolean isNumeric(String str){
        try {
            Integer.parseInt(str);
        } catch (NumberFormatException nfe) {
            return false;
        }

        try {
            Double.parseDouble(str);
        } catch (NumberFormatException nfe) {
            return false;
        }

        try {
            Float.parseFloat(str);
        } catch (NumberFormatException nfe) {
            return false;
        }

        return false;
    }
}
