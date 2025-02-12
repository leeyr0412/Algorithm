import java.util.*;

class Solution {
    static Set<String> answerSet = new HashSet<>(); // 중복 조합 방지를 위한 Set
    static boolean[] checks;    // 조합에 들어간 단어 체크

    public int solution(String[] user_id, String[] banned_id) {

        for (int i = 0; i < banned_id.length; i++) {
            // 정규식 활용을 위한 문자 변환
            banned_id[i] = banned_id[i].replace('*', '.');
        }
        checks = new boolean[user_id.length];
        combination(0, new String[banned_id.length], banned_id, user_id);

        return answerSet.size();
    }

    /**
     * 단어 조합을 만드는 함수
     *
     * @param depth
     * @param combi
     * @param bannedIds
     * @param userIds
     */
    private void combination(int depth, String[] combi, String[] bannedIds, String[] userIds) {
        if (depth == bannedIds.length) {
            // 분량 사용자 수만큼 아이디를 찾으면 해당 답을 Set에 넣기 위해 정렬한다.
            // 제재 아이디 목록들을 구했을 때 아이디들이 나열된 순서와 관계없이 아이디 목록의 내용이 동일하다면 같은 것으로 처리 < 문제 조건
            String[] answer = Arrays.copyOf(combi, combi.length);
            Arrays.sort(answer);
            answerSet.add(Arrays.toString(answer));
            return;
        }
        for (int i = 0; i < userIds.length; i++) {
            if (userIds[i].length() != bannedIds[depth].length()
                    || checks[i]) { // 문자열 길이와 이미 사용한 아이디인지 체크
                continue;
            }
            if (userIds[i].matches(bannedIds[depth])) {
                checks[i] = true;
                combi[depth] = userIds[i];
                combination(depth + 1, combi, bannedIds, userIds);
                checks[i] = false;
            }
        }
    }
}