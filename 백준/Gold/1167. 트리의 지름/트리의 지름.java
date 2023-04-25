import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * 트리의 지름 / 골드2 / 걸린시간 / 4월 21일
 */
public class Main {
    static int v;
    static Map<Integer, Integer>[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;// = new StringTokenizer(br.readLine());

        v = Integer.parseInt(br.readLine());
        arr = new Map[v + 1];
        for (int i = 0; i < v; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            if (arr[start] == null) {
                arr[start] = new HashMap<>();
            }
            int end;
            while ((end = Integer.parseInt(st.nextToken())) != -1) {
                int len = Integer.parseInt(st.nextToken());
                arr[start].put(end, len);
            }
        }
        int answer = 0;
        boolean[] visited = new boolean[v + 1];
        visited[1] = true;
        int[] temp = dfs(1, 0, visited, 1);
        visited[1] = false;
        visited[temp[0]] = true;
        temp = dfs(temp[0], 0, visited, temp[0]);
        answer = temp[1];
        System.out.println(answer);
    }

    private static int[] dfs(int start, int len, boolean[] visited, int curr) {
        int max = len;
        for (Integer end : arr[start].keySet()) {
            if (!visited[end]) {
                visited[end] = true;
                int[] temp = dfs(end, len + arr[start].get(end), visited, end);
                if (temp[1] > max) {
                    max = temp[1];
                    curr = temp[0];
                }
                visited[end] = false;
            }
        }
        return new int[]{curr, max};
    }
}