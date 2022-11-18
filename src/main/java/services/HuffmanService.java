package services;

import dtos.HuffmanCompressOutputDTO;
import huffman.*;
import utils.Utils;

import java.io.*;
import java.util.HashMap;
import java.util.PriorityQueue;

public class HuffmanService {
    private HashMap<Character, String> huffmanCode = new HashMap<>();

    // TODO: cambiar para que sea "relativo" el path y no absoluto
    final String FILES_PATH = "C:\\Users\\George\\Documents\\Huffman-Maven\\src\\main\\java\\files\\";

    public HuffmanService(){
    }

    private BinaryTree<FrequencyCharacter> initHuffman(String fileName){
        String pathFile = FILES_PATH + fileName + ".txt";
        BinaryTree<FrequencyCharacter> bigTree = getTreeCode(singletonTree(getFrequencyTable(pathFile)));

        getHuffmanCode(bigTree, "");
        return bigTree;
    }

    public void compressHuffmanFile(String fileName, HuffmanCompressOutputDTO huffmanCompressOutputDTO){
        try{
            String inputPathFile = FILES_PATH + fileName + ".txt";
            String outputPathFile = inputPathFile.substring(0, inputPathFile.length() - 4) + "_comprimido" + ".txt";

            initHuffman(fileName);

            long startTime = System.currentTimeMillis();

            compressHuffman(inputPathFile, outputPathFile);

            long timeElapsed = System.currentTimeMillis() - startTime;

            // metricas de tamaño de reduccion
            long inputSize = Utils.getFileSize(inputPathFile);
            long outputSize = Utils.getFileSize(outputPathFile);

            huffmanCompressOutputDTO.setSizeBytesInput(inputSize);
            huffmanCompressOutputDTO.setSizeBytesOutput(outputSize);
            huffmanCompressOutputDTO.setCompressPercent(Utils.calculatePercent(inputSize, outputSize));
            huffmanCompressOutputDTO.setTimeElapsed(timeElapsed);
        } catch (Exception e){
            System.out.println("Error compress huffman: " + e.getMessage());
        }
    }

    public void decompressHuffmanFile(String fileName){
        try{
            String pathFile = FILES_PATH + fileName + ".txt";
            BinaryTree<FrequencyCharacter> bigTree = initHuffman(fileName);
            decompressHuffman(pathFile.substring(0, pathFile.length() - 4) + "_comprimido" + ".txt", pathFile.substring(0, pathFile.length() - 4) + "_descomprimido" + ".txt", bigTree);;
        } catch (Exception e){
            System.out.println("Error decompress huffman: " + e.getMessage());
        }
    }

    private HashMap<Character, Integer> getFrequencyTable(String path){

        HashMap<Character, Integer> frequencyTable = new HashMap<>();
        BufferedReader inputFile = null;

        try {
            inputFile = new BufferedReader(new FileReader(path));
            int currentInteger;

            while ((currentInteger = inputFile.read()) >= 0) {
                char q = (char) currentInteger;
                if (frequencyTable.containsKey(q)) {
                    frequencyTable.put(q, frequencyTable.get(q) + 1);
                } else {
                    frequencyTable.put(q, 1);
                }
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        } finally {
            try {
                inputFile.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return frequencyTable;
    }

    private PriorityQueue<BinaryTree<FrequencyCharacter>> singletonTree(HashMap<Character, Integer> frequencyTable) {

        if (frequencyTable.size() == 0) {
            return null;
        }

        PriorityQueue<BinaryTree<FrequencyCharacter>> frequencyQueue = new PriorityQueue<BinaryTree<FrequencyCharacter>>(frequencyTable.size(), new ComparatorTree());

        for (Character c : frequencyTable.keySet()) {

            FrequencyCharacter singletonNode = new FrequencyCharacter(c, frequencyTable.get(c));

            BinaryTree<FrequencyCharacter> singletonTree = new BinaryTree<FrequencyCharacter>(singletonNode);

            frequencyQueue.add(singletonTree);
        }

        return frequencyQueue;
    }

    private BinaryTree<FrequencyCharacter> getTreeCode(PriorityQueue<BinaryTree<FrequencyCharacter>> frequency) {

        if (frequency == null) {
            BinaryTree<FrequencyCharacter> emptyTree = new BinaryTree<FrequencyCharacter>(null);
            return emptyTree;
        }

        if (frequency.size() == 1) {

            FrequencyCharacter rootNode = new FrequencyCharacter(frequency.peek().getData().getFrequency());

            BinaryTree<FrequencyCharacter> character = new BinaryTree<FrequencyCharacter>(rootNode);

            character.setLeft(frequency.poll());

            return character;
        }

        while (frequency.size() > 1) {

            BinaryTree<FrequencyCharacter> t1 = frequency.poll();
            BinaryTree<FrequencyCharacter> t2 = frequency.poll();

            FrequencyCharacter root = new FrequencyCharacter(t1.getData().getFrequency() + t2.getData().getFrequency());

            BinaryTree<FrequencyCharacter> bigTree = new BinaryTree<FrequencyCharacter>(root);
            bigTree.setLeft(t1);
            bigTree.setRight(t2);

            frequency.add(bigTree);
        }

        return frequency.poll();
    }

    private void getHuffmanCode(BinaryTree<FrequencyCharacter> node, String pathCode) {

        if (node.getData() == null) {
            return;
        }

        if (node.isLeafNode()) {
            huffmanCode.put(node.getData().getCharacter(), pathCode);
        }

        if (node.hasLeftChild()) {
            getHuffmanCode(node.getLeft(), pathCode + '0');
        }

        if (node.hasRightChild()) {
            getHuffmanCode(node.getRight(), pathCode + '1');
        }
    }

    public void compressHuffman(String input, String output) {

        BufferedReader inputFile = null;
        WriteBit outputFileBits = null;

        try {
            inputFile = new BufferedReader(new FileReader(input));
            outputFileBits = new WriteBit(output);

            int currentValue;

            while ((currentValue = inputFile.read()) >= 0) {
                char characterRead = (char) currentValue;

                String codeWord = huffmanCode.get(characterRead);

                for (int i = 0; i < codeWord.length(); i++) {
                    char b = codeWord.charAt(i);
                    if (b == '0') {
                        outputFileBits.writingBit(0);
                    } else {
                        outputFileBits.writingBit(1);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                inputFile.close();
                outputFileBits.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void decompressHuffman(String input, String output, BinaryTree<FrequencyCharacter> nodeFrequency) {

        ReadBit inputFileBits = null;
        BufferedWriter outputFile = null;

        try {
            inputFileBits = new ReadBit(input);
            outputFile = new BufferedWriter(new FileWriter(output));
            int bit;

            BinaryTree<FrequencyCharacter> root = nodeFrequency;

            while ((bit = inputFileBits.bitRead()) >= 0) {

                if (bit == 0) {
                    nodeFrequency = nodeFrequency.getLeft();
                }

                if (bit == 1) {
                    nodeFrequency = nodeFrequency.getRight();
                }

                if (nodeFrequency.isLeafNode()) {
                    outputFile.write(nodeFrequency.getData().getCharacter());
                    nodeFrequency = root;
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                inputFileBits.close();
                outputFile.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
