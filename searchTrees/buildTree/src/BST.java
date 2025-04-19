import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class BST implements Runnable{
    Node root;
    FileWriter output;

    class Node {
        int value;
        Node left;
        Node right;

        public Node(int value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }

    public static void main(String[] args) {
        new Thread(null, new BST(), "", 64 * 1024 * 1024).start();
    }

    @Override
    public void run() {
        try {
            buildTree();
            output = new FileWriter("output.txt");
            preOrderTraversal(root);
            output.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    void buildTree() throws IOException {
        File inputFile = new File("input.txt");
        Scanner sc = new Scanner(inputFile);
        if (sc.hasNextInt()) {
            root = new Node(sc.nextInt());

            while(sc.hasNextInt()) {
                insert(sc.nextInt());
            }
        }
    }

    public void insert(int valueToInsert) {
        Node node = root;
        while (true) {
            if (valueToInsert < node.value) {
                if (node.left == null) {
                    node.left = new Node(valueToInsert);
                    break;
                }
                node = node.left;
            } else if (valueToInsert > node.value) {
                if (node.right == null) {
                    node.right = new Node(valueToInsert);
                    break;
                }
                node = node.right;
            } else {
                return;
            }
        }
    }

    void preOrderTraversal(Node v) throws IOException {
        if (v == null) {
            return;
        }
        output.write(v.value + "\n");
        preOrderTraversal(v.left);
        preOrderTraversal(v.right);
    }
}