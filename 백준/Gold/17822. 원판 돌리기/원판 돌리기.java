import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 원판돌리기 / 골드2 / 걸린시간 / 4월 5일
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());   //원판 수
        int M = Integer.parseInt(st.nextToken());   //원판에 적혀있는 숫자 수
        int T = Integer.parseInt(st.nextToken());   //회전 수

        //원판 배열 입력받기
        int[][] disk = new int[N + 2][M + 2];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= M; j++) {
                disk[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        //원판 돌리기
        for (int t = 0; t < T; t++) {
        //원판 북쪽에 있는 값 인덱스
            int[] top = new int[N + 1];
            Arrays.fill(top, 1);
            // xi, di, ki ::: xi 배수인 원판을 d 방향으로 k칸 회전
            // 0: 시계방향 , 1: 반시계방향
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            //원판 돌리기
            int diskNum = x;
            while (diskNum <= N) {
                if (d == 0) {
                    top[diskNum] = (top[diskNum] - k + M) % (M + 1);
                } else {
                    top[diskNum] = (top[diskNum] + k) % (M + 1);
                }
                diskNum += x;
            }
            //새 디스크 만들기
            disk = getNewDisk(disk, top, N, M);

            //인접 지우기
            if (!deleteNum(disk, N, M)) {  //같은 수가 있어서 지웠음
                //같은 수가 없었음
                cal(disk, N, M);
            }

//            System.out.println();
//
//            for (int r = 1; r <= N; r++) {
//                for (int c = 1; c <= M; c++) {
//                    if (disk[r][c] != 0) {
//
//                    }
//                }
//            }


        }


        //합 구하기
        int sum = 0;
//        int cnt = 0;
        for (int r = 1; r <= N; r++) {
            for (int c = 1; c <= M; c++) {
                if (disk[r][c] == 0)
                    continue;
                sum += disk[r][c];
//                cnt++;
            }
        }
        System.out.println(sum);
    }

    private static boolean deleteNum(int[][] disk, int n, int m) {
        boolean delete = false;
        //인접한게 같은지 확인
        Deque<int[]> del = new LinkedList<>();
//        boolean [][] del = new boolean[n + 2][m + 2];
        for (int r = 1; r <= n; r++) {
            for (int c = 1; c <= m; c++) {
                if(disk[r][c]==0)
                    continue;

                if (disk[r][c] == disk[r][c + 1] || disk[r][c] == disk[r][c - 1] || disk[r][c] == disk[r - 1][c] || disk[r][c] == disk[r + 1][c]) {
                    del.offer(new int[]{r, c});
                    delete = true;
                }
            }
        }
        while (!del.isEmpty()) {
            int[] d = del.poll();
            disk[d[0]][d[1]] = 0;
        }
        return delete;
    }

    private static void cal(int[][] disk, int n, int m) {
        double avg = 0;
        int sum = 0;
        int cnt = 0;
        for (int r = 1; r <= n; r++) {
            for (int c = 1; c <= m; c++) {
                if (disk[r][c] == 0)
                    continue;
                sum += disk[r][c];
                cnt++;
            }
        }
        avg = (double) sum / cnt;
        for (int r = 1; r <= n; r++) {
            for (int c = 1; c <= m; c++) {
                if (disk[r][c] == 0)
                    continue;
                if (disk[r][c] > avg) {
                    disk[r][c]--;
                } else if (disk[r][c] < avg) {
                    disk[r][c]++;
                }
            }
        }
    }

    private static int[][] getNewDisk(int[][] disk, int[] top, int n, int m) {
        int[][] newDisk = new int[n + 2][m + 2];
        for (int r = 1; r <= n; r++) {
            int c = 0;
            while (c < m) {
                int curr = top[r] + c;
                if (curr > m) {
                    curr -= m;
                }
                newDisk[r][++c] = disk[r][curr];
            }
            newDisk[r][0] = newDisk[r][m];
            newDisk[r][m + 1] = newDisk[r][1];
        }
        return newDisk;
    }
}