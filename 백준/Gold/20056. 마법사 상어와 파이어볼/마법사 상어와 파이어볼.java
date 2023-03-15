import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.StringTokenizer;

/**
 * 마법사 상어와 파이어볼 / 난이도 / 30  / 3월 3일 https://www.acmicpc.net/problem/20056
 */
class FireBall {
    private int r, c, m, d, speed;

    public FireBall(int r, int c, int m, int d, int speed) {
        this.r = r;
        this.c = c;
        this.m = m;
        this.d = d;
        this.speed = speed;
    }

    public int getR() {
        return r;
    }

    public int getC() {
        return c;
    }

    public int getM() {
        return m;
    }

    public int getD() {
        return d;
    }

    public int getSpeed() {
        return speed;
    }
}

class Pos {
    private int r, c;
    private FireBall ball;

    public Pos(int r, int c, FireBall ball) {
        this.r = r;
        this.c = c;
        this.ball = ball;
    }

    public int getR() {
        return r;
    }

    public int getC() {
        return c;
    }

    public FireBall getBall() {
        return ball;
    }

}

public class Main {
	static int[][] dr = {{-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}};
    static int N;
    static ArrayList<FireBall>[][] map;
    static Deque<Pos> ballList = new ArrayDeque<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        map = new ArrayList[N + 1][N + 1];
        for(int r = 1; r <N+1;r++){
            for(int c = 1; c<N+1; c++){
                map[r][c] = new ArrayList<>();
            }
        }
        int ballNum = Integer.parseInt(st.nextToken());
        int commandNum = Integer.parseInt(st.nextToken());
        for (int i = 0; i < ballNum; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int speed = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
//            map[r][c].add(new FireBall(r, c, m, d, speed));
            ballList.add(new Pos(r, c, new FireBall(r, c, m, d, speed)));
        }

        for (int command = 0; command < commandNum; command++) {
            //이동
            for (Pos pos : ballList) {
                int d = pos.getBall().getD();
//                        map[r][c].get(0).getD();
                int speed = pos.getBall().getSpeed();
//                int speed = map[r][c].get(0).getSpeed();
                int newR = pos.getR() + dr[d][0] * speed;
                if (newR == 0 || newR % N == 0) {
                    newR = N;
                } else if (newR < 0) {
                    newR = newR % N + N;
                } else {
                    newR = newR % N;
                }
                int newC = pos.getC() + dr[d][1] * speed;
                if (newC == 0 || newC % N == 0) {
                    newC = N;
                } else if (newC < 0) {
                    newC = newC % N + N;
                } else {
                    newC = newC % N;
                }

                map[newR][newC].add(new FireBall(newR, newC, pos.getBall().getM(), d, speed));
            }
            ballList = new ArrayDeque<>();
            //합치기
            for (int r = 1; r <= N; r++) {
                for (int c = 1; c <= N; c++) {
                    if (map[r][c].size() > 1) {
//                        int same = map[r][c].get(0).getD() % 2; //짝수면 0, 홀수면 1
                        int even = 0;
                        int odd = 0;
                        int M = 0;
                        int speed = 0;
                        for (FireBall ball : map[r][c]) {
                            M += ball.getM();
                            speed += ball.getSpeed();
                            int d = ball.getD() % 2;
                            if(d ==0) {
                            	even++;
                            }else {
                            	odd++;
                            }
                        }
                        if (M / 5 > 0) {
                            if (even*odd == 0) { //방향 같음
                                ballList.add(new Pos(r, c, new FireBall(r, c, M / 5, 0, speed / map[r][c].size())));
                                ballList.add(new Pos(r, c, new FireBall(r, c, M / 5, 2, speed / map[r][c].size())));
                                ballList.add(new Pos(r, c, new FireBall(r, c, M / 5, 4, speed / map[r][c].size())));
                                ballList.add(new Pos(r, c, new FireBall(r, c, M / 5, 6, speed / map[r][c].size())));
                            } else {
                                ballList.add(new Pos(r, c, new FireBall(r, c, M / 5, 1, speed / map[r][c].size())));
                                ballList.add(new Pos(r, c, new FireBall(r, c, M / 5, 3, speed / map[r][c].size())));
                                ballList.add(new Pos(r, c, new FireBall(r, c, M / 5, 5, speed / map[r][c].size())));
                                ballList.add(new Pos(r, c, new FireBall(r, c, M / 5, 7, speed / map[r][c].size())));

                            }
                        } else {  //질량 0이면 사라짐
                            map[r][c] = new ArrayList<>();
                        }
                        map[r][c] = new ArrayList<>();
                    } else if (map[r][c].size() == 1) {
                        ballList.add(new Pos(r, c, map[r][c].get(0)));
                        map[r][c].remove(0);
                    }
                }
            }
        }       //명령 끝

        int answer = 0;
        for (Pos pos : ballList) {
            answer+= pos.getBall().getM();
        }
        System.out.println(answer);


    }
}