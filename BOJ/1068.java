// 1068 tree

import java.util.ArrayList;
import java.util.Scanner;

class Node {
    int node;

    public Node(int node) {
        this.node = node;
    }

    public int getNode() {
        return node;
    }
}

public class Main {

    private static int answer = 0;
    private static int root = -1;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        ArrayList<ArrayList<Node>> graph = new ArrayList<>();
        for (int idx = 0; idx < n; idx++) {
            graph.add(idx, new ArrayList<>());
        }

        for (int idx = 0; idx < n; idx++) {
            int nodeIndex = sc.nextInt();

            if (nodeIndex == -1) {
                root = idx;
            } else {
                graph.get(nodeIndex).add(new Node(idx));
            }
        }

        int deleteNode = sc.nextInt();

        if (deleteNode == root) {
            System.out.println(0);
        } else {
            for (int i = 0; i < graph.size(); i++) {
                for (int j = 0; j < graph.get(i).size(); j++) {
                    if (graph.get(i).get(j).getNode() == deleteNode) {
                        graph.get(i).remove(j);
                    }
                }
            }
            
            findLeafNode(graph.get(root), graph);
            System.out.println(answer);
        }
    }

    private static void findLeafNode(ArrayList<Node> visitNode, ArrayList<ArrayList<Node>> graph) {
        if (visitNode.size() == 0) {
            answer ++;
            return;
        }

        for (Node nextNode : visitNode) {
            if (nextNode.getNode() != -1) {
                findLeafNode(graph.get(nextNode.getNode()), graph);
            }
        }
    }
}
