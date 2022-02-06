import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.HashSet;
import java.util.Collections;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());

        ArrayList<List<String>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            List<String> collect = Arrays.stream(sc.nextLine().split("")).collect(Collectors.toList());
            graph.add(collect);
        }
        ArrayList<Integer> answer = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            HashSet<Integer> nodes = new HashSet<>();
            for (int j = 0; j < graph.get(i).size(); j++) {
                if (graph.get(i).get(j).equals("Y")) {
                    nodes.add(j);

                    for (int k = 0; k < n; k++) {
                        if (i == j || j == k || i == k) {
                            continue;
                        }

                        if (graph.get(j).get(k).equals("Y")) {
                            nodes.add(k);
                        }
                    }
                }
                answer.add(nodes.size());
            }
        }
        Collections.sort(answer);
        System.out.println(answer.get(answer.size()-1));
    }
}
