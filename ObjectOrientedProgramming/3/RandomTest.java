import java.math.*;

public class RandomTest 
{  public static void main(String[] args)
     {
       int n = 45;
       int[] result = new int[6];
       random6(result, 1, 45);
       for(int e: result)
            System.out.printf("%d ", e);
       System.out.println();
     }   
     public static void random6(int[] result, int m, int n){
          int r=0;
          boolean v=false;
          for(int i=0;i<result.length;i++){
               do{
                    v = false;
                    r = (int) (Math.random()*45+1);
                    for(int j=0;j<=i;j++){
                         if(result[j]==r)
                              v=true;
                    }
               } while(v);            
               result[i] = r;
          }
          
      }
}
