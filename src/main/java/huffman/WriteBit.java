package huffman;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class WriteBit {

    private byte currentByte;
    private byte bitsWrittenCounter;
    private BufferedOutputStream output;

    public WriteBit(String filePath) throws FileNotFoundException {
        currentByte = 0;
        bitsWrittenCounter = 0;
        output = new BufferedOutputStream(new FileOutputStream(filePath));
    }

    public void writingBit(int bit) throws IOException {
        if (bit < 0 || bit > 1)
            throw new IllegalArgumentException("Arg to write: bit = " + bit);

        bitsWrittenCounter++;
        currentByte |= bit << (8 - bitsWrittenCounter);
        if (bitsWrittenCounter == 8) {
            output.write(currentByte);
            bitsWrittenCounter = 0;
            currentByte = 0;
        }
    }

    public void close() throws IOException {
        output.write(currentByte);
        output.write(bitsWrittenCounter);

        output.close();
    }

}
