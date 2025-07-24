import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    // 방향 전환 편의를 위해 역순으로 배열
    static int[][] dirs = {{1, -1}, {1, 0}, {1, 1}, {0, 1}, {-1, 1}, {-1, 0}, {-1, -1}, {0, -1}};
    static List<Integer>[][] fishMap = new List[4][4];
    static int[][] smellMap = new int[4][4];
    static int sharkR;
    static int sharkC;
    static int[] path;
    static int eatCount;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int M = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());

        for (int r = 0; r < 4; r++) {
            for (int c = 0; c < 4; c++) {
                fishMap[r][c] = new ArrayList<>();
            }
        }

        // 물고기 입력
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int dir = Integer.parseInt(st.nextToken());

            fishMap[r][c].add(8 - dir);
        }

        // 상어 입력
        st = new StringTokenizer(br.readLine());
        sharkR = Integer.parseInt(st.nextToken()) - 1;
        sharkC = Integer.parseInt(st.nextToken()) - 1;

        // 상어 S번 연습
        for (int s = 0; s < S; s++) {
            // 1. 상어가 모든 물고기에게 복제 마법 시전
            List<Integer>[][] copyFishMap = copyFishMap();

            // 2. 모든 물고기 한 칸 이동(상어 있는 칸, 물고기 냄새 있는 칸은 이동 불가). 이동 가능할때까지 이동방향 회전(반시계). 이동 불가하면 이동하지 않음
            fishMove();

            // 3. 상어가 3칸 이동(상하좌우). 물고기 있는 칸이면 물고기 사라지면서 냄새 남김(2턴). 제외되는 물고기가 많은 방향으로 이동(여러개일땐 사전순)
            sharkMove();

            // 4. 냄새 사라짐
            decreaseSmell();

            // 5. 복제된 물고기 생성
            insertCopyFish(copyFishMap);
        }

        System.out.println(getFishCount());
    }

    private static List<Integer>[][] createNewFishMap() {
        List<Integer>[][] newFishMap = new List[4][4];
        for (int r = 0; r < 4; r++) {
            for (int c = 0; c < 4; c++) {
                newFishMap[r][c] = new ArrayList<>();
            }
        }
        return newFishMap;
    }

    private static int getFishCount() {
        int count = 0;
        for (int r = 0; r < 4; r++) {
            for (int c = 0; c < 4; c++) {
                count += fishMap[r][c].size();
            }
        }
        return count;
    }

    /**
     * 5. 복제된 물고기 생성
     *
     * @param copyFishMap
     */
    private static void insertCopyFish(List<Integer>[][] copyFishMap) {
        for (int r = 0; r < 4; r++) {
            for (int c = 0; c < 4; c++) {
                fishMap[r][c].addAll(copyFishMap[r][c]);
            }
        }
    }

    /**
     * 4. 냄새 사라짐
     */
    private static void decreaseSmell() {
        for (int r = 0; r < 4; r++) {
            for (int c = 0; c < 4; c++) {
                if (smellMap[r][c] > 0) {
                    smellMap[r][c]--;
                }
            }
        }
    }

    /**
     * 3. 상어가 3칸 이동
     * 사전순 = 상좌하우(dir:5713)
     */
    private static void sharkMove() {
        // 상어 이동 경로 구하기
        int[] way = new int[3];
        eatCount = -1;
        boolean[][] visited = new boolean[4][4];
        dfs(0, 0, sharkR, sharkC, fishMap, way, visited);

        // 물고기 제거
        for (int dir : path) {
            sharkR = sharkR + dirs[dir][0];
            sharkC = sharkC + dirs[dir][1];
            if (fishMap[sharkR][sharkC].size() > 0) {
                smellMap[sharkR][sharkC] = 3;
                fishMap[sharkR][sharkC] = new ArrayList<>();
            }
        }
    }

    private static void dfs(int depth, int count, int r, int c, List<Integer>[][] fishMap, int[] way, boolean[][] visited) {
        if (depth == 3) {
            if (count > eatCount) {
                path = Arrays.copyOf(way, 3);
                eatCount = count;
            }
            return;
        }

        int dir = 5;
        for (int i = 0; i < 4; i++) {
            int newR = r + dirs[dir][0];
            int newC = c + dirs[dir][1];
            if (inMap(newR, newC)) {
                way[depth] = dir;
                if (!visited[newR][newC]) {
                    visited[newR][newC] = true;
                    dfs(depth + 1, count + fishMap[newR][newC].size(), newR, newC, fishMap, way, visited);
                    visited[newR][newC] = false;
                } else {
                    dfs(depth + 1, count, newR, newC, fishMap, way, visited);
                }

            }
            dir = (dir + 2) % 8;
        }
    }

    /**
     * 2. 모든 물고기 한 칸 이동
     */
    private static void fishMove() {
        List<Integer>[][] newFishMap = createNewFishMap();
        for (int r = 0; r < 4; r++) {
            for (int c = 0; c < 4; c++) {
                // 물고기 이동 후 상태를 새로운 맵에 기록
                if (fishMap[r][c].size() > 0) {
                    for (Integer dir : fishMap[r][c]) {
                        boolean isMove = false;
                        for (int i = 0; i < 8; i++) {
                            int newR = r + dirs[dir][0];
                            int newC = c + dirs[dir][1];
                            if (inMap(newR, newC) &&    // 구역내
                                    smellMap[newR][newC] == 0 &&    // 냄새 없는곳
                                    (sharkR != newR || sharkC != newC)) {   // 상어 없는 곳
                                newFishMap[newR][newC].add(dir);
                                isMove = true;
                                break;
                            }
                            dir = (dir + 1) % 8;
                        }
                        if (!isMove) {
                            newFishMap[r][c].add(dir);
                        }
                    }
                }
            }
        }
        fishMap = newFishMap;
    }

    private static boolean inMap(int newR, int newC) {
        return newR >= 0 && newR < 4 && newC >= 0 && newC < 4;
    }

    /**
     * 1. 상어가 모든 물고기에게 복제 마법 시전
     *
     * @return
     */
    private static List<Integer>[][] copyFishMap() {
        List<Integer>[][] newFishMap = new List[4][4];
        for (int r = 0; r < 4; r++) {
            for (int c = 0; c < 4; c++) {
                newFishMap[r][c] = new ArrayList<>();
                newFishMap[r][c].addAll(fishMap[r][c]);
            }
        }
        return newFishMap;
    }
}