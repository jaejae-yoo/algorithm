class Solution {
    public int solution(int n, int[][] computers) {
        int answer = 0;
        boolean[] visited = new boolean[computers.length];
        for (int computer = 0; computer < n; computer++) { 
            if (!visited[computer]) {
                answer += dfs(computers, visited, computer);
            }
        }
        return answer;
    }
    
    private int dfs(int[][] graph, boolean[] visited, int start) {
        visited[start] = true;
        for (int next = 0; next < graph[start].length; next++) {
            if (start != next && graph[start][next] == 1) {
                if (!visited[next]) {
                    dfs(graph, visited, next);
                }
            }
        }
        return 1;
    }
}
