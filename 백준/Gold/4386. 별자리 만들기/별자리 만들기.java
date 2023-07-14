import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Pos {
    private int x;
    private int y;

    public Pos(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}

public class Main {
    static int INF = (int) 1e9;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int starNum = Integer.parseInt(br.readLine());
        List<Pos> stars = new ArrayList<>();
        for (int i = 0; i < starNum; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int x = (int) (Double.parseDouble(st.nextToken()) )* 100;
            int y = (int) (Double.parseDouble(st.nextToken())) * 100;
            stars.add(new Pos(x, y));
        }
        int[] distance = new int[starNum];
        boolean[] visited = new boolean[starNum];

        int curr = 0;
        visited[curr] = true;
        for (int i = 1; i < starNum; i++) {
            distance[i] = INF;
        }
        int answer = 0;
        int checkNum = 1;
        while (checkNum < starNum) {
            // 기준별과 다른 별들 사이 거리 구해 최솟값으로 초기화 해두기
            for (int end = 0; end < starNum; end++) {
                if (end == curr) {
                    continue;
                }
                if (visited[end]) {
                    continue;
                }
                int nowX = stars.get(curr).getX();
                int nowY = stars.get(curr).getY();
                int toX = stars.get(end).getX();
                int toY = stars.get(end).getY();
                int dist = (int) Math.sqrt(Math.pow(nowX - toX, 2)
                        + Math.pow(nowY - toY, 2));
                distance[end] = Math.min(dist, distance[end]);
            }

            // 이을 선 찾아 연결
            int plus = INF;
            int next = -1;
            for (int i = 0; i < starNum; i++) {
                if (!visited[i]) {
                    if (distance[i] < plus) {
                        next = i;
                        plus = distance[i];
                    }
                }
            }
            answer += plus;
            visited[next] = true;
            checkNum++;
            curr = next;
        }

        System.out.println((double) answer/100);
    }
}