import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/**
 * 탈출 / 골드4 / 걸린시간 / 4월 5일
 */
class Pos {
    private int r;
    private int c;
    private int count;

    public Pos(int r, int c, int count) {
        this.r = r;
        this.c = c;
        this.count = count;
    }

    public int getR() {
        return r;
    }

    public int getC() {
        return c;
    }

    public int getCount() {
        return count;
    }
}

public class Main {
    static int R, C;
    static char[][] map;
    static Pos[][] hedgehogMap;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        hedgehogMap = new Pos[R][C];
        int currR = 0, currC = 0;

        for (int r = 0; r < R; r++) {
            String line = br.readLine();
            for (int c = 0; c < C; c++) {
                map[r][c] = line.charAt(c);
                if (map[r][c] == 'S') {
                    currC = c;
                    currR = r;
                    hedgehogMap[r][c] = new Pos(r,c,0);
//                    map[r][c] = '.';
                }
            }
        }

        int answer = 0;
        boolean flag = true;
        //물퍼짐
        while (true) {
            //고슴도치 이동

            boolean[][] hedgehog = new boolean[R][C];
            for (int r = 0; r < R&&flag; r++) {
                for (int c = 0; c < C&&flag; c++) {
                    if (map[r][c] == 'S') {
                        int[][] dr = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
                        for (int[] d : dr) {
                            int newR = r + d[0];
                            int newC = c + d[1];
                            if (newR >= 0 && newC >= 0 && newR < R && newC < C) {
                                if(map[newR][newC] == 'D'){
                                    answer = hedgehogMap[r][c].getCount()+1;
                                    flag = false;
                                    break;
                                }
                                if (map[newR][newC] == '.') {
                                    if (hedgehogMap[newR][newC] == null) {
                                        hedgehogMap[newR][newC] = new Pos(r,c,hedgehogMap[r][c].getCount()+1);
//                                        map[newR][newC] = 'S';
                                        hedgehog[newR][newC] = true;
                                    }
                                }
                            }
                        }
                        map[r][c] = '.';

                    }

                }
            }
            if(!flag)
                break;
            int h = 0;
            for (int r = 0; r < R; r++) {
                for (int c = 0; c < C; c++) {
                    if (hedgehog[r][c]) {
                        map[r][c] = 'S';
                        h++;
                    }
                }
            }

            if(h==0) {
                answer = -1;
                break;
            }


//            printMap();
            boolean[][] water = new boolean[R][C];
            for (int r = 0; r < R; r++) {
                for (int c = 0; c < C; c++) {
                    if (map[r][c] == '*' && !water[r][c]) {
//                        water[r][c] = true;
                        waterBfs(water, r, c);
                    }
                }
            }
            for (int r = 0; r < R; r++) {
                for (int c = 0; c < C; c++) {
                    if (water[r][c]) {
                        map[r][c] = '*';
                    }
                }
            }

//            printMap();
        }

        if (answer < 0) {
            System.out.println("KAKTUS");
        } else System.out.println(answer);
    }

    private static void printMap() {
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                System.out.print(map[r][c]);
            }
            System.out.println();
        }
        System.out.println();
    }

    private static void waterBfs(boolean[][] water, int r, int c) {
        int[][] dr = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        water[r][c] = true;
        Deque<Pos> que = new ArrayDeque<>();
        que.offer(new Pos(r, c, 0));
        while (!que.isEmpty()) {
            Pos now = que.poll();
            for (int[] d : dr) {
                int newR = now.getR() + d[0];
                int newC = now.getC() + d[1];
                if (newR >= 0 && newC >= 0 && newR < R && newC < C) {
                    if (water[newR][newC])
                        continue;
                    if (map[newR][newC] == '*') {
                        que.offer(new Pos(newR, newC, 0));
                        water[newR][newC] = true;
                    }
                    if (map[newR][newC] == '.'||map[newR][newC] == 'S')
                        water[newR][newC] = true;
                }
            }
        }
    }

    private static int hedgehogMove(int currR, int currC, boolean[][] water) {
        int[][] dr = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        boolean[][] visited = new boolean[R][C];
        Deque<Pos> que = new ArrayDeque<>();
        que.offer(new Pos(currR, currC, 0));
        while (!que.isEmpty()) {
            Pos now = que.poll();
            for (int[] d : dr) {
                int newR = now.getR() + d[0];
                int newC = now.getC() + d[1];
                if (newR >= 0 && newC >= 0 && newR < R && newC < C) {
                    if (visited[newR][newC]) continue;
                    if (map[newR][newC] == 'D') {
                        return now.getCount() + 1;
                    }
                    if (map[newR][newC] == '.') {
                        visited[newR][newC] = true;
                        que.offer(new Pos(newR, newC, now.getCount() + 1));
                    }
                }
            }
        }
        return -1;
    }
}