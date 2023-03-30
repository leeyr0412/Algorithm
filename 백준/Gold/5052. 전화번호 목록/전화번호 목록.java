import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 전화번호 목록 / 골드4 / 걸린시간 / 3월 30일
 */

class Char {
    private char curr;


    private List<Char> child;
    private boolean end;


    public Char(char curr, List<Char> child, boolean end) {
        this.curr = curr;
        this.child = child;
        this.end = end;
    }

    public char getCurr() {
        return curr;
    }

    public List<Char> getChild() {
        return child;
    }

    public boolean isEnd() {
        return end;
    }

    public void setEnd(boolean end) {
        this.end = end;
    }
}

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;// = new StringTokenizer(br.readLine());

        int t = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < t; tc++) {
            int n = Integer.parseInt(br.readLine());
            String[] phone = new String[n];
            for (int i = 0; i < n; i++) {
                phone[i] = br.readLine(); //입력받은 문자열
            }
            Char tree = new Char('r', new ArrayList<>(), false);
            Char curr = tree;
            //첫번째 전화번호 등록하기
            for (int i = 0; i < phone[0].length(); i++) {
                curr.getChild().add(new Char(phone[0].charAt(i), new ArrayList<>(), false));
                curr = curr.getChild().get(0);
            }
            curr.setEnd(true);
            boolean answer = true;      //정답 출력 위한 변수
            for (int num = 1; num < n && answer; num++) {   //입력받은 2번째 번호부터 돌리기 num:phone index
                curr = tree;
                for (int i = 0; i < phone[num].length() && answer; i++) {
                    char c = phone[num].charAt(i);
                    boolean findC = false;
                    for (Char child : curr.getChild()) {
                        if (child.getCurr() == c) {
                            findC = true;//문자 겹침
                            if (child.isEnd()) {      //키워드임
                                answer = false;
                                break;
                            } else {
                                curr = child;
                                break;
                            }
                        }
                    }
                    if (!findC) { //겹치지 않아서 문자열 다 넣어줘야함
                        for (int j = i; j < phone[num].length(); j++) {
                            curr.getChild().add(new Char(phone[num].charAt(j), new ArrayList<>(), false));
                            curr = curr.getChild().get(curr.getChild().size()-1);
                        }
                        curr.setEnd(true);
                        break;
                    }
                }
                if(curr.getChild().size()>0){
                    answer=false;
                    curr.setEnd(true);
                }
            }
            if (answer) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }
}