package huffman;

import java.util.Comparator;

public class ComparatorTree implements Comparator<BinaryTree<FrequencyCharacter>> {

    public int compare(BinaryTree<FrequencyCharacter> tree1, BinaryTree<FrequencyCharacter> tree2) {

        if (tree1.getData().getFrequency() < tree2.getData().getFrequency()) {
            return -1;
        }

        if (tree1.getData().getFrequency() > tree2.getData().getFrequency()) {
            return 1;
        } else {
            return 0;
        }
    }
}
