import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/**
 * 파이프옮기기1_이예리 / 골드5 / 30분 / 3월 29일
 */
class Point {
    private int r;
    private int c;
    private int d;  //방향 0:가로, 1:대각선, 2: 세로

    public Point(int r, int c, int d) {
        this.r = r;
        this.c = c;
        this.d = d;
    }

    public int getD() {
        return d;
    }

    public int getR() {
        return r;
    }

    public int getC() {
        return c;
    }
}

public class Main {
    static int answer = 0;
    static int[][] map;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;// = new StringTokenizer(br.readLine());
        StringBuffer sb = new StringBuffer();

        N = Integer.parseInt(br.readLine());
        map = new int[N + 1][N + 1];

        // 맵 입력받기
        for (int r = 1; r <= N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 1; c <= N; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }


        bfs(N);
        System.out.println(answer);
    }

    private static void bfs(int n) {
        //현재 좌표 지정
        int currR = 1;
        int currC = 2;
        Deque<Point> que = new ArrayDeque<>();
        que.add(new Point(currR, currC, 0));
        while (!que.isEmpty()) {
            Point curr = que.poll();
            if (curr.getR() == n && curr.getC() == n) {
                answer++;
                continue;
            }
            int newR;
            int newC;

            //가로 상황
            if (curr.getD() == 0) {
                //가로로 이동
                newR = curr.getR();
                newC = curr.getC() + 1;
                if (check(newR, newC, 0)) {
                    que.add(new Point(newR, newC, 0));
                }
                //대각선
                newR = curr.getR() + 1;
                newC = curr.getC() + 1;
                if (check(newR, newC, 1)) {
                    que.add(new Point(newR, newC, 1));
                }
            } else if (curr.getD() == 1) {    //대각선
                //가로로 이동
                newR = curr.getR();
                newC = curr.getC() + 1;
                if (check(newR, newC, 0)) {
                    que.add(new Point(newR, newC, 0));
                }
                //대각선
                newR = curr.getR() + 1;
                newC = curr.getC() + 1;
                if (check(newR, newC, 1)) {
                    que.add(new Point(newR, newC, 1));
                }
                // 세로로 이동
                newR = curr.getR()+1;
                newC = curr.getC();
                if (check(newR, newC, 2)) {
                    que.add(new Point(newR, newC, 2));
                }
            } else {     //세로
                // 세로로 이동
                newR = curr.getR()+1;
                newC = curr.getC();
                if (check(newR, newC, 2)) {
                    que.add(new Point(newR, newC, 2));
                }
                //대각선
                newR = curr.getR() + 1;
                newC = curr.getC() + 1;
                if (check(newR, newC, 1)) {
                    que.add(new Point(newR, newC, 1));
                }
            }
        }
    }

    private static boolean check(int newR, int newC, int d) {
        //범위 탐색
        if (newR > 0 && newR <= N && newC > 0 && newC <= N) {
            if (d == 1) {  //대각선
                return (map[newR][newC] == 0 && map[newR - 1][newC] == 0 && map[newR][newC - 1] == 0);
            } else { //세로, 가로
                return map[newR][newC] == 0;
            }
        }
        return false;
    }
}