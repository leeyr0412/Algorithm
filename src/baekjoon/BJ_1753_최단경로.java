package yeri.algorithm0209;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BJ_1753_최단경로 {
	static int[][] graph;
	static int[] cost;
	static int start;
	static int V;
	static ArrayList<Integer> que = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		start = Integer.parseInt(br.readLine());
		graph = new int[V + 1][V + 1];
		cost = new int[V + 1];
		for (int i = 1; i <= V; i++) {
			cost[i] = Integer.MAX_VALUE;
		}

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int v1 = Integer.parseInt(st.nextToken());
			int v2 = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			graph[v1][v2] = c;
			graph[v2][v1] = c;
		}

		que.add(start);
		bfs(start, 0);

		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= V; i++) {
			if (cost[i] == Integer.MAX_VALUE && i != start)
				sb.append("INF").append("\n");
			else
				sb.append(cost[i ]).append("\n");
		}
		System.out.println(sb);
	}

	private static void bfs(int curr, int sum) {
		que.remove(0);
		if (curr == start) {
			cost[curr]= 0;
		}
		for (int i = 0; i < V; i++) {
			if (graph[curr][i] != 0 && cost[i] > graph[curr][i] + sum) {
				cost[i] = graph[curr][i] + sum;
				que.add(i);
			}
		}
		if(que.size()>0)
			bfs(que.get(0), sum + graph[que.get(0)][curr]);
	}

}
