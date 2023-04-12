class Solution {
    public int solution(int[] nums) {
        int answer = 0;
        int N = nums.length;
        int max = N / 2;
        boolean[] use = new boolean[200001];
        for (int i : nums) {
            if (!use[i]) {
                answer++;
                use[i] = true;
            }
            if (answer == max) {
                break;
            }
        }
        return answer;
    }
}