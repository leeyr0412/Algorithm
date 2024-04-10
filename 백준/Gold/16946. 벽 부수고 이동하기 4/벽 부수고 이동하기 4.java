import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int[][] dr = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    static int row;
    static int col;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());
        char[][] map = new char[row][col];
        int[][] answer = new int[row][col];
        int[][] areaMap = new int[row][col];
        List<Integer> list = new ArrayList<>();

        for (int r = 0; r < row; r++) {
            map[r] = br.readLine().toCharArray();
        }

        int num = 1;
        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                if (map[r][c] == '0' && areaMap[r][c] == 0) {
                    bfs(map, r, c, areaMap, list, num);
                    num++;
                }
            }
        }

        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                if (map[r][c] != '0') {
                    Set<Integer> visited = new HashSet<>();
                    int count = 1;
                    for (int[] d : dr) {
                        int nextR = r + d[0];
                        int nextC = c + d[1];
                        if (nextR < 0 || nextC < 0 || nextR >= row || nextC >= col || areaMap[nextR][nextC] == 0) {
                            continue;
                        }
                        int areaNum = areaMap[nextR][nextC] - 1;
                        if (!visited.contains(areaNum)) {
                            count += list.get(areaNum);
                            visited.add(areaNum);
                        }
                    }
                    answer[r][c] = count;
                }
                sb.append(answer[r][c] % 10);
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    private static void bfs(char[][] map, int r, int c, int[][] mapCopy, List<Integer> list, int num) {
        int count = 0;
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{r, c});
        mapCopy[r][c] = num;
        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            count++;
            for (int[] d : dr) {
                int nextR = now[0] + d[0];
                int nextC = now[1] + d[1];
                if (nextR < 0 || nextC < 0 || nextR >= row || nextC >= col) {
                    continue;
                }
                if (map[nextR][nextC] == '0' && mapCopy[nextR][nextC] == 0) {
                    mapCopy[nextR][nextC] = num;
                    queue.add(new int[]{nextR, nextC});
                }
            }
        }
        list.add(count);
    }
}