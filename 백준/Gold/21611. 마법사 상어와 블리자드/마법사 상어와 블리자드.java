import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int answer = 0;

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] map = new int[N][N];
        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }
        int[][] indexMap = new int[N][N];

        List<Integer> ballList = new ArrayList<>();
        int shark = N / 2;
        init(shark, map, ballList, indexMap);
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        // 명령어수행
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken()) - 1;
            int s = Integer.parseInt(st.nextToken());
            Deque<Integer> removeIndex = new ArrayDeque<>();
            int r = shark;
            int c = shark;
            for (int size = 0; size < s; size++) {
                r = r + dirs[d][0];
                c = c + dirs[d][1];
                if (ballList.size() > indexMap[r][c])
                    removeIndex.add(indexMap[r][c]);
                else break;
            }
            removeBall(ballList, removeIndex);

            // 4개 이상 연속된 구슬 폭발
            int length = N * N;
            while (length != ballList.size()) {
                length = ballList.size();
                int count = 0;
                int ballNumber = 0;
                List<Integer> temp = new ArrayList<>();
                for (int ballIndex = 0; ballIndex < ballList.size(); ballIndex++) {

                    if (ballList.get(ballIndex) == ballNumber) {
                        count++;
                    } else {
                        if (count >= 4) {
                            answer += count * ballNumber;
                            removeIndex.addAll(temp);
                            temp.clear();
                        }
                        ballNumber = ballList.get(ballIndex);
                        temp.clear();
                        count = 1;
                    }
                    temp.add(ballIndex);
                }
                if(temp.size()>=4){
                    answer += count * ballNumber;
                    removeIndex.addAll(temp);
                    temp.clear();
                }
                removeBall(ballList, removeIndex);
            }

            // 구슬 분열
            if (ballList.size() > 0)
                ballList = ballSplit(N, ballList);
        }
        System.out.println(answer);
    }

    private static List<Integer> ballSplit(int N, List<Integer> ballList) {
        List<Integer> newBallList = new ArrayList<>();
        int count = 1;
        int ballNum = ballList.get(0);
        for (int ballIndex = 1; ballIndex < ballList.size(); ballIndex++) {
            int ball = ballList.get(ballIndex);
            if (ball != ballNum) {
                newBallList.add(count);
                newBallList.add(ballNum);
                count = 1;
                ballNum = ball;
            } else {
                count++;
            }
        }
        newBallList.add(count);
        newBallList.add(ballNum);
        ballList.clear();
        ballList.addAll(newBallList);
        if (ballList.size() > N * N - 1) {
            ballList = ballList.subList(0, N * N - 1);
        }
        return ballList;
    }

    private static void removeBall(List<Integer> ballList, Deque<Integer> removeIndex) {
        while (!removeIndex.isEmpty()) {
            int index = removeIndex.pollLast();
            if (index < ballList.size())
                ballList.remove(index);
        }
    }

    private static void init(int shark, int[][] map, List<Integer> ballList, int[][] indexMap) {
        int r = shark;
        int c = shark;
        int size = 1;
        int[][] dirs = {{0, -1}, {1, 0}, {0, 1}, {-1, 0}};
        int dir = 0;
        int index = 0;
        while (r >= 0 && c >= 0) {
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < size; j++) {
                    r = r + dirs[dir][0];
                    c = c + dirs[dir][1];
                    if (r < 0 || c < 0) {
                        break;
                    }
                    if (map[r][c] != 0) {
                        ballList.add(map[r][c]);
                    }
                    indexMap[r][c] = index++;
                }
                if (r < 0 || c < 0) {
                    break;
                }
                dir = (dir + 1) % 4;
            }
            size++;
        }
    }
}