import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Pos {
    private int r;
    private int c;

    public Pos(int r, int c) {
        this.r = r;
        this.c = c;
    }

    public int getR() {
        return r;
    }

    public int getC() {
        return c;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            // 입력
            st = new StringTokenizer(br.readLine());
            int h = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            char[][] map = new char[h + 1][w + 1];

            Queue<Pos> queue = new ArrayDeque<>();
            boolean[][] visited = new boolean[h + 1][w + 1];
            for (int r = 1; r <= h; r++) {
                String line = br.readLine();
                for (int c = 1; c <= w; c++) {
                    map[r][c] = line.charAt(c - 1);
                    if (map[r][c] != '*') {
                        if (r == 1 || r == h || c == 1 || c == w) {
                            queue.add(new Pos(r, c));
                            visited[r][c] = true;
                        }
                    }
                }
            }

            int key = 0;
            String keyList = br.readLine();
            if (!keyList.equals("0")) {
                int keyNum = keyList.length();
                for (int i = 0; i < keyNum; i++) {
                    key = key | (1 << (keyList.charAt(i) - 'a'));
                }
            }

            int answer = 0;

            // bfs
            int[][] dr = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
//            Map<Character, List<Pos>> doorMap = new HashMap<>();
            List<Pos>[] doorArr = new List[26];
            for (int i = 0; i < 26; i++) {
                doorArr[i] = new ArrayList<>();
            }
            while (!queue.isEmpty()) {
                Pos now = queue.poll();
                int nowR = now.getR();
                int nowC = now.getC();
                if (map[nowR][nowC] == '.') {
//                    visited[nowR][nowC] = true;
                } else if (map[nowR][nowC] == '$') {
                    answer++;
//                    visited[nowR][nowC] = true;
                    map[nowR][nowC] = '.';
                } else if (map[nowR][nowC] >= 'A' && map[nowR][nowC]<='Z') {    // 문
                    int doorNum = map[nowR][nowC] - 'A';
                    int door = 1 << doorNum;
                    if ((door & key) > 0) {   //열쇠 있음
//                        visited[nowR][nowC] = true;
                        map[nowR][nowC] = '.';
                    } else {  //열쇠 없음
                        doorArr[doorNum].add(new Pos(nowR, nowC));
//                        List<Pos> doorList = new ArrayList<>();
//                        if (doorMap.containsKey(map[nowR][nowC])) {
//                            doorList = doorMap.get(map[nowR][nowC]);
//                        }
//                        doorList.add(new Pos(nowR, nowC));
//                        doorMap.put(map[nowR][nowC], doorList);
                        continue;
                    }
                } else {    //열쇠
//                    visited[nowR][nowC] = true;
//                    char matchDoor = Character.toUpperCase(map[nowR][nowC]);
                    int door = map[nowR][nowC] - 'a';
//                    List<Pos> doorList = doorArr[door];
                    queue.addAll(doorArr[door]);
                    doorArr[door] = new ArrayList<>();
//                    if (doorMap.containsKey(matchDoor)) {
//                        List<Pos> doorList = doorMap.get(matchDoor);
//                        queue.addAll(doorList);
//                        doorMap.remove(matchDoor);
//                    }
                    key = key | (1 << (map[nowR][nowC] - 'a'));
                }
                for (int[] d : dr) {
                    int nextR = nowR + d[0];
                    int nextC = nowC + d[1];
                    if (nextR < 1 || nextR > h || nextC < 1 || nextC > w) {
                        continue;
                    }
                    if (map[nextR][nextC] == '*' || visited[nextR][nextC]) {
                        continue;
                    }
                    queue.add(new Pos(nextR, nextC));
                    visited[nextR][nextC] = true;
                }
            }
            sb.append(answer).append("\n");
        }
        System.out.println(sb);
    }
}