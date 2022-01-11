//5972 dijkstra

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

class Node implements Comparable<Node> {
    int nodeIdx;
    int distance;

    public Node(int nodeIdx, int distance) {
        this.nodeIdx = nodeIdx;
        this.distance = distance;
    }

    public int getNodeIdx() {
        return nodeIdx;
    }

    public int getDistance() {
        return distance;
    }

    @Override
    public int compareTo(Node o) {
        return getDistance() - o.getDistance();
    }
}

public class Main {

    private static final int INF = (int) 1e9;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        ArrayList<ArrayList<Node>> graph = new ArrayList<>();
        for (int idx = 0; idx < n+1; idx++) {
            graph.add(new ArrayList<>());
        }

        for (int idx = 0; idx < m; idx++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int c = sc.nextInt();

            graph.get(a).add(new Node(b, c));
            graph.get(b).add(new Node(a, c));
        }

        int[] answer = dijkstra(1, n, graph);
        System.out.println(answer[n]);
    }

    private static int[] dijkstra(int start, int n, ArrayList<ArrayList<Node>> graph) {
        int[] distance = new int[n+1];
        Arrays.fill(distance, INF);
        distance[start] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(1, 0));

        while(!pq.isEmpty()) {
            Node node = pq.poll();

            ArrayList<Node> nodes = graph.get(node.getNodeIdx());
            for (Node nextNode : nodes) {
                if (distance[nextNode.getNodeIdx()] > distance[node.getNodeIdx()] + nextNode.getDistance()) {
                    distance[nextNode.getNodeIdx()] = distance[node.getNodeIdx()] + nextNode.getDistance();
                    pq.add(new Node(nextNode.getNodeIdx(), distance[nextNode.getNodeIdx()]));
                }
            }
        }
        return distance;
    }
}