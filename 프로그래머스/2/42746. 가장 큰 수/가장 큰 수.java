import java.util.*;

class Item implements Comparable<Item> {
    private String num;

    public Item(int num) {
        this.num = num + "";
    }

    public String getNum() {
        return num;
    }

    @Override
    public int compareTo(Item o) {
        return (o.num+this.num).compareTo(this.num+o.num);
    }
}

class Solution {
    public String solution(int[] numbers) {
        String answer = "";
        boolean flag = true;
        
        PriorityQueue<Item> queue = new PriorityQueue<>();
        for (int number : numbers) {
            if(number != 0){
                flag = false;
            }
            queue.add(new Item(number));
        }
        if(flag)
            return "0";
        while (!queue.isEmpty()) {
            answer += queue.poll().getNum();
        }
        return answer;
    }
}