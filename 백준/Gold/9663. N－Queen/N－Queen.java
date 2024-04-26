import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * N-Queen / 난이도 / 걸린시간 / 날짜
 * https://www.acmicpc.net/problem/9663
 */
public class Main {
    static int[] col;
    static int N;
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        boolean[] row = new boolean[N];
        col = new int[N + 1];
        setQueen(1);
        System.out.println(answer);

    }

    private static void setQueen(int rowNo) {       //놓으려고 하는 퀸의 행 번호
        if (!isAvailalbe(rowNo - 1)) return;

        if (rowNo > N) {
            answer += 1;
            return;
        }

        for (int c = 1; c <= N; c++) {
            col[rowNo] = c;
            setQueen(rowNo + 1);
        }
    }

    private static boolean isAvailalbe(int rowNo) {
        for (int k = 1; k < rowNo; k++) {
            if (col[k] == col[rowNo] || Math.abs(col[k] - col[rowNo]) == rowNo - k) return false;
        }
        return true;
    }
}