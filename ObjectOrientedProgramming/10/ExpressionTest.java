import java.util.*;

interface Expression {
    int eval(int a, int b);
}

public class ExpressionTest {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        char op = in.next().charAt(0);
        int x = in.nextInt();
        int y = in.nextInt();
        Expression exp = null;

        switch (op) {
            // 3-(A): op에 따라 exp 변수에 적절한 lambda expression을 지정하는 코드를 작성하시오.
            case '+': exp = (a,b)->a+b; break; 
            case '-': exp = (a,b)->a-b; break;
            case '*': exp = (a,b)->a*b; break;
        }
        int result = f(exp, x,y);  // 3-(B): (A)의 exp을 연산하는 코드를 작성하여 연산 결과를 result에 반환하시오.
        System.out.println("result = "+ result);
    }
    public static int f(Expression e, int a, int b) {
        return e.eval(a, b);
    }
}