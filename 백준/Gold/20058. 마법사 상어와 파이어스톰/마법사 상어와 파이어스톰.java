import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    static int[][] map;
    static int mapSize;
    static int bigIceSize = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int remainIce = 0;

        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        mapSize = (int) Math.pow(2, N);
        map = new int[mapSize][mapSize];
        for (int r = 0; r < mapSize; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < mapSize; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
                remainIce += map[r][c]; // 초기 얼음 양 계산
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int q = 0; q < Q; q++) {
            // 격자 부분 회전
            int L = Integer.parseInt(st.nextToken());
            int splitSize = (int) Math.pow(2, L);
            for (int r = 0; r < mapSize; r += splitSize) {
                for (int c = 0; c < mapSize; c += splitSize) {
                    rotateArea(r, c, splitSize);
                }
            }

            // ice--
            List<int[]> declineList = new ArrayList<>();
            for (int r = 0; r < mapSize; r++) {
                for (int c = 0; c < mapSize; c++) {
                    if (map[r][c] == 0) {
                        continue;
                    }
                    if (isDecline(r, c)) {  // 주위 얼음이 있는 칸이 3칸 미만인지 확인
                        declineList.add(new int[]{r, c});   // 미리 감소시키면 안됨
                    }
                }
            }
            for (int[] pos : declineList) { // 얼음의 양은 동시 감소
                map[pos[0]][pos[1]]--;
                remainIce--;    // 남은 얼음 양 계산
            }
        }

        // 얼음 덩어리 크기 계산
        for (int r = 0; r < mapSize; r++) {
            for (int c = 0; c < mapSize; c++) {
                if (map[r][c] > 0) {
                    bigIceSize = Math.max(bigIceSize, bfs(r, c));
                }
            }
        }

        System.out.println(remainIce);
        System.out.println(bigIceSize);
    }

    /**
     * 구역의 크기 계산
     *
     * @return 얼음 덩어리 크기
     */
    private static int bfs(int r, int c) {
        int areaCount = 0;
        map[r][c] = 0;
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{r, c});
        while (!queue.isEmpty()) {
            areaCount++;
            int[] now = queue.poll();
            for (int[] dir : dirs) {
                int nextR = now[0] + dir[0];
                int nextC = now[1] + dir[1];
                if (isInvalid(nextR, nextC)) {
                    continue;
                }
                if (map[nextR][nextC] > 0) {
                    queue.add(new int[]{nextR, nextC});
                    map[nextR][nextC] = 0;
                }
            }
        }
        return areaCount;
    }

    /**
     * 얼음이 감소하는지 확인
     *
     * @return 감소해야되면 true
     */
    private static boolean isDecline(int r, int c) {
        int noIceCount = 0; // 얼음이 없는 인접구역 카운트
        for (int[] dir : dirs) {
            if (noIceCount > 1) {  // 인접한 곳에 얼음이 없는 칸이 2칸 이상이면 감소함
                return true;
            }
            int newR = r + dir[0];
            int newC = c + dir[1];
            if (isInvalid(newR, newC)) { // 격자 밖도 없음이 없음
                noIceCount++;
                continue;
            }
            if (map[newR][newC] == 0) {
                noIceCount++;
            }
        }
        return noIceCount > 1;
    }

    private static boolean isInvalid(int r, int c) {
        return r < 0 || c < 0 || r >= mapSize || c >= mapSize;
    }

    /**
     * 격자 회전
     *
     * @param sr        격자 시작 좌표
     * @param sc
     * @param splitSize 격자 크기
     */
    private static void rotateArea(int sr, int sc, int splitSize) {
        int[][] temp = new int[splitSize][splitSize];
        // 회전 후 임시 배열에 대입
        for (int r = 0; r < splitSize; r++) {
            for (int c = 0; c < splitSize; c++) {
                temp[c][splitSize - 1 - r] = map[sr + r][sc + c];
            }
        }

        for (int r = 0; r < splitSize; r++) {
            for (int c = 0; c < splitSize; c++) {
                map[sr + r][sc + c] = temp[r][c];
            }
        }
    }
}