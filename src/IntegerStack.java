import java.util.EmptyStackException;

public class IntegerStack {

    private int capacity = 10;
    private int[] stack;
    private int top;

    public IntegerStack() {
        stack = new int[capacity];
        top = 0;
    }

    public void push(int data) {
        if (top == capacity) {
            expand();
        }
        stack[top] = data;
        top++;
    }

    public int pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        top--;
        int data = stack[top];
        shrink();
        return data;
    }

    private void expand() {
        capacity *= 2;
        int[] newStack = new int[capacity];
        System.arraycopy(stack, 0, newStack, 0, top);
        stack = newStack;
    }

    private void shrink() {
        if (top > 0 && top <= capacity / 4) {
            capacity /= 2;
            int[] newStack = new int[capacity];
            System.arraycopy(stack, 0, newStack, 0, top);
            stack = newStack;
        }
    }

    public int peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return stack[top - 1];
    }

    public int getSize() {
        return top;
    }

    public boolean isEmpty() {
        return top == 0;
    }
}

