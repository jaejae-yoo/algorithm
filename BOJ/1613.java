//플로이드 워셜 1613

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int k = sc.nextInt();

        int[][] graph = new int[n+1][n+1];
        for (int idx = 0; idx < n+1; idx++){
            Arrays.fill(graph[idx], 0);
        }

        for (int idx =0; idx < k; idx++){
            int start = sc.nextInt();
            int end = sc.nextInt();

            graph[start][end] = 1;
            graph[end][start] = -1;
        }

        ArrayList<ArrayList<Integer>> exam = new ArrayList<>();
        int s = sc.nextInt();
        for (int idx =0; idx < s; idx++){
            int start = sc.nextInt();
            int end = sc.nextInt();

            exam.add(new ArrayList<>(Arrays.asList(start, end)));
        }

        for (int a = 1; a < n+1; a++) {
            for (int b = 1; b < n + 1; b++) {
                if (a == b) {
                    graph[a][b] = 0;
                }
            }
        }

        for (int c = 1; c < n+1; c++) {
            for (int a = 1; a < n+1; a++) {
                for (int b = 1; b < n+1; b++) {
                    if (graph[a][b] == 0) {
                        if (graph[a][c] > 0 && graph[c][b] > 0) {
                            graph[a][b] = graph[a][c] + graph[c][b];
                        } else if (graph[a][c] < 0 && graph[c][b] < 0) {
                            graph[a][b] = graph[a][c] + graph[c][b];
                        }
                    }
                }
            }
        }

        for (int idx = 0; idx < exam.size(); idx++) {
            if ((graph[exam.get(idx).get(0)][exam.get(idx).get(1)] == 0)) {
                System.out.println(0);
                continue;
            }

            if (graph[exam.get(idx).get(0)][exam.get(idx).get(1)] > 0) {
                System.out.println(-1);
            } else {
                System.out.println(1);
            }
        }
    }
}