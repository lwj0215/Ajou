public class Stack
{
    private static final int MAX = 10;
    private static int top=0;  
    private static int[] s = new int[MAX];
    public static int pop()
    {
         if (top == 0)  {
              System.out.println("Empty!");
              System.exit(-1);
         } else      top--;
         return s[top];
    }
    public static void push(int x)
    {
       if(top==MAX) {
           System.out.println("Full!");
           System.exit(-1);
       } else  {
           s[top] = x;
           top++;
       }
       return;   
    }
}
