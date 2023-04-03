import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 보석 도둑 / 골드2 / 걸린시간 / 4월 3일
 */
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

    @Override
    public int compareTo(Gem o) {
        if (o.m != this.m) {
            return this.m - o.m;
        }
        return o.cost - this.cost;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());   //보석 수
        int bagNum = Integer.parseInt(st.nextToken());   //가방 수
        boolean[] use = new boolean[N];

        PriorityQueue<Gem> gems = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            gems.offer(new Gem(m, v));
        }
//        Collections.sort(gems);
        int[] bags = new int[bagNum];
        for (int i = 0; i < bagNum; i++) {
            bags[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(bags);

        long answer = 0;
        PriorityQueue<Integer> cost = new PriorityQueue<>();
        for (int bag : bags) {
            while (!gems.isEmpty()&&gems.peek().getM()<=bag){
                cost.offer(gems.poll().getCost()*-1);
            }
            if(!cost.isEmpty()) {
                answer += cost.poll()*(-1);
            }
        }
        System.out.println(answer);











//        for (int i = 0; i < bagNum; i++) {
//            int tempCost = 0;
////            int index = -1;
//            for (int j = 0; j < gems.size(); j++) {
//                if(use[j])
//                    continue;
//                if (bags[i] < gems.get(j).getM()) {
//                    break;
//                } else {
//                    if(tempCost <= gems.get(j).getCost()){
//                        tempCost = gems.get(j).getCost();
////                        index = j;
//                        use[j] = true;
//                    }
//                }
//            }
////            if(index <0){
////                continue;
////            }
////            gems.remove(index);
//            answer += tempCost;
//        }
//        System.out.println(answer);
    }
}

/*
4 4
1 100
2 200
13 300
10 500
10
10
10
14
 */