import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * 친구네트워크 / 난이도 / 걸린시간 / 3월 22일
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;// = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {

            int e = Integer.parseInt(br.readLine());
            Map<String, String> map = new HashMap<>();  //친구네트워크 <나,대장친구>
            Map<String, Integer> num = new HashMap<>(); //친구네트워크에 있는 사람 수 <대장친구, 친구수>
            for (int j = 0; j < e; j++) {
                st = new StringTokenizer(br.readLine());
                String a = st.nextToken();
                String b = st.nextToken();
                if(a.equals(b)){
                    sb.append(num.get(map.get(a))).append("\n");
//                    System.out.println(num.get(map.get(a)));
                    continue;
                }
                if (map.containsKey(a)) {               //a가 속한 네트워크가 있음
                    if (map.containsKey(b)) {           //b도 속한 네트워크가 있음
                        if (map.get(a).equals(map.get(b))) { //a,b 조상이 같음 = 이미 한 네트워크
                            sb.append(num.get(map.get(a))).append("\n");
//                            System.out.println(num.get(map.get(a)));
                        } else {                        //a, b 각자 다른 그룹이었음 = 그룹 합치기
                            String cap = map.get(b);    //b 그룹 대장
                            for (String k : map.keySet()) {
                                if (map.get(k).equals(cap)) {           //b가 속한 그룹의 사람이면
                                    map.put(k, map.get(a));             //a그룹에 넣기
                                    num.put(map.get(a),
                                            num.get(map.get(a)) + 1);   //a그룹 사람 ++
                                }
                            }
//                            System.out.println(num.get(map.get(a)));
                            sb.append(num.get(map.get(a))).append("\n");
                        }
                    } else {  //a 그룹에 b 넣기
                        num.put(map.get(a), num.get(map.get(a)) + 1);
                        map.put(b, map.get(a));
                        sb.append(num.get(map.get(a))).append("\n");
//                        System.out.println(num.get(map.get(a)));
                    }
                } else if (map.containsKey(b)) { //b가 속한 네트워크가 있음
                    //b 그룹에 a 넣기
                    num.put(map.get(b), num.get(map.get(b)) + 1);
                    map.put(a, map.get(b));
                    sb.append(num.get(map.get(a))).append("\n");
//                    System.out.println(num.get(map.get(a)));
                }else{// 대장을 a로
                    map.put(a,a);
                    map.put(b,a);
                    num.put(a,2);
                    sb.append(2).append("\n");
//                    System.out.println(2);
                }
            }
        }
        System.out.println(sb);
    }

}