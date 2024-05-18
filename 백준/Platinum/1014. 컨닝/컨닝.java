import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class SeatCase {
    private int seats;
    private int count;

    public SeatCase(int seats, int count) {
        this.seats = seats;
        this.count = count;
    }

    public int getSeats() {
        return seats;
    }

    public int getCount() {
        return count;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            st = new StringTokenizer(br.readLine());
            int R = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            int max = 1 << C;
            int[][] dp = new int[R + 1][max];
            char[][] map = new char[R + 1][C];
            for (int r = 1; r <= R; r++) {
                String line = br.readLine();
                for (int c = 0; c < C; c++) {
                    map[r][c] = line.charAt(c);
                }
            }

            //한 행에 가능한 자리 배치 구하기
            List<SeatCase> seatCaseList = new ArrayList<>();
            for (int seatCase = 0; seatCase < (1 << C); seatCase++) {
                if (sideCheck(seatCase)) {//인접한 자리에 앉는지 확인
                    int count = 0;
                    for (int i = 0; i < C; i++) {//앉은사람 수 카운트
                        if ((seatCase & (1 << i)) != 0) {
                            count++;
                        }
                    }
                    seatCaseList.add(new SeatCase(seatCase, count));
                }
            }

            int answer = 0;

            //앞 줄부터 앉히기
            for (int r = 1; r <= R; r++) {
                for (SeatCase seatCase : seatCaseList) {
                    //앉아야 하는 자리에 의자가 부서지지 않았는지 확인
                    if (chairCheck(seatCase.getSeats(), map[r])) {
                        //앞 자리랑 대각선인지 확인
                        for (SeatCase frontCase : seatCaseList) {
                            if (sideCheck(frontCase.getSeats() | seatCase.getSeats())) {
                                dp[r][seatCase.getSeats()] = Math.max(dp[r][seatCase.getSeats()], dp[r - 1][frontCase.getSeats()] + seatCase.getCount());
                                answer = Math.max(answer, dp[r][seatCase.getSeats()]);
                            }
                        }
                    }
                }
            }
            sb.append(answer).append("\n");
        }
        System.out.println(sb);
    }


    /**
     * 의자 앉을수있는지 확인
     */
    private static boolean chairCheck(int seats, char[] seat) {
        for (int i = seat.length - 1, chairNum = 0; i >= 0; i--, chairNum++) {
            if (((1 << i) & seats) != 0) {
                if (seat[chairNum] == 'x') {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 연속된 자리에 앉는지 확인
     */
    private static boolean sideCheck(int seatCase) {
        int test = 0;
        for (int i = 0; test <= seatCase; i++) {
            test = 3 << i;
            if ((seatCase & test) == test) {
                return false;
            }
        }
        return true;
    }
}