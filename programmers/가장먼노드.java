import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Arrays;
import java.util.Queue;

class Solution {
    private ArrayList<ArrayList<Integer>> init(int n, int[][] edge) {
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        for (int node = 0; node < n+1; node++) {
            graph.add(node, new ArrayList<>());
        }
        
        for (int idx = 0; idx < edge.length; idx++) {
            graph.get(edge[idx][0]).add(edge[idx][1]);
            graph.get(edge[idx][1]).add(edge[idx][0]);
        }
        return graph;
    }
    
    public int solution(int n, int[][] edge) {
        ArrayList<ArrayList<Integer>> graph = init(n, edge);
        return bfs(n, graph);
    }
    
    private int bfs(int n, ArrayList<ArrayList<Integer>> graph) {
        int[] distance = new int[n+1];
        Queue<Integer> visit = new LinkedList<Integer>();
        visit.add(1);

        while (visit.size() > 0) {
           int node = visit.poll();
            
            for (int idx = 0; idx < graph.get(node).size(); idx++) {
                if (distance[graph.get(node).get(idx)] == 0 && graph.get(node).get(idx) != 1) {
                    distance[graph.get(node).get(idx)] = distance[node] + 1;
                    visit.add(graph.get(node).get(idx));
                }
            }
        }
        return getMostFarNode(distance); 
    }
    
    private int getMostFarNode(int[] distance) {
        Arrays.sort(distance);
        int maxDistance = distance[distance.length-1];
        
        int count = 0;
        for (int node = 1; node < distance.length; node++) {
            if (distance[node] == maxDistance) {
                count ++;
            }
        }
        return count;
    }
}