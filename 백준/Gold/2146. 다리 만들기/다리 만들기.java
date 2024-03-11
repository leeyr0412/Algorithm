import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Pos {
    int r;
    int c;

    public Pos(int r, int c) {
        this.r = r;
        this.c = c;
    }
}

public class Main {
    static int[][] arr;
    static int i_cnt = 0; // 섬 수
    static int[][] dr = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    static int N;
    static int result ;
//    static int result = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];
        for (int y = 0; y < N; y++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int x = 0; x < N; x++) {
                arr[y][x] = Integer.parseInt(st.nextToken());
            }
        }
        result = 2*N -1;

        //섬 수 찾기
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (arr[r][c] == 1) {
                    i_cnt++;
                    findIsland(r, c);
                }
            }
        }

        //거리 찾기
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (arr[r][c] < 0 ) {    //현재 위치가 섬
                    int temp = bfs(r, c, arr[r][c]);
                    if (temp <= 0) {
                        continue;
                    }
                    result = Math.min(result, temp);
                }
            }
        }

        System.out.println(result);
    }

    private static int bfs(int r, int c, int currIsland) {
        Queue<Pos> que = new LinkedList<>();
        que.offer(new Pos(r,c));
        int [][]map = new int [N][N];
        while(!que.isEmpty()){
            Pos curr = que.poll();
            for (int[] d : dr) {
                if(map[curr.r][curr.c]>result)
                    return -1;
                int nr = curr.r + d[0];
                int nc = curr.c + d[1];
                if (nr < 0 || nr >= N || nc < 0 || nc >= N) {
                    continue;
                }
                if(map[nr][nc]!=0)
                    continue;
                if (arr[nr][nc] == 0 ) {
                    map[nr][nc] = map[curr.r][curr.c] + 1;
                    que.offer(new Pos(nr, nc));
                    continue;
                }
                if (arr[nr][nc] != currIsland) {
                    return map[curr.r][curr.c];
                }
                if(map[nr][nc]>result)
                    return -1;
            }
        }
        return -1;
    }

    private static void findIsland(int r, int c) {
        if (arr[r][c] == 1) {
            arr[r][c] = -i_cnt;
            for (int[] d : dr) {
                if (r + d[0] < 0 || r + d[0] >= N || c + d[1] < 0 || c + d[1] >= N) {
                    continue;
                }
                if (arr[r + d[0]][c + d[1]] == 1) {
                    findIsland(r + d[0], c + d[1]);
                }
            }
        }
    }


}