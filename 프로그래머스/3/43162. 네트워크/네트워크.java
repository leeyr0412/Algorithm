import java.util.*;

class Solution {
    static boolean [] visited;
    public int solution(int n, int[][] computers) {
        int answer = 0;
        visited = new boolean[n];
        for(int i = 0; i < n; i++){
            if(!visited[i]){
                bfs(i, computers, n);
                answer++;
            }
        }
        return answer;
    }
    
    static public void bfs(int start, int[][] computers, int n){
        visited[start] = true;
        // System.out.println("bfs");
        // print();//
        Queue<Integer> que = new ArrayDeque<>();
        que.offer(start);
        while(!que.isEmpty()){
            int next = que.poll();
            // System.out.println(next+" que out");//
            for(int i = 0; i < n; i++){
                // System.out.println("computers["+next+"]["+i+"] = "+computers[next][i]);//
                if(computers[next][i] == 0){
                    continue;
                }
                if(visited[i] || start == i){
                    continue;
                }
                que.offer(i);
                // System.out.println(i+" que add");//
                visited[i] = true;
                // print();//
            }
        }
    }
    
    // static public void print(){
    //     System.out.print("visited = ");
    //     for(int i = 0; i < 3; i++){
    //         System.out.print(visited[i] + " ");
    //     }
    //      System.out.println();
    // }
}