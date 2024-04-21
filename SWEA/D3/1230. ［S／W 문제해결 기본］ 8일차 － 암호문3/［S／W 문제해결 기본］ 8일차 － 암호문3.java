import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;// = new StringTokenizer(br.readLine());

        int T = 10;
        for (int tc = 1; tc <= T; tc++) {
            sb.append("#").append(tc).append(" ");
            int N = Integer.parseInt(br.readLine());
            List<Integer> list = new LinkedList<>();
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                list.add(Integer.parseInt(st.nextToken()));
            }
            int M = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                String order = st.nextToken();
                if (order.equals("I")) {
                    insert(st, list);
                } else if (order.equals("D")) {
                    delete(st, list);
                } else {
                    add(st, list);
                }
            }
            for (int i = 0; i < 10; i++) {
                sb.append(list.get(i)).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    private static void add(StringTokenizer st, List<Integer> list) {
        int num = Integer.parseInt(st.nextToken());
        for (int n = 0; n < num; n++) {
            list.add(Integer.parseInt(st.nextToken()));
        }
    }

    private static void delete(StringTokenizer st, List<Integer> list) {
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        for (int n = 0; n < y; n++) {
            list.remove(x);
        }
    }

    private static void insert(StringTokenizer st, List<Integer> list) {
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        for (int n = 0; n < y; n++) {
            list.add(x + n, Integer.parseInt(st.nextToken()));
        }
    }
}