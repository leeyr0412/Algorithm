import java.util.*;

class Block {
    int size;
    boolean[][] shape;

    public Block(int size, boolean[][] shape) {
        this.size = size;
        this.shape = shape;
    }

    // 모양 회전
    public void rotate() {
        int rows = shape.length;
        int cols = shape[0].length;
        boolean[][] newShape = new boolean[cols][rows];
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                newShape[c][rows - 1 - r] = shape[r][c];
            }
        }
        shape = newShape;
    }
}

class EmptyArea {
    Block block;
    boolean fill;

    public EmptyArea(Block block) {
        this.block = block;
        this.fill = false;
    }
}

class Solution {
    static int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public int solution(int[][] game_board, int[][] table) {
        int answer = 0;
        List<EmptyArea>[] areaList = new List[7];   // 빈 구역 리스트
        for (int i = 1; i < 7; i++) {
            areaList[i] = new ArrayList<>();
        }

        // 빈 공간 찾아서 정리
        for (int r = 0; r < game_board.length; r++) {
            for (int c = 0; c < game_board.length; c++) {
                if (game_board[r][c] == 0) {
                    Block block = createBlock(game_board, r, c, 0);
                    areaList[block.size].add(new EmptyArea(block));
                }
            }
        }

        for (int r = 0; r < table.length; r++) {
            for (int c = 0; c < table.length; c++) {
                if (table[r][c] == 1) {
                    // 블럭을 찾음(모양과 크기 나옴)
                    Block block = createBlock(table, r, c, 1);
                    boolean fill = false;
                    for (int i = 0; i < 4; i++) {
                        // 해당 사이즈의 남아있는 빈칸들에 넣을 수 있는지 확인
                        for (EmptyArea emptyArea : areaList[block.size]) {
                            if (emptyArea.fill) {
                                // 이미 채워진 빈칸
                                continue;
                            }
                            // 깊은 비교(2차원 배열 비교)
                            if (Arrays.deepEquals(emptyArea.block.shape, block.shape)) {
                                emptyArea.fill = true;
                                answer += block.size;
                                fill = true;
                                break;
                            }
                        }
                        if (fill) {
                            break;
                        }
                        block.rotate(); // 칸에 못 넣었을 경우 회전

                    }
                }
            }
        }
        return answer;
    }

    /**
     * 블럭/빈 공간 찾아서 Block 클래스로 반환
     *
     * @param find // 찾아야하는 숫자(0: 빈 공간, 1: 블럭)
     * @return
     */
    private Block createBlock(int[][] board, int sr, int sc, int find) {
        int turn = find == 1 ? 0 : 1;   // 방문지점 체크를 위한 변수
        // 모양 배열의 크기를 위한 변수
        int minR = 51;
        int minC = 51;
        int maxR = -1;
        int maxC = -1;
        // 모양을 이루는 좌표들 배열
        int[][] posArr = new int[7][2];
        int size = 0;
        Queue<int[]> queue = new ArrayDeque<>();
        board[sr][sc] = turn;
        queue.add(new int[]{sr, sc});
        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            minR = Math.min(minR, now[0]);
            minC = Math.min(minC, now[1]);
            maxR = Math.max(maxR, now[0]);
            maxC = Math.max(maxC, now[1]);
            posArr[size++] = new int[]{now[0], now[1]};
            for (int[] dir : dirs) {
                int r = now[0] + dir[0];
                int c = now[1] + dir[1];
                if (!isInvalid(r, c, board.length)) {
                    if (board[r][c] == find) {
                        queue.add(new int[]{r, c});
                        board[r][c] = turn;
                    }
                }
            }
        }

        // 모양. 블럭을 구성하는 좌표들 중에서 가장 왼쪽 위에 있는 칸을 (0, 0)으로 만듦
        boolean[][] shape = new boolean[maxR - minR + 1][maxC - minC + 1];
        for (int i = 0; i < size; i++) {
            posArr[i][0] -= minR;
            posArr[i][1] -= minC;
            shape[posArr[i][0]][posArr[i][1]] = true;
        }
        return new Block(size, shape);
    }

    /**
     * 범위 확인(구역 밖이면 true)
     */
    private boolean isInvalid(int r, int c, int length) {
        return r < 0 || c < 0 || r >= length || c >= length;
    }
}