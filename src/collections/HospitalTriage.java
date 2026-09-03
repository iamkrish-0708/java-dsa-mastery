package collections;

import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class HospitalTriage {
    public static void main(String[] args) {
        Queue<String> regularQueue=new LinkedList<>();
        regularQueue.offer("Patient John");
        regularQueue.offer("Patient Alice");
        regularQueue.offer("Patient Bob");
        System.out.println(regularQueue.peek());
        String dequeue1=regularQueue.poll();
        System.out.println(dequeue1);
        String dequeue2=regularQueue.poll();
        System.out.println(dequeue2);

        PriorityQueue<Integer> icuSeverity=new PriorityQueue<>(Collections.reverseOrder());
        icuSeverity.offer(45);
        icuSeverity.offer(70);
        icuSeverity.offer(30);
        icuSeverity.offer(65);
        int priorityDeque1=icuSeverity.poll();
        System.out.println(icuSeverity);


    }
}
