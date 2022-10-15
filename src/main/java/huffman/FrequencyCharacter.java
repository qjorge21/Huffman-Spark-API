package huffman;

public class FrequencyCharacter {

    private char character;
    private int frequency;

    public FrequencyCharacter(Character c, Integer f) {
        character = c;
        frequency = f;
    }

    public FrequencyCharacter(Integer f) {
        frequency = f;
    }

    public char getCharacter() {
        return this.character;
    }

    public int getFrequency() {
        return this.frequency;
    }

    public String toString() {
        return ("\tCharacter: " + character + "\t\t Frequency: " + frequency);
    }
}
