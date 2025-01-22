import java.util.*;

class Solution {
    public String solution(int n, int t, int m, String[] timetable) {
        int answer = 0;
        Arrays.sort(timetable);
        Queue<Integer> que = new ArrayDeque<>();
        for (String time : timetable) {
            que.add(Integer.parseInt(time.substring(0, 2)) * 60 + Integer.parseInt(time.substring(3, 5)));
        }
        int nowShuttleTime = 9 * 60;    // 09:00을 분으로 표현
        int lastCrewTime = 0; // 마지막 크루 탑승시간

        for (int shuttle = 1; shuttle <= n; shuttle++) {
            int count = 0;
            // 탑승할 자리 있고, 대기 줄 있으며 지금 셔틀 탈수있음
            while (count < m && !que.isEmpty() && que.peek() <= nowShuttleTime) {
                count++;
                lastCrewTime = que.poll();  // 셔틀 탄 사람의 시간 기록
            }

            // 마지막 셔틀 버스
            if (shuttle == n) {
                if (count == m) { // 남은 자리 없음
                    answer = lastCrewTime - 1;
                } else {
                    answer = nowShuttleTime;
                }
            }
            nowShuttleTime += t; // 다음 셔틀 시간으로
        }
        return generateAnswer(answer);
    }

    private String generateAnswer(int answer) {
        int hour = answer / 60;
        int minute = answer % 60;
        return String.format("%02d", hour) + ":" + String.format("%02d", minute);
    }
}