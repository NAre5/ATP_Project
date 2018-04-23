package test;

import java.util.Collection;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class Test {
    public static void main(String[] args) {
        Queue<Integer> list = new PriorityQueue<>();
        list.add(9);
        list.add(7);
        list.add(5);
        System.out.println(list);
        System.out.println(list.poll());
        System.out.println(list.poll());
        System.out.println(list.poll());
    }
}
