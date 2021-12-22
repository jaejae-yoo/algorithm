import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    static int[] row = {-1, 1, 0, 0};
    static int[] column = {0, 0, -1, 1};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] size = scanner.nextLine().split(" ");

        ArrayList<List<Integer>> frame = new ArrayList<List<Integer>>();
        for (int idx = 0; idx < Integer.parseInt(size[0]); idx++) {
            ArrayList<String> row = new ArrayList(Arrays.asList(scanner.nextLine().split(" ")));
            List<Integer> collect = row.stream()
                    .map(Integer::valueOf)
                    .collect(Collectors.toList());
            frame.add(collect);
        }

        int count = 0;
        int pastCheese = isExistCheese(frame);
        while (true) {
            boolean[][] boundary = findBoundary(frame);
            melt(frame, boundary);
            int existCheese = isExistCheese(frame);
            count ++;
            
            if (existCheese == 0) {
                System.out.println(count);
                System.out.println(pastCheese);
                break;
            }
            pastCheese = existCheese;
        }
    }

    private static int isExistCheese(ArrayList<List<Integer>> frame) {
        int cheese = 0;
        for (int i = 0; i < frame.size(); i++) {
            for (int j = 0; j < frame.get(i).size(); j++) {
                if (frame.get(i).get(j) == 1) {
                    cheese ++;
                }
            }
        }
        if (cheese > 0) {
            return cheese;
        }
        return 0;
    }

    private static boolean[][] findBoundary(ArrayList<List<Integer>> frame) {
        Queue<List<Integer>> queue = new LinkedList<List<Integer>>();
        boolean[][] boundary = new boolean[frame.size()][frame.get(0).size()];
        queue.add(new ArrayList<>(Arrays.asList(0, 0)));

        while (queue.size() > 0) {
            List<Integer> location = queue.poll();
            int x = location.get(0);
            int y = location.get(1);

            for (int idx = 0; idx < row.length; idx++) {
                int moveX = x + row[idx];
                int moveY = y + column[idx];

                if (moveX < 0 || moveY < 0 || moveX >= frame.size() || moveY >= frame.get(0).size()) {
                    continue;
                }

                if (isBoundary(frame, boundary, moveX, moveY)) {
                    boundary[moveX][moveY] = true;
                    queue.add(Arrays.asList(moveX, moveY));
                }
            }
        }
        return boundary;
    }

    private static boolean isBoundary(ArrayList<List<Integer>> frame, boolean[][] boundary, int x, int y) {
        if (frame.get(x).get(y) == 0) {
            if (!boundary[x][y]) {
                return true;
            }
        }
        return false;
    }

    private static void melt(ArrayList<List<Integer>> frame, boolean[][] boundary) {
        for (int x = 0; x < frame.size(); x++) {
            for (int y = 0; y < frame.get(x).size(); y++) {

                for (int idx = 0; idx < row.length; idx++) {
                    int moveX = x + row[idx];
                    int moveY = y + column[idx];

                    if (moveX < 0 || moveY < 0 || moveX >= frame.size() || moveY >= frame.get(0).size()) {
                        continue;
                    }

                    if (boundary[moveX][moveY]) {
                        frame.get(x).set(y, 0);
                        break;
                    }
                }
            }
        }
    }
}
