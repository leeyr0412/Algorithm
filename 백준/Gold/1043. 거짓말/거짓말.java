import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * 거짓말 / 골드4 / 1시간 / 3월 3일
 */
public class Main {

	static ArrayList<Set<Integer>> link = new ArrayList<>();
	static boolean[] people;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int peopleNum = Integer.parseInt(st.nextToken());
		int partyNum = Integer.parseInt(st.nextToken());
		people = new boolean[peopleNum + 1];

		st = new StringTokenizer(br.readLine());
		int fact = Integer.parseInt(st.nextToken());
		for (int i = 0; i < fact; i++) {
			int p = Integer.parseInt(st.nextToken());
			people[p] = true;
		}
		for (int i = 0; i <= peopleNum; i++) {
			link.add(new HashSet<>());
		}
		ArrayList<ArrayList<Integer>> party = new ArrayList<>();
		
		// 사람끼리 겹치는 그래프, 파티 구하기
		for (int i = 0; i < partyNum; i++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());		//파티 오는 사람 수
			ArrayList<Integer> p = new ArrayList<>();
			int [] joinPeople = new int [num];
			for (int j = 0; j < num; j++) {
				int person = Integer.parseInt(st.nextToken());
				joinPeople[j] = person;
				p.add(person);
			}
			for(int j = 0; j < num; j++) {
				for(int f = 0; f < num; f++) {
					if(j!=f) {
						link.get(joinPeople[j]).add(joinPeople[f]);
					}
				}
			}			
			party.add(p);
		}
		
		visited = new boolean[peopleNum + 1];
		for (int i = 1; i <= peopleNum; i++) {		//진실을 아는 사람 구하기
			if (people[i]) {
				findFact(i, peopleNum);
			}
		}
		int answer = 0;
		for(int i = 0; i < partyNum; i++) {
			boolean flag = false;
			for(int person : party.get(i)) {
				if(people[person]) {//진실을 알고있는 사람
					flag = true;
					break;
				}
			}
			if(!flag) {
				answer++;
			}
		}
		System.out.println(answer);
	}

	static boolean[] visited;

	private static void findFact(int person, int peopleNum) {
		visited[person] = true;
		Deque<Integer> que = new ArrayDeque<Integer>();
		que.offer(person);
		while(!que.isEmpty()) {
			int now = que.poll();
			for(Integer p : link.get(now)) {
				if(!visited[p]) {
					visited[p] = true;
					people[p] = true;
					que.add(p);
				}
			}
		}
	}
}