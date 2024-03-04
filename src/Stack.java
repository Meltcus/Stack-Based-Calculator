import java.util.LinkedList;
import java.util.EmptyStackException;

public class Stack {

    int capacity = 10;
    char[] stack = new char[capacity];
    int top = 0;

    public void push(char data) {

        if (getSize() == capacity) {
            expand();
        }


        stack[top] = data;
        top++;


    }

    public char pop() {

        char data;

        if (isEmpty()) {
            throw new EmptyStackException();
        }

        else {
            top--;
            data = stack[top];
            stack[top] = '@';
            shrink();
        }

        return data;

    }

    public void expand() {
        int length = getSize();

        char[] new_stack = new char[capacity*2];
        System.arraycopy(stack,0,new_stack,0,length);

        capacity *=2;
        stack = new_stack;

    }

    public void shrink() {

        int length = getSize();

        if (length <= (capacity/2) / 2) {
            capacity = capacity/2;
        }

        char[] new_stack = new char[capacity];
        System.arraycopy(stack,0,new_stack,0,length);
        stack = new_stack;

    }

    public char peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }

        else
            return stack[top-1];
    }

    public int getSize() {
        return top;
    }

    public boolean isEmpty() {
        return top==0;
    }
}
