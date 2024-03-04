public class TheoryQ3 {

    public static void main(String[] args) {

        int[] A = {22, 9, 61,61, 61, 21, 0, 9, 9, 9, 9, 35, 81,81, 9, 5, 5};

        Consecutive(A);

    }


    public static void Consecutive(int[] A) {
        Queue queue = new Queue();
        int value, counter = 0, consecutive = 1;

        for (int n : A)
            queue.enqueue(n);

        while (!queue.isEmpty()) {
            value = queue.dequeue();
            counter++;
            consecutive = 1;

            // if a consecutive occurs
            while (!queue.isEmpty() && value == queue.peek()) {
                consecutive++;
                value = queue.dequeue();
                counter++;
            }

            if (consecutive > 1)
                System.out.println("Value " + value + " is repeated " + consecutive + " times at index " + (counter - consecutive));
        }
    }


}
