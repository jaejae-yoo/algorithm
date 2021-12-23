import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.List;

public class Main {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int cases = Integer.parseInt(scanner.nextLine());

        for (int c = 0; c < cases; c++) {
            String functions = scanner.nextLine();
            int numbers = Integer.parseInt(scanner.nextLine());
            String numberArrayTemp = scanner.nextLine();
            Deque<String> numberArray = new LinkedList<String>(Arrays.asList(numberArrayTemp.substring(1, numberArrayTemp.length() - 1).split(",")));
            startFunction(Arrays.asList(functions.split("")), numberArray);
        }
    }

    private static void startFunction(List<String> functions, Deque<String> numberArray) {
        boolean pass = true;
        boolean reverse = false;
        for (String function : functions) {
            if (function.equals("R")) {
                reverse = !reverse;
            }

            if (function.equals("D")) {
                if (numberArray.size() == 0 || numberArray.peek().equals("")) {
                    System.out.println("error");
                    pass = false;
                    break;
                }
                if (reverse) {
                    numberArray.removeLast();
                }
                if (!reverse) {
                    numberArray.removeFirst();
                }
            }
        }

        if (pass) {
            printAnswer(reverse, numberArray);
        }
    }

    private static void printAnswer(boolean reverse, Deque<String> numberArray) {
        StringBuilder sb = new StringBuilder("[");
        while (!numberArray.isEmpty()) {
            sb.append(reverse ? numberArray.removeLast() : numberArray.removeFirst());
            if (numberArray.size() != 0)
                sb.append(',');
        }
        sb.append(']');
        System.out.println(sb.toString());
    }
}