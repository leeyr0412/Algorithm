import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Pos1 {
    int x;
    int y;

    public Pos1(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}

public class Main {
    static int[][] originalMap; //초기맵
    static boolean[][] visitedMap; //방문 확인
    static List<Pos1> safePos = new ArrayList<>();
    static int result =0;
    static int N =0;
    static int M =0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

         N = Integer.parseInt(st.nextToken());
         M = Integer.parseInt(st.nextToken());
        originalMap = new int[N][M];
        visitedMap = new boolean[N][M];

        int safeCount = 0;
        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < M; c++) {
                originalMap[r][c] = Integer.parseInt(st.nextToken());
                if (originalMap[r][c] == 0) {
                    safeCount++;
                    safePos.add(new Pos1(c, r));
                }
            }
        }

        //조합 구하기
        //0좌표 중 3개 고르기
        numbers = new int[3];
        comi(safeCount, 3, 0, 0);
        System.out.println(result);
    }


    static int[] numbers;

    private static void comi(int safeCount, int needs, int cnt, int start) {
        if (cnt == needs) {//안전 구역 중 3개 좌표 고름
            int[][] copyMap = new int[originalMap.length][];
            for (int r = 0; r < originalMap.length; r++) {
                copyMap[r] = originalMap[r].clone();
            }
            //고른 좌표 3개 벽으로 만들기
            for (int i = 0; i < 3; i++) {
                Pos1 p = safePos.get(numbers[i]);
                copyMap[p.getY()][p.getX()] = 1;
            }
            result=Math.max(getResult(copyMap),result);
            return;
        }
        for (int i = start; i < safeCount; i++) {
            numbers[cnt] = i;
            comi(safeCount, needs, cnt + 1, i + 1);
        }
    }

    private static int getResult(int[][] copyMap) {
        int temp = 0;   //0의 수
        visitedMap = new boolean[N][M];
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                if(copyMap[r][c] == 2){
                    bfs(r,c, copyMap);
                }
            }
        }
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                if(copyMap[r][c]==0)
                    temp++;
            }
        }
        return temp;
    }

    private static void bfs(int r, int c, int[][] copyMap) {
        int [][] dr = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        Deque<Pos1> que = new ArrayDeque<>();
        que.offer(new Pos1(c,r));
        while (!que.isEmpty()){
            Pos1 p = que.poll();
            for( int[]d :dr) {
                int newR = p.getY() + d[0];
                int newC = p.getX() + d[1];
                if(newC >=0&&newC<M&&newR>=0&&newR<N){
                    if(copyMap[newR][newC]==0){
                        que.offer(new Pos1(newC, newR));
                        copyMap[newR][newC] = 2;
                    }
                }
            }
        }
    }

}