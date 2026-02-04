public class Bus 
{
    public static final double BASE_PASSENGER = 20.0;	// 기준 승객수
    private static int distanceRate=50;			// 거리 요율

    private String no;
    private int departure;
    private int destination;

    private int passengers;				// 승객수

    public Bus(String no, int departure, int destination, int passengers)
    {
        this.no = no; 
        this.departure = departure;
        this.destination = destination;
        this.passengers = passengers;
    }

      public int calcTollFee() 					// 통행료 계산
      {  
         double passengerRate = passengers / BASE_PASSENGER;
         int distance = destination - departure;
         int toll = (int) (distance * distanceRate * passengerRate);
         return toll;                            
      }
      public String getNo() { return no; }
      public int getDeparture() { return departure; }
      public int getDestination() { return destination; }
      public int getPassengers() { return passengers; }
      public static void setDistanceRate(int d) { distanceRate = d; }
      public static int getDistanceRate() { return distanceRate; }
   }