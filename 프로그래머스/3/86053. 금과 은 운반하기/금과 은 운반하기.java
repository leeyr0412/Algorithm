class Solution {
    public long solution(int a, int b, int[] g, int[] s, int[] w, int[] t) {
        long maxTime = ((long) 10e9 * 2) * ((long) 10e5 * 2) + (long) 10e5;
        long minTime = 1;
        long mid;
        long answer = maxTime;

        while (minTime <= maxTime) {
            mid = (maxTime + minTime) / 2;
            if (check(a, b, g, s, w, t, mid)) {
                maxTime = mid - 1;
                answer = Math.min(answer, mid);
            } else {
                minTime = mid + 1;
            }
        }
        return answer;
    }

    /**
     * time 안에 금과 은을 다 옮길수있는지 확인하는 함수
     *
     * @param time
     * @return
     */
    public boolean check(int a, int b, int[] g, int[] s, int[] w, int[] t, long time) {
        long sumGold = 0;   // 모든 트럭이 옮길 수 있는 금의 합
        long sumSilver = 0; // 모든 트럭이 옮길 수 있는 은의 합
        long totalWeight = 0; // 모든 트럭이 옮길 수 있는 무게의 합
        for (int truck = 0; truck < g.length; truck++) {
            if (t[truck] > time) {  // 트럭 편도 이동시간보다 목표한 시간이 짧으면 계산하지 않음
                continue;
            }
            long round = (time - t[truck]) / (t[truck] * 2) + 1; // time 동안 왕복 가능 횟수 + 처음
            long carrySum = round * w[truck];   // 트럭이 옮길 수 있는 최대한의 용량

            // 최대 그 지역에 있는 자원만큼 옮길 수 있음
            sumGold += Math.min(carrySum, g[truck]);
            sumSilver += Math.min(carrySum, s[truck]);
            totalWeight += Math.min(carrySum, s[truck] + g[truck]);
        }

        if (sumGold >= a && sumSilver >= b && totalWeight >= a + b) {
            // 모든 트럭이 목표한 시간에 목표한 자원을 모두 옮길 수 있음
            return true;
        }
        return false;
    }
}