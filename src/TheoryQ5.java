public class TheoryQ5 {

    public static void main(String[] args) {

        String A = "THEMTHENTHEY";

        char[] B = new char[A.length()];

        for (int i = 0; i < A.length() ; i++) {
            B[i] = A.charAt(i);
        }

        for (int i = 0; i < A.length() ; i++)
            System.out.print(B[i] + " ");


        int k = 0;
        char[] Cypher = new char[B.length];

        for (int i = 0; i < B.length ; i++) {
            Cypher[i] = (char) (B[i] + k + (i % 3));
        }

        System.out.println();

        for (int i = 0; i < Cypher.length ; i++)
            System.out.print(Cypher[i] + " ");


    }
}
