import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Queue;
import java.util.LinkedList;
import java.util.stream.Collectors;

class Node {
    int row;
    int column;
    int time;
    int crush;

    public Node(int row, int column, int crush, int time) {
        this.row = row;
        this.column = column;
        this.crush = crush;
        this.time = time;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public int getTime() {
        return time;
    }

    public int getCrush() {
        return crush;
    }
}

public class Main {

    public static int[] x = {1, -1, 0, 0};
    public static int[] y = {0, 0, 1, -1};
    public static int INF = (int) 1e9;
    public static int answer = (int) 1e9;

    public static void main(String[] args) {

        ArrayList<List<Integer>> graph = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        String[] info = sc.nextLine().split(" ");
        int r = Integer.parseInt(info[0]);
        int c = Integer.parseInt(info[1]);

        for (int idx = 0; idx < r; idx++) {
            graph.add(Arrays.stream(sc.nextLine().split(""))
                    .map(Integer::parseInt).collect(Collectors.toList()));
        }
        boolean[][] visit = new boolean[r][c];
        boolean[][] crushVisit = new boolean[r][c];
        bfs(r, c, visit, crushVisit, graph);


        if (answer == INF) {
            System.out.println(-1);
        } else {
            System.out.println(answer);
        }
    }

    private static void bfs(int r, int c, boolean[][] visit, boolean[][] crushVisit, ArrayList<List<Integer>> graph) {
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(0, 0, 0, 1));
        visit[0][0] = true;

        while (!q.isEmpty()) {
            Node node = q.poll();

            if (node.getRow() == r - 1 && node.getColumn() == c - 1) {
                answer = node.getTime();
                break;
            }

            for (int idx = 0; idx < 4; idx++) {
                int moveX = node.getRow() + x[idx];
                int moveY = node.getColumn() + y[idx];
                int crush = node.getCrush();
                int time = node.getTime();

                if (moveX < 0 || moveY < 0 || moveX >= r || moveY >= c) {
                    continue;
                }

                if (graph.get(moveX).get(moveY) == 0) {
                    if (crush == 0 && !visit[moveX][moveY]) {
                        visit[moveX][moveY] = true;
                        q.add(new Node(moveX, moveY, 0, time + 1));

                    } else if (crush == 1 && !crushVisit[moveX][moveY]) {
                        crushVisit[moveX][moveY] = true;
                        q.add(new Node(moveX, moveY, 1, time + 1));
                    }

                } else if (graph.get(moveX).get(moveY) == 1) {
                    if (crush == 0) {
                        crushVisit[moveX][moveY] = true;
                        q.add(new Node(moveX, moveY, 1, time + 1));
                    }
                }
            }
        }
    }
}