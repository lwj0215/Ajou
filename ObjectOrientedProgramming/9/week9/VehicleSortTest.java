
import java.util.Arrays;


public class VehicleSortTest
  {
     public static void main(String[] args)
     {
       Vehicle[] vs = new Vehicle[3];
       vs[0] = new Car("c1111", 0, 200, 3000);	// 3000은 volume
       vs[1] = new Car("c2222", 100, 300, 2000);	// 2000은 volume
       vs[2] = new Bus("b3333", 200, 400, 40);	// 40은 승객수


      Arrays.sort(vs, new Vehicle.TollComparator()); 

       for(Vehicle c: vs)
       {
          System.out.printf("%s : %d%n", c.getNo(), c.calcTollFee());
       }   
     }
  }
