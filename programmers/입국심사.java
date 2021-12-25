import java.util.Arrays; 

class Solution {
    public long solution(int n, int[] times) {
        Arrays.sort(times);
        long answer = 0;
        long left = 0;
        long right = (long)n * (long)times[times.length-1];
        
        while (left <= right) {
            long mid = (left + right) / 2;
            long peopleCount = 0;
            for (int idx = 0; idx < times.length; idx++) {
                peopleCount += (mid / times[idx]);
            }
            
            if (peopleCount >= n) {
                answer = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return answer;
    }
}
