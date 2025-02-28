class Solution {
    public int solution(int[] arr) {
        int answer = 1;
        int size = arr.length;
        for (int i = 0; i < size; i++) {
            answer = (answer * arr[i]) / gcd(answer, arr[i]);
        }
        return answer;
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }
}