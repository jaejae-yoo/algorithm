import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static int mostFar = 0;
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] size = scanner.nextLine().split(" ");

        ArrayList<List<String>> frame = new ArrayList<List<String>>();
        for (int idx = 0; idx < Integer.parseInt(size[0]); idx++) {
            ArrayList<String> row = new ArrayList(Arrays.asList(scanner.nextLine().split("")));
            frame.add(row);
        }
        ArrayList<List<Integer>> land = findLand(frame);
        findMostFar(frame, land);

        System.out.println(mostFar);
    }

    private static void findMostFar(ArrayList<List<String>> frame, ArrayList<List<Integer>> land) {
        for (int i = 0; i < land.size(); i++) {
            if (frame.get(land.get(i).get(0)).get(land.get(i).get(1)).equals("L")) {
                bfs(frame, land.get(i).get(0), land.get(i).get(1));
            }
        }
    }

    private static void bfs(ArrayList<List<String>> frame, int r, int c) {
        int[] row = {-1, 1, 0, 0};
        int[] column = {0, 0, -1, 1};
        boolean[][] visit = new boolean[frame.size()][frame.get(0).size()];
        Queue<List<Integer>> queue = new LinkedList<List<Integer>>();

        visit[r][c] = true;
        queue.add(Arrays.asList(r, c, 0));

        while (queue.size() > 0) {
            List<Integer> location = queue.poll();
            int x = location.get(0);
            int y = location.get(1);
            int distance = location.get(2);
            for (int idx = 0; idx < row.length; idx++) {
                int moveX = x + row[idx];
                int moveY = y + column[idx];

                if (moveX < 0 || moveY < 0 || moveX >= frame.size() || moveY >= frame.get(0).size()) {
                    continue;
                }

                if (frame.get(moveX).get(moveY).equals("W")) {
                    continue;
                }

                if (!visit[moveX][moveY]) {
                    visit[moveX][moveY] = true;
                    queue.add(Arrays.asList(moveX, moveY, distance+1));

                    if (distance+1 > mostFar) {
                        mostFar = distance+1;
                    }
                }
            }
        }
    }
    
    private static ArrayList<List<Integer>> findLand(ArrayList<List<String>> frame) {
        ArrayList<List<Integer>> land = new ArrayList<List<Integer>>();

        for (int i = 0; i < frame.size(); i++) {
            for (int j = 0; j < frame.get(i).size(); j++) {
                if (frame.get(i).get(j).equals("L")) {
                    land.add(Arrays.asList(i, j));
                }
            }
        }
        return land;
    }
}