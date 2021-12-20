import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int height = Integer.parseInt(scanner.nextLine());
        
        ArrayList<ArrayList<Integer>> triangle = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < height; i++) {
            String[] numbers = scanner.nextLine().split(" ");
            ArrayList<Integer> layer = new ArrayList<>();
            for (String temp : numbers) {
                layer.add(Integer.parseInt(temp));
            }
            triangle.add(layer);
        }
        System.out.println(dpForAnswer(height, triangle));
    }

    private static int dpForAnswer(int height, ArrayList<ArrayList<Integer>> triangle) {
        for (int h = 1; h < height; h++) {
            for (int w = 0; w < triangle.get(h).size(); w++) {
                if (w == 0) {
                    triangle.get(h).set(w, triangle.get(h-1).get(w) + triangle.get(h).get(w));
                    continue;
                }
                if (w == triangle.get(h).size() - 1) {
                    triangle.get(h).set(w, triangle.get(h-1).get(w-1) + triangle.get(h).get(w));
                    continue;
                }
                int maxNumber = Math.max(triangle.get(h-1).get(w-1), triangle.get(h-1).get(w));
                triangle.get(h).set(w, maxNumber + triangle.get(h).get(w));
            }
        }
        return Collections.max(triangle.get(triangle.size()-1));
    }
}