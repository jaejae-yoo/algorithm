import java.util.Scanner;
import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.stream.Collectors;

class Node implements Comparable<Node> {
    private int node;
    private int distance;

    public Node(int node, int distance) {
        this.node = node;
        this.distance = distance;
    }

    public int getNode() {
        return node;
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
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] info = scanner.nextLine().split(" ");
        int start = Integer.parseInt(scanner.nextLine());

        ArrayList<ArrayList<Node>> graph = new ArrayList<>();
        for (int i = 0; i < Integer.parseInt(info[0]) + 1; i++) {
            graph.add(i, new ArrayList<Node>());
        }
        for (int i = 0; i < Integer.parseInt(info[1]); i++) {
            List<Integer> node = Arrays.stream(scanner.nextLine().split(" "))
                    .map(Integer::parseInt).collect(Collectors.toList());

            graph.get(node.get(0)).add(new Node(node.get(1), node.get(2)));
            graph.get(node.get(0)).add(new Node(node.get(0), node.get(2)));
        }

        int[] answer = dijkstra(Integer.parseInt(info[0]), graph, start);
        for (int idx = 1; idx < answer.length; idx++) {
            if (answer[idx] == Integer.MAX_VALUE) {
                System.out.println("INF");
            } else {
                System.out.println(answer[idx]);
            }
        }
    }

    private static int[] dijkstra(int n, ArrayList<ArrayList<Node>> graph, int start) {
        int[] distance = new int[n+1];
        for (int idx = 0; idx < distance.length; idx++) {
            if (idx != start) {
                distance[idx] = Integer.MAX_VALUE;
            }
        }

        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.add(new Node(start, 0));
        while (queue.size() > 0) {
            Node node = queue.poll();

            if (node.getDistance() > distance[node.getNode()]) {
                continue;
            }

            ArrayList<Node> nextNodes = graph.get(node.getNode());
            for (Node nextNode : nextNodes) {
                if (distance[nextNode.getNode()] > distance[node.getNode()] + nextNode.getDistance()) {
                    distance[nextNode.getNode()] = distance[node.getNode()] + nextNode.getDistance();
                    queue.add(new Node(nextNode.getNode(), distance[nextNode.getNode()]));
                }
            }
        }
        return distance;
    }
}