import java.util.*;

class Solution {
    public String solution(String input_string) {
        StringBuilder answer = new StringBuilder();
        Set<Character> answerSet = new HashSet<>();
        int length = input_string.length();
        boolean[] visited = new boolean[26];
        for (int i = 0; i < length; i++) {
            int alpha = input_string.charAt(i) - 'a';
            if (visited[alpha]) {
                if (input_string.charAt(i - 1) - 'a' != alpha) {
                    answerSet.add(input_string.charAt(i));
                }
            } else {
                visited[alpha] = true;
            }
        }
        if (answerSet.size() == 0) {
            return "N";
        }
        List<Character> anserList = new ArrayList<>(answerSet);
        Collections.sort(anserList);
        for (Character character : anserList) {
            answer.append(character);
        }
        return answer.toString();
    }
}
