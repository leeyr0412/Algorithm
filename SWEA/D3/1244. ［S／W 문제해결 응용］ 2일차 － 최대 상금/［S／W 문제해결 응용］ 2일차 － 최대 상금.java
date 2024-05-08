import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;// = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            int answer = 0;
            char[] board = st.nextToken().toCharArray();
            int changes = Integer.parseInt(st.nextToken());
//            changes = Math.min(changes, board.length);
            answer = solution(0, 0, changes, board, answer);

            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }
        System.out.println(sb);
    }

    private static int solution(int index, int depth, int changes, char[] board, int answer) {
        if (depth == changes) {
            return toInt(board);
        }
        for (int from = index; from < board.length - 1; from++) {
            for (int to = from + 1; to < board.length; to++) {
//                if (board[from] > board[to]) {
//                    continue;
//                }
                swap(board, from, to);
                answer = Math.max(answer, solution(from, depth + 1, changes, board, answer));

                swap(board, from, to);
            }
        }
        return answer;
    }

    private static int toInt(char[] board) {
        int result = 0;
        for (char c : board) {
            result = result * 10 + (c - '0');
        }
        return result;
    }

    private static void swap(char[] board, int from, int to) {
        char temp = board[from];
        board[from] = board[to];
        board[to] = temp;
    }
}


/*
10
123 1
2737 1
757148 1
78466 2
32888 2
777770 5
436659 2
431159 7
112233 3
456789 10

#1 321
#2 7732
#3 857147
#4 87664
#5 88832
#6 777770
#7 966354
#8 954311
#9 332211
#10 987645


 */