package tree;

public class TRIE {

    Node nodes[];
    boolean isLeaf;

    public TRIE() {
        nodes = new Node[26];
        isLeaf = true;
    }

    public static class Node {

        Character info;
        Object child;

    }

    public void addElement(String element) {

        for (int index = 0; index < element.length(); index++) {
            Character ch = Character.toLowerCase(element.charAt(index));
            nodes['a' - ch].info = ch;
            // nodes[]
        }

    }
}
