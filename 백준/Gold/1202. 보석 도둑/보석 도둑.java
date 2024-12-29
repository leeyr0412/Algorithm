import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Gem implements Comparable<Gem> {
    private int m;
    private int cost;

    public Gem(int m, int cost) {
        this.m = m;
        this.cost = cost;
    }

    public int getM() {
        return m;
    }

    public int getCost() {
        return cost;
    }

    /**
     * 무게 기준 오름차순
     */
    @Override
    public int compareTo(Gem o) {
        return this.m - o.m;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());   //보석 수
        int K = Integer.parseInt(st.nextToken());   //가방 수

        // 보석 입력받아 무게 가벼운 순으로 정렬
        PriorityQueue<Gem> gems = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            gems.offer(new Gem(m, v));
        }
        
        // 가방 입력, 작은 순으로 정렬
        int[] bags = new int[K];
        for (int i = 0; i < K; i++) {
            bags[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(bags);

        long answer = 0;
        // 가방에 넣을 수 있는 보석 후보들의 가격(내림차순)
        PriorityQueue<Integer> cost = new PriorityQueue<>(Comparator.reverseOrder());
        for (int bag : bags) {  // 작은 가방부터
            while (!gems.isEmpty() && gems.peek().getM() <= bag) {  // 지금 가방에 들어갈 수 있는 보석들 전부 가격 후보에 넣기
                cost.offer(gems.poll().getCost());
            }
            if (!cost.isEmpty()) {  // 후보 중 가장 가치 높은 보석 답에 넣기
                answer += cost.poll();
            }
        }
        System.out.println(answer);
    }
}