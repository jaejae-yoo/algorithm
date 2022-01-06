//1504 다익스트라

import java.util.Scanner;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Arrays;

class Node implements Comparable<Node> {
    private int nodeName;
    private int distance;

    public Node(int nodeName, int distance) {
        this.nodeName = nodeName;
        this.distance = distance;
    }

    public int getNodeName() {
        return nodeName;
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

    public static final int INF = (int) 1e9;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int nodeNumber = sc.nextInt();
        int edgeNumber = sc.nextInt();

        ArrayList<ArrayList<Node>> graph = new ArrayList<>();
        for (int i = 0; i < nodeNumber + 1; i++) {
            graph.add(i, new ArrayList<Node>());
        }

        for (int i = 0; i < edgeNumber; i++) {
            int start = sc.nextInt();
            int end = sc.nextInt();
            int cost = sc.nextInt();

            graph.get(start).add(new Node(end, cost));
            graph.get(end).add(new Node(start, cost));
        }

        int firstMustVisit = sc.nextInt();
        int secondMustVisit = sc.nextInt();

        int[] fDistance = dijkstra(nodeNumber, 1, firstMustVisit, graph);
        int[] sDistance = dijkstra(nodeNumber, 1, secondMustVisit, graph);

        int[] firstToSecondAnswer = finalDijkstra(nodeNumber, secondMustVisit, fDistance[secondMustVisit], graph);
        int[] secondToFirstAnswer = finalDijkstra(nodeNumber, firstMustVisit, sDistance[firstMustVisit], graph);

        if (secondToFirstAnswer[nodeNumber] == INF && firstToSecondAnswer[nodeNumber] == INF) {
            System.out.println(-1);
            return;
        }

        if (secondToFirstAnswer[nodeNumber] > firstToSecondAnswer[nodeNumber]) {
            System.out.println(firstToSecondAnswer[nodeNumber]);
        } else {
            System.out.println(secondToFirstAnswer[nodeNumber]);
        }
    }

    private static int[] dijkstra(int n, int start, int end, ArrayList<ArrayList<Node>> graph) {
        int[] distance = new int[n+1];
        Arrays.fill(distance, INF);
        distance[start] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));

        while(!pq.isEmpty()) {
            Node node = pq.poll();

            ArrayList<Node> nodes = graph.get(node.getNodeName());
            for (Node nextNode : nodes) {
                if (distance[nextNode.getNodeName()] > node.getDistance() + nextNode.getDistance()) {
                    distance[nextNode.getNodeName()] = node.getDistance() + nextNode.getDistance();
                    pq.add(new Node(nextNode.getNodeName(), node.getDistance() + nextNode.getDistance()));
                }
            }
        }
        return finalDijkstra(n, end, distance[end], graph);
    }

    private static int[] finalDijkstra(int n, int start, int pasDistance, ArrayList<ArrayList<Node>> graph) {
        int[] distance = new int[n+1];
        Arrays.fill(distance, INF);
        distance[start] = pasDistance;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, pasDistance));

        while(!pq.isEmpty()) {
            Node node = pq.poll();

            ArrayList<Node> nodes = graph.get(node.getNodeName());
            for (Node nextNode : nodes) {
                if (distance[nextNode.getNodeName()] > node.getDistance() + nextNode.getDistance()) {
                    distance[nextNode.getNodeName()] = node.getDistance() + nextNode.getDistance();
                    pq.add(new Node(nextNode.getNodeName(), node.getDistance() + nextNode.getDistance()));
                }
            }
        }
        return distance;
    }
}