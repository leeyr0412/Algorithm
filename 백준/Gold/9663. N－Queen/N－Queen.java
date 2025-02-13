import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int[] board; // board[row] = col
    static int N;
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        board = new int[N];
        setQueen(0);
        System.out.println(answer);

    }

    private static void setQueen(int line) {       //놓으려고 하는 퀸의 행 번호
        if (line == N) {
            answer++;
            return;
        }

        for (int col = 0; col < N; col++) {
            board[line] = col;
            if (isAvailable(line)) {
                setQueen(line + 1);
            }
        }
    }

    /**
     *  board[line] = col;을 대입한 상태에서 유효한 자리에 대입이 된 것인지 확인
     * @param line
     * @return
     */
    private static boolean isAvailable(int line) {
        for (int col = 0; col < line; col++) {
            if (board[col] == board[line]   // 이전 행에서 이미 같은 열에 놓은 퀸이 있는지 확인
                    || Math.abs(board[col] - board[line]) == line - col) {  // 대각선 확인(열의 차이가 행의 차이와 같다면 대각선임)
                return false;
            }
        }
        return true;
    }
}