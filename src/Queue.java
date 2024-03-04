import java.util.LinkedList;

public class Queue {

    private LinkedList<Integer> queue;

    public Queue () {
        queue = new LinkedList<>();
    }

    public void enqueue(int num) {
        queue.addLast(num);
    }

    public int dequeue() {
        if(queue.isEmpty()) {
            throw new IllegalStateException("Queue is Empty");
        }

        return queue.removeFirst();
    }

    public int peek() {
        if (queue.isEmpty())
            throw new IllegalStateException("Queue is Empty");

        return queue.getFirst();
    }


    public int getSize() {
        return queue.size();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }



}
