import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 공항 / 골드2 / 걸린 / 4월 19일
 */
public class Main {
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int answer = 0;

        int gateNum = Integer.parseInt(br.readLine());
        int planeNum = Integer.parseInt(br.readLine());
        parent = new int[gateNum + 1];
        for (int i = 1; i <= gateNum; i++) {
            parent[i] = i;
        }
        for (int i = 0; i < planeNum; i++) {
            int plane = Integer.parseInt(br.readLine());
            int planeGate = find(plane);//비행기가 들어갈 게이트
            if (planeGate > 0) {  //들어갈 수 있음
                answer++;
                parent[planeGate] = parent[planeGate-1];
                find(plane);
            } else {
                System.out.println(answer);
                return;
            }
        }
        System.out.println(answer);
    }

    private static int find(int plane) {
        if (parent[plane] != plane) {
            return parent[plane] = find(parent[plane]);
        }
        return parent[plane];
    }
}