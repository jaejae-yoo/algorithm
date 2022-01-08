//1991 이진트리

import java.util.ArrayList;
import java.util.Scanner;

class Node {
    String node;
    String leftNode;
    String rightNode;

    public Node(String node, String leftNode, String rightNode) {
        this.node = node;
        this.leftNode = leftNode;
        this.rightNode = rightNode;
    }

    public String getNode() {
        return node;
    }

    public String getLeftNode() {
        return leftNode;
    }

    public String getRightNode() {
        return rightNode;
    }
}

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());
        ArrayList<Node> nodes = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String[] info = sc.nextLine().split(" ");
            nodes.add(new Node(info[0], info[1], info[2]));
        }

        for (Node node : nodes) {
            if (node.getNode().equals("A")) {
                preorder(node, nodes);
            }
        }

        System.out.println();

        for (Node node : nodes) {
            if (node.getNode().equals("A")) {
                inorder(node, nodes);
            }
        }

        System.out.println();

        for (Node node : nodes) {
            if (node.getNode().equals("A")) {
                postorder(node, nodes);
            }
        }
    }

    private static void preorder(Node visitNode, ArrayList<Node> nodes) {
        System.out.print(visitNode.getNode());
        for (Node node : nodes) {
            if (node.getNode().equals(visitNode.getLeftNode())) {
                preorder(node, nodes);
            }
        }

        for (Node node : nodes) {
            if (node.getNode().equals(visitNode.getRightNode())) {
                preorder(node, nodes);
            }
        }
    }

    private static void inorder(Node visitNode, ArrayList<Node> nodes) {
        for (Node node : nodes) {
            if (node.getNode().equals(visitNode.getLeftNode())) {
                inorder(node, nodes);
            }
        }

        System.out.print(visitNode.getNode());

        for (Node node : nodes) {
            if (node.getNode().equals(visitNode.getRightNode())) {
                inorder(node, nodes);
            }
        }
    }

    private static void postorder(Node visitNode, ArrayList<Node> nodes) {
        for (Node node : nodes) {
            if (node.getNode().equals(visitNode.getLeftNode())) {
                postorder(node, nodes);
            }
        }

        for (Node node : nodes) {
            if (node.getNode().equals(visitNode.getRightNode())) {
                postorder(node, nodes);
            }
        }

        System.out.print(visitNode.getNode());
    }
}
