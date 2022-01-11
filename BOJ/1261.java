//1261 BFS

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.Queue;
import java.util.LinkedList;

public class Main {

    private static final int[] x = new int[] {-1, 1, 0, 0};
    private static final int[] y = new int[] {0, 0, -1, 1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] info = sc.nextLine().split(" ");
        int n = Integer.parseInt(info[1]);
        int m = Integer.parseInt(info[0]);

        ArrayList<List<Integer>> maps = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            List<Integer> numbers = Arrays.stream(sc.nextLine().split(""))
                    .map(Integer::parseInt).collect(Collectors.toList());
            maps.add(numbers);
        }

        int[][] answer = bfs(0, 0, n, m, maps);
        System.out.println(answer[n-1][m-1]);
    }

    private static int[][] bfs(int startN, int startM, int n, int m, ArrayList<List<Integer>> maps) {
        int[][] wall = new int[n+1][m+1];
        boolean[][] visit = new boolean[n+1][m+1];
        visit[startN][startM] = true;

        Queue<List<Integer>> queue = new LinkedList<>();
        queue.add(Arrays.asList(0, 0));
        while (!queue.isEmpty()) {
            List<Integer> location = queue.poll();
            int locationX = location.get(0);
            int locationY = location.get(1);

            for (int idx = 0; idx < x.length; idx++) {
                int moveX = locationX + x[idx];
                int moveY = locationY + y[idx];

                if (moveX < 0 || moveY < 0 || moveX >= n || moveY >= m) {
                    continue;
                }

                if (!visit[moveX][moveY]) {
                    visit[moveX][moveY] = true;
                    wall[moveX][moveY] = wall[locationX][locationY] + maps.get(moveX).get(moveY);
                    queue.add(Arrays.asList(moveX, moveY));
                } else {
                    if (wall[locationX][locationY] + maps.get(moveX).get(moveY) < wall[moveX][moveY]) {
                        wall[moveX][moveY] = wall[locationX][locationY] + maps.get(moveX).get(moveY);
                        queue.add(Arrays.asList(moveX, moveY));
                    }
                }
            }
        }
        return wall;
    }
}