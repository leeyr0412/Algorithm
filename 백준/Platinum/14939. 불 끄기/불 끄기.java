import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] originMap = new int[10];
        //0일 경우 << 해도 0이기 때문에 처음 1로 초기화 해줘야함
        Arrays.fill(originMap, 1);
        for (int r = 0; r < 10; r++) {
            String line = br.readLine();
            for (int c = 0; c < 10; c++) {
                originMap[r] = originMap[r] << 1;
                if (line.charAt(c) == 'O') {
                    originMap[r] |= 1;
                }
            }
        }

        // 1줄 2^10
        int T = 1 << 10;
        int answer = Integer.MAX_VALUE;
        for (int tc = 0; tc < T; tc++) {
            int[] copyMap = Arrays.copyOf(originMap, 10);
            int count = 0;
            for (int c = 0; c < 10; c++) {
                if (((1 << c) & tc) != 0) {
                    turn(copyMap, 0, c);
                    count++;
                }
            }
            //2번째줄부터
            for (int r = 1; r < 10; r++) {
                for (int c = 0; c < 10; c++) {
                    if (copyMap[r - 1] == T) {
                        //윗줄이 전부 스위치가 꺼진 상태
                        break;
                    }
                    if ((copyMap[r - 1] & (1 << c)) > 0) {
                        turn(copyMap, r, c);
                        count++;
                    }
                }
            }
            if (copyMap[9] == T) {
                answer = Math.min(answer, count);
            }
        }
        if (answer == Integer.MAX_VALUE) {
            answer = -1;
        }
        System.out.println(answer);
    }

    private static void turn(int[] map, int r, int c) {
        int[][] dr = {{0, 0}, {1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        for (int[] d : dr) {
            int nextR = r + d[0];
            int nextC = c + d[1];
            if (nextR < 0 || nextR >= 10 || nextC < 0 || nextC >= 10) {
                continue;
            }
            //XOR연산
            map[nextR] = map[nextR] ^ (1 << nextC);
        }
    }
}