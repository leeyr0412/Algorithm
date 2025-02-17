class Solution {
    public int solution(int[][] triangle) {
        int floor = triangle.length - 1;
        for (int r = floor - 1; r >= 0; r--) {
            for (int c = 0; c <= r; c++) {
                triangle[r][c] += Math.max(triangle[r + 1][c], triangle[r + 1][c + 1]);
            }
        }
        return triangle[0][0] ;
    }
}