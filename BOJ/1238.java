//1238 다익스트라 (N = 1000 이므로, 플로이드 워셜 불가)

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

class Node implements Comparable<Node> {
    int nodeIndex;
    int time;

    public Node(int nodeIndex, int time) {
        this.nodeIndex = nodeIndex;
        this.time = time;
    }

    public int getNodeIndex() {
        return nodeIndex;
    }

    public int getTime() {
        return time;
    }

    @Override
    public int compareTo(Node o) {
        return getTime() - o.getTime();
    }
}

public class Main {

    private static final int INF = (int) 1e9;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int x = sc.nextInt();

        ArrayList<ArrayList<Node>> graph = new ArrayList<>();
        for (int idx = 0; idx < n+1; idx++) {
            graph.add(idx, new ArrayList<Node>());
        }

        for (int idx = 0; idx < m; idx++) {
            int start = sc.nextInt();
            int end = sc.nextInt();
            int time = sc.nextInt();

            graph.get(start).add(new Node(end, time));
        }

        int[] destinationToNode = dijkstra(n, x, graph);

        int[] nodeToDestination = new int[n+1];
        for (int node = 1; node < n+1; node++) {
            nodeToDestination[node] = dijkstra(n, node, graph)[x];
        }

        int answer = 0;
        for (int idx = 1; idx < n+1; idx++) {
            if ((destinationToNode[idx] + nodeToDestination[idx]) > answer) {
                answer = destinationToNode[idx] + nodeToDestination[idx];
            }
        }
        System.out.println(answer);
    }

    private static int[] dijkstra(int n, int destination, ArrayList<ArrayList<Node>> graph) {
        int[] time = new int[n+1];
        Arrays.fill(time, INF);
        time[destination] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(destination, 0));

        while (!pq.isEmpty()) {
            Node node = pq.poll();

            ArrayList<Node> nodes = graph.get(node.getNodeIndex());
            for (Node nextNode : nodes) {
                if (time[nextNode.getNodeIndex()] > node.getTime() + nextNode.getTime()) {
                    time[nextNode.getNodeIndex()] = node.getTime() + nextNode.getTime();
                    pq.add(new Node(nextNode.getNodeIndex(), time[nextNode.getNodeIndex()]));
                }
            }
        }
        return time;
    }
}
