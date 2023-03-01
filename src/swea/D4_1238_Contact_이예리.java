package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.StringTokenizer;

/**
 * Contact / D4 / 1시간 /
 */

class Person {
    int index;
    int count;

    public Person(int index, int count) {
        this.index = index;
        this.count = count;
    }

    public int getIndex() {
        return index;
    }

    public int getCount() {
        return count;
    }
}

public class D4_1238_Contact_이예리 {
    static int answer;
    static int tempCount;
    static ArrayList<Integer>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for (int tc = 1; tc <= 10; tc++) {
            answer = 0;
            graph = new ArrayList[101];
            st = new StringTokenizer(br.readLine());
            int edgeNum = Integer.parseInt(st.nextToken()) / 2;
            int start = Integer.parseInt(st.nextToken());
            for (int i = 0; i < 101; i++) {
                graph[i] = new ArrayList<>();
            }
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < edgeNum; i++) {
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                graph[from].add(to);
            }
            bfs(start);
            System.out.println("#" + tc + " " + answer);
        }
    }

    private static void bfs(int start) {
        Deque<Person> que = new ArrayDeque<>();
        boolean[] visited = new boolean[101];
        que.offer(new Person(start, 0));
        tempCount = 0;
        visited[start] = true;
        while (!que.isEmpty()) {
            Person p = que.poll();
            if (tempCount == p.getCount()) {
                if (p.getIndex() > answer)
                    answer = p.getIndex();
            } else if (tempCount < p.getCount()) {
                answer = p.getIndex();
                tempCount = p.getCount();
            }
            for (Integer i : graph[p.index]) {
                if (!visited[i]) {
                    que.offer(new Person(i, tempCount + 1));
                    visited[i] = true;
                }
            }
        }
    }
}
