import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.StringTokenizer;


class Node implements Comparable<Node>{
	private int intdex;
	private int cost;
	public Node(int intdex, int cost) {
		super();
		this.intdex = intdex;
		this.cost = cost;
	}
	
	public int getIntdex() {
		return intdex;
	}
	public int getCost() {
		return cost;
	}

	@Override
	public int compareTo(Node o) {
		return this.getCost() - o.getCost();
	}
	
	
	
}
public class Main {
	
	static int INF = (int) 1e9;
	static ArrayList<ArrayList<Node>> graph = new ArrayList<>();
	static int [] distance ;
	static int V;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		int start = Integer.parseInt(br.readLine());
		
		//그래프 초기화
		for(int i = 0; i <= V; i++) {
			graph.add(new ArrayList<>());
		}
		//간선 입력받기
		for(int e = 0; e < E; e++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int dis = Integer.parseInt(st.nextToken());
			graph.get(from).add(new Node(to, dis));
		}
		distance = new int[V+1];
		Arrays.fill(distance, INF);
		
		dijkstra(start);
		
		for(int i = 1; i <=V; i++) {
			if(distance[i]!=INF) {
				System.out.println(distance[i]);
			}else {
				System.out.println("INF");
			}
		}
	}
	private static void dijkstra(int start) {
		boolean [] use = new boolean[V+1];
		distance[start] = 0;
		PriorityQueue<Node> pque = new PriorityQueue<>();
		pque.add(new Node(start, 0));
//		for(Node vertex : graph.get(start)) {
//			distance[vertex.getIntdex()] = vertex.getCost();
//		}
		while(!pque.isEmpty()) {
			Node node = pque.poll();
			int vertex = node.getIntdex();
			int dis  = node.getCost();
			use[vertex] = true;
			if(distance[vertex]<dis)
				continue;
			for(Node n : graph.get(vertex)) {
				if(distance[n.getIntdex()]> distance[vertex]+n.getCost()) {
					distance[n.getIntdex()] =distance[vertex]+n.getCost();
					pque.add(new Node(n.getIntdex(), distance[vertex]+n.getCost()));
				}
			}
			
		}
	}
}