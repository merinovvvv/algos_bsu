import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class BST implements Runnable{
    Node root;
    FileWriter output;
    Scanner sc;
    File inputFile;
    ArrayList<Integer> filter;

    class Node {
        int value;
        Node left;
        Node right;
        int height;
        int vertexNum;

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
            inputFile = new File("in.txt");
            sc = new Scanner(inputFile);
            filter = new ArrayList<>();
            buildTree();
            calculateHeightAndSize(root);
            filterVertexes(root);
            if (filter.size() % 2 != 0) {
                int res = filter.get(filter.size() / 2);
                remove(res);
            }
            output = new FileWriter("out.txt");
            preOrderTraversal(root);
            output.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    void buildTree() throws IOException {
        while(sc.hasNextInt()) {
            insert(sc.nextInt());
        }
    }

    public void remove(int val) {
        root = removeNode(root, val);
    }

    private Node removeNode(Node node, int val) {
        if (node == null) {
            return null;
        }
        if (val < node.value) {
            node.left = removeNode(node.left, val);
        } else if (val > node.value) {
            node.right = removeNode(node.right, val);
        } else {
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            }

            Node minRightSubtree = findMin(node.right);
            node.value = minRightSubtree.value;
            node.right = removeNode(node.right, minRightSubtree.value);
        }
        return node;
    }

    private Node findMin(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    void insert(int valueToInsert) {
        if (root == null) {
            root = new Node(valueToInsert);
            return;
        }
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

    private void calculateHeightAndSize(Node node) {
        if (node == null) {
            return;
        }
        calculateHeightAndSize(node.left);
        calculateHeightAndSize(node.right);

        int leftHeight = (node.left != null) ? node.left.height : -1;
        int rightHeight = (node.right != null) ? node.right.height : -1;
        node.height = Math.max(leftHeight, rightHeight) + 1;

        int leftSize = (node.left != null) ? node.left.vertexNum : 0;
        int rightSize = (node.right != null) ? node.right.vertexNum : 0;
        node.vertexNum = leftSize + rightSize + 1;
    }

    void filterVertexes(Node root) {
        if (root == null) {
            return;
        }
        filterVertexes(root.left);
        if (root.left != null && root.right != null) {
            if (root.left.height != root.right.height && root.left.vertexNum == root.right.vertexNum) {
                filter.add(root.value);
            }
        }
        filterVertexes(root.right);
    }
}