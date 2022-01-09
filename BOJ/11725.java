// 11725 tree

import java.util.ArrayList;
import java.util.Scanner;

class Node {
    int nodeIdx;

    public Node(int nodeIdx) {
        this.nodeIdx = nodeIdx;
    }

    public int getNodeIdx() {
        return nodeIdx;
    }
}

public class Main {

    private static int[] parents;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        parents = new int[n+1];

        ArrayList<ArrayList<Node>> graph = new ArrayList<>();
        for (int idx = 0; idx < n+1; idx++) {
            graph.add(idx, new ArrayList<>());
        }

        for (int idx = 0; idx < n-1; idx++) {
            int a = sc.nextInt();
            int b = sc.nextInt();

            graph.get(a).add(new Node(b));
            graph.get(b).add(new Node(a));
        }

        treeSearch(1, graph, 0);

        for (int idx = 2; idx < parents.length; idx++) {
            System.out.println(parents[idx]);
        }
    }

    private static void treeSearch(int visit, ArrayList<ArrayList<Node>> graph, int parent) {
        parents[visit] = parent;

        ArrayList<Node> nodes = graph.get(visit);
        if (nodes.size() <= 1 && visit != 1) {
            return;
        }

        for (Node nextNode : nodes) {
            if (nextNode.getNodeIdx() != parent) {
                treeSearch(nextNode.getNodeIdx(), graph, visit);
            }
        }
        return;
    }
}