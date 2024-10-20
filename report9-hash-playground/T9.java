import java.util.ArrayList;

public class T9 {
    private class Node {
        public Node[] next; // Represents branches to other nodes
        public boolean valid; // True if this node completes a valid word

        public Node() {
            next = new Node[27]; // 27 branches for 'a' to 'ö'
            valid = false;
        }
    }

    private Node root;

    public T9() {
        root = new Node();
    }

    // Map character to index (0 for 'a', 26 for 'ö')
    private static int code(char w) {
        switch (w) {
            case 'a':
                return 0;
            case 'b':
                return 1;
            case 'c':
                return 2;
            case 'd':
                return 3;
            case 'e':
                return 4;
            case 'f':
                return 5;
            case 'g':
                return 6;
            case 'h':
                return 7;
            case 'i':
                return 8;
            case 'j':
                return 9;
            case 'k':
                return 10;
            case 'l':
                return 11;
            case 'm':
                return 12;
            case 'n':
                return 13;
            case 'o':
                return 14;
            case 'p':
                return 15;
            case 'r':
                return 16;
            case 's':
                return 17;
            case 't':
                return 18;
            case 'u':
                return 19;
            case 'v':
                return 20;
            case 'x':
                return 21;
            case 'y':
                return 22;
            case 'z':
                return 23;
            case 'å':
                return 24;
            case 'ä':
                return 25;
            case 'ö':
                return 26;
            default:
                return -1; // Invalid character
        }
    }

    // Map key (like '2', '3') to character index range
    private static int[] keyToIndexes(char key) {
        switch (key) {
            case '2':
                return new int[] { 0, 1, 2 }; // 'a', 'b', 'c'
            case '3':
                return new int[] { 3, 4, 5 }; // 'd', 'e', 'f'
            case '4':
                return new int[] { 6, 7, 8 }; // 'g', 'h', 'i'
            case '5':
                return new int[] { 9, 10, 11 }; // 'j', 'k', 'l'
            case '6':
                return new int[] { 12, 13, 14 }; // 'm', 'n', 'o'
            case '7':
                return new int[] { 15, 16, 17 }; // 'p', 'r', 's'
            case '8':
                return new int[] { 18, 19, 20 }; // 't', 'u', 'v'
            case '9':
                return new int[] { 21, 22, 23 }; // 'x', 'y', 'z'
            case '0':
                return new int[] { 24, 25, 26 }; // 'å', 'ä', 'ö'
            default:
                return new int[] {}; // Invalid key
        }
    }

    // Add a word to the Trie
    public void addWord(String word) {
        Node current = root;
        for (char ch : word.toCharArray()) {
            int index = code(ch);
            if (current.next[index] == null) {
                current.next[index] = new Node();
            }
            current = current.next[index];
        }
        current.valid = true; // Mark the end of the word
    }

    // Decode a key sequence to find possible words
    public ArrayList<String> decode(String keys) {
        ArrayList<String> words = new ArrayList<>();
        collect(root, "", keys, 0, words); // Start collecting from the root
        return words;
    }

    // Recursive method to collect words
    // Recursive method to collect words, even partial matches
    private void collect(Node node, String currentWord, String keys, int depth, ArrayList<String> words) {
        // Check if we are at a valid word, regardless of depth
        if (node != null && node.valid) {
            words.add(currentWord); // Add valid word to the list
            System.out.println("Added word: " + currentWord); // Debug
        }

        // If we've reached the end of the key sequence, stop searching further
        if (depth == keys.length()) {
            return;
        }

        // Get possible character indexes for the current key
        char key = keys.charAt(depth);
        int[] indexes = keyToIndexes(key);

        // Recursively check all valid branches
        System.out.println("Current depth: " + depth + ", Current word: " + currentWord + ", Key: " + key); // Debug
        for (int index : indexes) {
            if (node.next[index] != null) {
                char correspondingChar = reverseCode(index); // Convert index back to character
                collect(node.next[index], currentWord + correspondingChar, keys, depth + 1, words);
            } else {
                System.out.println("No node for index: " + index + " at depth: " + depth); // Debug
            }
        }
    }

    // Convert code back to character
    private char reverseCode(int code) {
        switch (code) {
            case 0:
                return 'a';
            case 1:
                return 'b';
            case 2:
                return 'c';
            case 3:
                return 'd';
            case 4:
                return 'e';
            case 5:
                return 'f';
            case 6:
                return 'g';
            case 7:
                return 'h';
            case 8:
                return 'i';
            case 9:
                return 'j';
            case 10:
                return 'k';
            case 11:
                return 'l';
            case 12:
                return 'm';
            case 13:
                return 'n';
            case 14:
                return 'o';
            case 15:
                return 'p';
            case 16:
                return 'r';
            case 17:
                return 's';
            case 18:
                return 't';
            case 19:
                return 'u';
            case 20:
                return 'v';
            case 21:
                return 'x';
            case 22:
                return 'y';
            case 23:
                return 'z';
            case 24:
                return 'å';
            case 25:
                return 'ä';
            case 26:
                return 'ö';
            default:
                return '?'; // Error case
        }
    }

    // Testing the implementation
    public static void main(String[] args) {
        T9 t9 = new T9();

        // Add words
        t9.addWord("andra");
        t9.addWord("arton");
        t9.addWord("bakre");
        t9.addWord("brunch");
        t9.addWord("decimeter");
        t9.addWord("elva");
        t9.addWord("ett");
        t9.addWord("europa");
        t9.addWord("fem");
        t9.addWord("femte");

        // Test decoding
        String keySequence = "225"; // This should match some words
        ArrayList<String> words = t9.decode(keySequence);

        // Print decoded words
        System.out.println("Possible words for key sequence " + keySequence + ":");
        for (String word : words) {
            System.out.println(word);
        }
    }
}
