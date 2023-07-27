import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;// = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            st = new StringTokenizer(br.readLine());
            int buildingNum = Integer.parseInt(st.nextToken());
            int ruleNum = Integer.parseInt(st.nextToken());
            int[] buildTime = new int[buildingNum + 1];
            int[] indegree = new int[buildingNum + 1];
            int[] time = new int[buildingNum + 1];

            //건설 시간
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= buildingNum; i++) {
                buildTime[i] = Integer.parseInt(st.nextToken());
            }

            // 규칙
            List<Integer>[] rule = new ArrayList[buildingNum + 1];
            for (int i = 1; i <= buildingNum; i++) {
                rule[i] = new ArrayList<>();
            }
            for (int i = 0; i < ruleNum; i++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                rule[from].add(to);
                indegree[to]++;
            }

            int gaol = Integer.parseInt(br.readLine());
            Queue<Integer> que = new ArrayDeque<>();

            insertQueue(buildingNum, indegree, que);

            int curr = que.poll();
            while (curr!=gaol){

                time[curr] += buildTime[curr];
                for (Integer to : rule[curr]) {
                    time[to] = Math.max(time[curr],time[to]);
                    indegree[to]--;
                }

                insertQueue(buildingNum, indegree, que);

                curr = que.poll();
            }

            System.out.println(time[curr]+buildTime[curr]);
        }
    }

    private static void insertQueue(int buildingNum, int[] indegree, Queue<Integer> que) {
        for (int i = 1; i <= buildingNum; i++) {
            if (indegree[i] == 0) {
                indegree[i]--;
                que.add(i);
            }
        }
    }
}