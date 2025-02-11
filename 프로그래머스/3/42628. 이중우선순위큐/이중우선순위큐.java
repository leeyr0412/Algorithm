import java.util.*;

class Solution {
    public int[] solution(String[] operations) {
        // 자동으로 정렬이 되는 TreeMap을 사용
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        for (String operation : operations) {
            StringTokenizer st = new StringTokenizer(operation);
            String token = st.nextToken();
            int number = Integer.parseInt(st.nextToken());
            
            if (token.equals("I")) {
                treeMap.put(number, number);
            } else {
                if (treeMap.size() == 0) {  // 트리에 들어있는게 없다면 넘어간다.
                    continue;
                }
                if (number == 1) {
                    treeMap.remove(treeMap.lastKey());
                } else {
                    treeMap.remove(treeMap.firstKey());
                }
            }
        }
        if (treeMap.size() == 0) {
            return new int[]{0, 0};
        }
        return new int[]{treeMap.lastKey(), treeMap.firstKey()};
    }
}