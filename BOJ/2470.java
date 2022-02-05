import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        ArrayList<Integer> array = new ArrayList<>();
        for (int idx = 0; idx < n; idx++) {
            array.add(Integer.parseInt(st.nextToken()));
        }
        Collections.sort(array);

        int leftIdx = 0;
        int rightIdx = array.size() - 1;
        
        int leftNum = (int)1e9;
        int rightNum = (int)1e9;
        int sum = (int)1e9;
        
        while (leftIdx < rightIdx) {
            sum = array.get(leftIdx) + array.get(rightIdx);

            if (Math.abs(array.get(leftIdx) + array.get(rightIdx)) < Math.abs(leftNum + rightNum)) {
                leftNum = array.get(leftIdx);
                rightNum = array.get(rightIdx);
            }

            if (sum < 0) {
                leftIdx ++;
            } else {
                rightIdx --;
            }
        }

        if (leftNum < rightNum) {
            System.out.println(leftNum + " " + rightNum);
        } else {
            System.out.println(rightNum + " " + leftNum);
        }
    }
}