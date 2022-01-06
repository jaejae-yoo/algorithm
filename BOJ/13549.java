// 13549 BFS

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Node {
    int location;
    int time;

    public Node(int location, int time) {
        this.location = location;
        this.time = time;
    }

    public int getLocation() {
        return location;
    }

    public int getTime() {
        return time;
    }
}

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int start = sc.nextInt();
        int end = sc.nextInt();

        int[] answer = bfs(start, end);
        System.out.println(answer[end]);
    }

    private static int[] bfs(int start, int end) {
        boolean[] visit = new boolean[10000001];
        int[] locations = new int[10000001];
        locations[start] = 0;
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(start, 0));

        while(!queue.isEmpty()) {
            Node node = queue.poll();

            if (node.getLocation() == end) {
                return locations;
            }

            if (node.getLocation() * 2 < visit.length && !visit[node.getLocation() * 2]) {
                queue.add(new Node(node.getLocation() * 2, node.getTime()));
                locations[node.getLocation() * 2] = node.getTime();
                visit[node.getLocation() * 2] = true;
            }

            if (node.getLocation() - 1 >= 0 && !visit[node.getLocation() - 1]) {
                queue.add(new Node(node.getLocation() - 1, node.getTime() + 1));
                locations[node.getLocation() - 1] = node.getTime() + 1;
                visit[node.getLocation() - 1] = true;
            }

            if (node.getLocation() + 1 < visit.length && !visit[node.getLocation() + 1] ) {
                queue.add(new Node(node.getLocation() + 1, node.getTime() + 1));
                locations[node.getLocation() + 1] = node.getTime() + 1;
                visit[node.getLocation() + 1] = true;
            }
        }
        return locations;
    }
}
