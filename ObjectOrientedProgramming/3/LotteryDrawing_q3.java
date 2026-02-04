/**
 * This program demonstrates array manipulation.
 * @version 1.20 2004-02-10
 * @author Cay Horstmann
 */
import java.math.*;

 public class LotteryDrawing_q3
{
   public static void main(String[] args)
   {
         final int n =45;
         final int k = 6;

      // fill an array with numbers 1 2 3 . . . n
      int[] numbers = new int[n];
      for (int i = 0; i < numbers.length; i++)
         numbers[i] = i + 1;

      // draw k numbers and put them into a second array
      for (int i = 0; i < k; i++)
      {
         int r = (int) (Math.random()*(45-i)+i);
         int tmp = numbers[i];
         numbers[i] = numbers[r];
         numbers[r] = tmp;
      }
      //print
      for(int i = 0;i<k;i++)
         System.out.print(numbers[i] + " ");
      System.out.println();
   }
}
