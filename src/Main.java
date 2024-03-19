import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
public class Main {
    public static void main(String[] args) {

        String A = "5 + 3 * (2 - 4)";

        System.out.println(SolveExpression(A));

        try {
            BufferedReader br = new BufferedReader(new FileReader("Arithmetic.txt"));
            BufferedWriter bw = new BufferedWriter(new FileWriter("ArithmeticAnswer.txt"));

            String line;
            while ((line = br.readLine()) != null) {
                // Process each line read from the file
                System.out.println("Expression is: " + line);
                bw.write("Expression: " + line + "\n");

                System.out.println("Answer of the Expression is: " + SolveExpression(line));
                bw.write("Answer: " + SolveExpression(line) + "\n");

                System.out.println();
                bw.newLine();
            }

            br.close();
            bw.close();
        } catch (IOException e) {
            System.out.println("File Could not be opened");
        }

    }

    /** This helper method takes care of any cases of "==" or "!=" and converts them to a character '=' or '!' for simplicty in calculation
     *
     * @param arithmetic the String which may or may not be modified if necessary
     * @return The proper arthritic expression to calculate
     */
    public static String SolveExpression (String arithmetic) {

        arithmetic = arithmetic.replace("==","=");
        arithmetic = arithmetic.replace("!=", "!");

        return SolveExpressionHelper(arithmetic);


    }


    /** Solves the Arithmetic Expression utilized two stacks
     *
     * @param arithmetic the String that has been converted from its "==" to '=' and "!=" to '!'
     * @return a String Answer ( a number or "True" or "False")
     */
    public static String SolveExpressionHelper(String arithmetic) {

        IntegerStack value_stack = new IntegerStack();      // int stack
        Stack operating_stack = new Stack();                // char stack

        boolean hasInequality = false;

        for (int i = 0; i < arithmetic.length(); i++) {
            char token = arithmetic.charAt(i);

            // Ignore white spaces
            if (token == ' ')
                continue;

            // If token is a digit
            if (Character.isDigit(token)) {
                int num = 0;

                // Converts the char number token into an int and then checks for consecutive digits following it, then concatenates if needed.
                while (i < arithmetic.length() && Character.isDigit(arithmetic.charAt(i))) {
                    num = num * 10 + (arithmetic.charAt(i) - '0');
                    i++;
                }

                // Num has been created so put it into the stack and decrement i so we don't skip another character in the following iteration
                i--;
                value_stack.push(num);
            } // end of if statement
            else if (token == '(') {
                operating_stack.push('(');

            } else if (token == ')') {

                while (operating_stack.peek() != '(') { // loop until we find the opening brackets
                    int num1, num2;
                    char operator;

                    num1 = value_stack.pop();            // gives us num1
                    num2 = value_stack.pop();           // gives us num2
                    operator = operating_stack.pop();  // gives us an operator

                    // Perform the operation and put its value into the value stack
                    value_stack.push(applyOperation(operator, num1, num2));
                } // end of while loop

                // remove the "(" from the stack
                operating_stack.pop();
            } // end of if-else statement



            else if (isOperator(token)) {

                while (!operating_stack.isEmpty() && precedence(operating_stack.peek()) >= precedence(token)) {
                    int num1, num2;
                    char operator;

                    num2 = value_stack.pop(); // Second value
                    num1 = value_stack.pop(); // First value
                    operator = operating_stack.pop();

                    // Perform the operation and push the result back onto the value stack
                    value_stack.push(applyOperation(operator, num1, num2));
                }

                // Push the current operator onto the stack.
                operating_stack.push(token);


            } // end of else if statement


        } // end of for-loop

        // Now clear the remaining elements of the stack (like inserting $ into the stack from Notes)
        // Clear remaining operators from the stack
        while (!operating_stack.isEmpty()) {
            char operator = operating_stack.pop();

            int num1 = value_stack.pop(); // Pop the first operand 4
            int num2 = value_stack.pop(); // Pop the second operand 5

            if (operator == '>' || operator == '<' || operator == '≥' || operator == '≤' || operator == '!' || operator == '=')
                hasInequality = true;

            // Apply the operation and push the result back onto the stack
            int result = applyOperation(operator, num1, num2);
            value_stack.push(result);
        }

        // Return the final result

        if(hasInequality) {
            if (value_stack.pop() == 1)
                return "True";
            else
                return "False";
        }
        else
            return "" + value_stack.pop();
    }



    /** Checks the value of the precedence
     *
     * @param operator is a char
     * @return the value of the precedence
     */
    public static int precedence(char operator) {
        switch (operator) {

            case '=':
            case '!':
                return 1;
            case '>':
            case '<':
            case '≥':
            case '≤':
                return 2;
            case '+':
            case '-':
                return 3;
            case '*':
            case '/':
                return 4;
            case '^':
                return 5; // Assuming ^ is used for exponentiation and has the highest precedence.
            default:
                return -1;
        }
    }



    /** This method will let us know if the operator is a valid or invalid operator
     *
     * @param op the supposed operator character
     * @return True or False
     */
    public static boolean isOperator(char op) {
        return op == '+' || op == '-' || op == '*' || op == '/' || op == '^'
                || op == '>' || op == '<' || op == '≥' || op == '≤' || op == '=' || op == '!';
    }




    /** Will do the operation
     *
     * @param operator is a char of the supposed operater
     * @param b is int value
     * @param a is another int value
     * @return the final value of the this simple expression
     */
    public static int applyOperation(char operator, int b, int a) {
        switch (operator) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                if (b == 0) throw new UnsupportedOperationException("Cannot divide by zero");
                return b / a;
            case '^':
                return (int) Math.pow(a, b);

            case '>':
                return a > b ? 1 : 0;   // If 1 then a > b and 0
            case '<':
                return a < b ? 1 : 0;
            case '≥':
                return a >= b ? 1 : 0;
            case '≤':
                return a <= b ? 1 : 0;

            case '=':
                return a == b ? 1 : 0; // If 1 then a == b
            case '!':
                return a != b ? 1 : 0; // If 1 then a != b

            default:
                throw new IllegalArgumentException("Invalid operator: " + operator);
        }
    }




}

