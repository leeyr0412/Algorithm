import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
    static class Fish {
        int num, dir, r, c;
        boolean alive;


        public Fish(int num, int dir, int r, int c) {
            this.num = num;
            this.dir = dir;
            this.r = r;
            this.c = c;
            alive = true;
        }

        public Fish(int num, int dir, int r, int c, boolean alive) {
            this.num = num;
            this.dir = dir;
            this.r = r;
            this.c = c;
            this.alive = alive;
        }

        /**
         * 이동 가능한 방향 구하기(물고기)
         *
         * @param map
         * @return
         */
        public int getFishMovableDir(int[][] map) {
            while (true) {
                int nextR = r + dirs[this.dir][0];
                int nextC = c + dirs[this.dir][1];
                if (nextR < 0 || nextC < 0 || nextR >= 4 || nextC >= 4 || map[nextR][nextC] == -1) {
                    this.dir = (this.dir + 1) % 8; // 45도 회전
                    continue;
                }
                return dir;
            }
        }

        public Fish copyShark(int nextR, int nextC) {
            return new Fish(SHARK, this.dir, nextR, nextC);
        }
    }

    static int SHARK = -1;
    static int[][] dirs = {{-1, 0}, {-1, -1}, {0, -1}, {1, -1}, {1, 0}, {1, 1}, {0, 1}, {-1, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        Map<Integer, Fish> fishMap = new HashMap<>();
        int[][] originalMap = new int[4][4];
        for (int r = 0; r < 4; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < 4; c++) {
                int num = Integer.parseInt(st.nextToken());
                int dir = Integer.parseInt(st.nextToken()) - 1;
                originalMap[r][c] = num;
                fishMap.put(num, new Fish(num, dir, r, c));
            }
        }
        // 처음 상어 방향은 0,0 위치의 물고기 방향
        Fish shark = new Fish(SHARK, fishMap.get(originalMap[0][0]).dir, 0, 0);
        int answer = dfs(shark, originalMap, fishMap);
        System.out.println(answer);
    }

    private static int dfs(Fish shark, int[][] map, Map<Integer, Fish> fishMap) {
        int deadFishNum = map[shark.r][shark.c];    // 상어가 먹은 물고기 번호
        int eatCount = deadFishNum;
        map[shark.r][shark.c] = -1; // 물고기 먹음
        Fish deadFish = fishMap.get(deadFishNum);
        deadFish.alive = false;     // 물고기 죽음
        shark.dir = deadFish.dir;   // 상어 방향 수정

        // 물고기 이동
        for (int num = 1; num <= 16; num++) {
            Fish nowFish = fishMap.get(num);
            if (!nowFish.alive) {
                continue;
            }
            int dir = nowFish.getFishMovableDir(map);  // 물고기 이동 가능 방향 구하기
            int nextR = nowFish.r + dirs[dir][0];
            int nextC = nowFish.c + dirs[dir][1];
            if (map[nextR][nextC] != 0) {   // 물고기 이동 위치에 물고기 존재하면 위치 바꿔주기
                Fish swapFish = fishMap.get(map[nextR][nextC]);
                swap(swapFish, nowFish, map);
            } else {
                fishMove(map, nowFish, nextR, nextC);
            }
        }
        int count = 0;
        // 상어 이동
        for (int dist = 1; dist <= 3; dist++) {
            int nextR = shark.r + dirs[shark.dir][0] * dist;
            int nextC = shark.c + dirs[shark.dir][1] * dist;
            if (nextR < 0 || nextC < 0 || nextR >= 4 || nextC >= 4 || map[nextR][nextC] == 0) {
                continue;   // 맵 안에 있으면서 물고기 있는 자리여야함
            }
            // 상어, 지도, 물고기 목록 복사
            Fish newShark = shark.copyShark(nextR, nextC);
            int[][] newMap = copyMap(map);
            newMap[shark.r][shark.c] = 0;
            Map<Integer, Fish> newFishMap = coptMap(fishMap);
            count = Math.max(count, dfs(newShark, newMap, newFishMap)); // 가장 큰 물고기 번호 먹어야함
        }
        return count + eatCount;
    }

    private static Map<Integer, Fish> coptMap(Map<Integer, Fish> fishMap) {
        Map<Integer, Fish> newFishMap = new HashMap<>();
        for (int i = 1; i <= 16; i++) {
            Fish fish = fishMap.get(i);
            newFishMap.put(i, new Fish(fish.num, fish.dir, fish.r, fish.c, fish.alive));
        }
        return newFishMap;
    }


    private static int[][] copyMap(int[][] map) {
        int[][] newMap = new int[4][4];
        for (int r = 0; r < 4; r++) {
            newMap[r] = map[r].clone();
        }
        return newMap;
    }

    private static void fishMove(int[][] map, Fish nowFish, int nextR, int nextC) {
        map[nowFish.r][nowFish.c] = 0;
        nowFish.r = nextR;
        nowFish.c = nextC;
        map[nowFish.r][nowFish.c] = nowFish.num;
    }

    /**
     * 물고기 위치 스왚
     *
     * @param swapFish
     * @param nowFish
     * @param map
     */
    private static void swap(Fish swapFish, Fish nowFish, int[][] map) {
        map[nowFish.r][nowFish.c] = swapFish.num;
        map[swapFish.r][swapFish.c] = nowFish.num;
        int tempR = swapFish.r;
        int tempC = swapFish.c;
        swapFish.r = nowFish.r;
        swapFish.c = nowFish.c;
        nowFish.r = tempR;
        nowFish.c = tempC;
    }
}