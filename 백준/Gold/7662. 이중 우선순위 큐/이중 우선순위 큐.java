import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            TreeMap<Integer, Integer> treeMap = new TreeMap<>();
            int K = Integer.parseInt(br.readLine()); // 연산 개수
            for (int k = 0; k < K; k++) {
                st = new StringTokenizer(br.readLine());
                if (st.nextToken().equals("I")) {
                    int insertNum = Integer.parseInt(st.nextToken());
                    int count = treeMap.getOrDefault(insertNum, 0) + 1;
                    treeMap.put(insertNum, count);
                } else {
                    int deleteNum;
                    if (treeMap.size() == 0) {
                        continue;
                    } else if (st.nextToken().equals("1")) {
                        deleteNum = treeMap.lastKey();
                    } else {
                        deleteNum = treeMap.firstKey();
                    }

                    if (treeMap.get(deleteNum) == 1) {
                        treeMap.remove(deleteNum);
                    } else {
                        treeMap.put(deleteNum, treeMap.get(deleteNum) - 1);
                    }
                }
            }
            if (treeMap.size() == 0) {
                System.out.println("EMPTY");
            } else {
                System.out.println(treeMap.lastKey() + " " + treeMap.firstKey());
            }
        }
    }
}