import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Collectors;

public class Main {

    public static int[] x = {-1, 1, 0, 0};
    public static int[] y = {0, 0, -1, 1};
    public static int answer = (int) 1e9;

    public static void main(String[] args) {

        ArrayList<List<String>> graph = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        String[] info = sc.nextLine().split(" ");
        int r = Integer.parseInt(info[0]);
        int c = Integer.parseInt(info[1]);

        for (int idx = 0; idx < r; idx++) {
            graph.add(Arrays.stream(sc.nextLine().split("")).collect(Collectors.toList()));
        }

        Queue<int[]> q = new LinkedList<>();
        for (int i = 0; i < graph.size(); i++) {
            for (int j = 0; j < graph.get(i).size(); j++) {
                if (graph.get(i).get(j).equals("S")) {
                    q.add(new int[]{0, i, j});
                }
            }
        }

        for (int i = 0; i < graph.size(); i++) {
            for (int j = 0; j < graph.get(i).size(); j++) {
                if (graph.get(i).get(j).equals("*")) {
                    q.add(new int[]{0, i, j});
                }
            }
        }

        moveDochi(r, c, q, graph);

        if (answer == (int)1e9) {
            System.out.println("KAKTUS");
        } else {
            System.out.println(answer);
        }
    }

    private static void moveDochi(int r, int c, Queue<int[]> q, ArrayList<List<String>> graph) {
        while (!q.isEmpty()) {
            int[] dot = q.poll();
            int time = dot[0];
            int dotR = dot[1];
            int dotC = dot[2];

            for (int idx = 0; idx < 4; idx++) {
                int moveX = dotR + x[idx];
                int moveY = dotC + y[idx];

                if (moveX < 0 || moveY < 0 || moveX >= r || moveY >= c) {
                    continue;
                }

                if (graph.get(dotR).get(dotC).equals("*")) {
                    if (graph.get(moveX).get(moveY).equals(".") || graph.get(moveX).get(moveY).equals("S")) {
                        graph.get(moveX).set(moveY, "*");
                        q.add(new int[]{time, moveX, moveY});
                    }
                } else if (graph.get(dotR).get(dotC).equals("S")){
                    if (graph.get(moveX).get(moveY).equals(".")) {
                        graph.get(moveX).set(moveY, "S");
                        q.add(new int[]{time + 1, moveX, moveY});
                    } else if (graph.get(moveX).get(moveY).equals("D")) {
                        answer = time + 1;
                        q.clear();
                        break;
                    }
                }
            }
        }
    }
}
