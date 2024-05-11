import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int maxPlug;
    static int K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        maxPlug = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        int[] order = new int[K];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            order[i] = Integer.parseInt(st.nextToken());
        }

        int[] plugs = new int[maxPlug];
        int answer = 0;

        for (int use = 0; use < K; use++) {
            int plugNum = 0;
            for (int inPlugMachine : plugs) {
                //이미 플러그에 있는지 확인
                if (inPlugMachine == order[use]) {
                    plugNum = -1;
                    break;
                }
            }
            if (plugNum == -1) {
                continue;
            }
            plugNum = getEmptyPlugNum(plugs);
            if (plugNum >= 0) {
                //플러그 꽂을 위치 있음
                plugs[plugNum] = order[use];
                //continue;
            } else {
                answer++;
                plugNum = getNotUse(use, order, plugs);
                if (plugNum >= 0) {
                    plugs[plugNum] = order[use];
                } else {
                    plugs[getLastUse(use, order, plugs)] = order[use];
                }
            }

        }
        System.out.println(answer);
    }

    //가장 마지막에 사용하는 플러그 반환
    private static int getLastUse(int use, int[] order, int[] plugs) {
        if (use == order.length - 1) {
            return 0;
        }
        HashMap<Integer, Integer> m = new HashMap<>();
        for (int plug = 0; plug < plugs.length; plug++) {
            m.put(plugs[plug], plug);
        }

        for (int i = use + 1; i < order.length; i++) {
            m.remove(order[i]);
            if (m.size() == 1) {
                break;
            }
        }
        for (Integer machine : m.keySet()) {
            return m.get(machine);
        }
        return 0;
    }

    /**
     * 뒤에 사용하지 않는 플러그 찾아 반환
     */
    private static int getNotUse(int use, int[] order, int[] plugs) {
        if (use == order.length - 1) {
            return 0;
        }
        int[] machine = new int[K + 1];
        Arrays.fill(machine, -1);
        for (int i = 0; i < plugs.length; i++) {
            machine[plugs[i]] = i;
        }
        for (int i = use + 1; i < order.length; i++) {
            if (machine[order[i]] >= 0) {
                machine[order[i]] = -1;
            }
        }
        for (int i = 0; i < K + 1; i++) {
            if (machine[i] >= 0) {
                return machine[i];
            }
        }
        return -1;
    }

    /**
     * 플러그 꽂을 위치 반환
     *
     * @return 자리 없으면 -1
     */
    private static int getEmptyPlugNum(int[] plugs) {
        for (int i = 0; i < plugs.length; i++) {
            if (plugs[i] == 0) {
                return i;
            }
        }
        return -1;
    }
}