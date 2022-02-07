import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Integer> info = Arrays.stream(sc.nextLine().split(" "))
                .map(Integer::parseInt).collect(Collectors.toList());
        int n = info.get(0);
        int m = info.get(1);

        ArrayList<List<Integer>> before = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            List<Integer> temp = Arrays.stream(sc.nextLine().split(""))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
            before.add(temp);
        }
        ArrayList<List<Integer>> after = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            List<Integer> temp = Arrays.stream(sc.nextLine().split(""))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
            after.add(temp);
        }

        calc(n, m, before, after);
    }

    private static void calc(int n, int m, ArrayList<List<Integer>> before, ArrayList<List<Integer>> after) {
        boolean[][] visit = new boolean[n][m];
        boolean possible = true;
        int answer = 0;
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (before.get(i).get(j) != after.get(i).get(j)) {
                    if (i + 3 > n || j + 3 > m) {
                        continue;
                    }
                    answer++;
                    change(i, j, visit, before);
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (before.get(i).get(j) != after.get(i).get(j) && possible) {
                    possible = false;
                    break;
                }
            }
        }

        if (!possible) {
            System.out.println(-1);
        } else {
            System.out.println(answer);
        }
    }

    private static void change(int i, int j, boolean[][] visit, ArrayList<List<Integer>> before) {
        for (int k = 0; k < 3; k++) {
            for (int l = 0; l < 3; l++) {
                visit[i+k][j+l] = true;
            }
        }

        for (int k = 0; k < 3; k++) {
            for (int l = 0; l < 3; l++) {
                if (before.get(i+k).get(j+l) == 0) {
                    before.get(i+k).set(j+l, 1);
                } else {
                    before.get(i+k).set(j+l, 0);
                }
            }
        }
    }
}
