import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();
        String line;
        while (!((line=br.readLine()).equals("."))){
            Stack<Character> stack = new Stack<>();
            int size = line.length();
            boolean flag = true;
            for(int c = 0; c < size;c++){
                char temp = line.charAt(c);
                if(temp=='[' || temp=='('){
                    stack.add(temp);
                } else if (temp==']') {
                    if(stack.size()==0){
                        flag = false;
                        break;
                    }
                    if(stack.pop()=='[')
                        continue;
                    else {
                        flag = false;
                        break;
                    }
                }else if (temp==')') {
                    if(stack.size()==0){
                        flag = false;
                        break;
                    }
                    if(stack.pop()=='(')
                        continue;
                    else {
                        flag = false;
                        break;
                    }
                }
            }
            if(stack.size()>0)
                flag = false;
            if(flag){
                sb.append("yes").append("\n");
            }else {
                sb.append("no").append("\n");
            }
        }
        System.out.println(sb);
    }

}