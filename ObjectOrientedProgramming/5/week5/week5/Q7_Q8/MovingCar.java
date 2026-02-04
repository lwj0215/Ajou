      public class MovingCar
      {
         Car car;
         int departure;
         int destination;

         public Car getCar(){

            return this.car;
         }

         public MovingCar(Car c, int dept, int dest)
         {
            car = c; 
            this.departure = dept;
            this.destination = dest; 
         }
         public int calcTollFee() 
         {
               //해당부분작성
         }
           
      }
