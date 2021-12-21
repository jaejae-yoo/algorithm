class Solution {
    public int solution(int m, int n, int[][] puddles) {
        int[][] maps = new int[n][m];
        maps[0][0] = 1;
        
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < m; c++) {
                if (!isPossible(puddles, r, c)) {
                    continue;
                }
                
                if (c == 0) {
                    maps[r][c] = maps[r-1][c] % 1000000007;
                }
                
                if (r == 0) {
                    maps[r][c] = maps[r][c-1] % 1000000007;
                }

                if (r != 0 && c != 0) {
                    maps[r][c] = (maps[r-1][c] + maps[r][c-1]) % 1000000007;
                }
            }
        }
        return (maps[n-1][m-1] % 1000000007);
    }
    
    private boolean isPossible(int[][] puddles, int r, int c) {
        if (r == 0 && c == 0) {
            return false;
        }
                
        if (!check(puddles, r+1, c+1)) {
            return false;
        }
        return true;
    }
    
    private boolean check(int[][] puddles, int row, int column) {
        for (int idx = 0; idx < puddles.length; idx++) {
            if (puddles[idx][0] == column && puddles[idx][1] == row) {
                return false;
            }
        }
        return true;
    }
}
