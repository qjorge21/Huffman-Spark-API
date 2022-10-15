package huffman;

import java.io.BufferedInputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;

public class ReadBit {

    int current;   // Bit actual. Voy a devolver bita bit
    int nextBit;      // Siguiente bit a devolver
    int nextToNext; // Dos byte despues del Byte actual.
    int bitMask;   // Muestra el bit a devolver

    BufferedInputStream input;

    public ReadBit(String pathFile) throws IOException {
        input = new BufferedInputStream(new FileInputStream(pathFile));

        current = input.read();
        if (current == -1) {
            throw new EOFException("El archivo no tiene dos bytes");
        }
        nextBit = input.read();
        if (nextBit == -1) {
            throw new EOFException("El archivo no tiene dos bytes");
        }
        nextToNext = input.read();
        bitMask = 128;
    }

    public int bitRead() throws IOException {
        int bitReturned;

        if (nextToNext == -1) {
            if (nextBit == 0) {
                return -1;
            } else {
                if ((bitMask & current) == 0) {
                    bitReturned = 0;
                } else {
                    bitReturned = 1;
                }
                nextBit--;
                bitMask = bitMask >> 1;
                return bitReturned;
            }
        } else {
            if ((bitMask & current) == 0) {
                bitReturned = 0;
            } else {
                bitReturned = 1;
            }
            bitMask = bitMask >> 1;

            if (bitMask == 0) {
                bitMask = 128;
                current = nextBit;
                nextBit = nextToNext;
                nextToNext = input.read();
            }
            return bitReturned;
        }
    }

    public void close() throws IOException {
        input.close();
    }
}
