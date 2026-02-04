public class Bus extends Vehicle
{
    public static final double BASE_PASSENGER = 20.0;	// 기준 승객수
    //private static int distanceRate=50;			// 거리 요율, 삭제
    // private String no; 삭제
    // private int departure; 삭제
    // private int destination; 삭제
    private int passengers;				// 승객수

    public Bus(String no, int departure, int destination, int passengers)
    {
        super(no, departure, destination);
        this.passengers = passengers;
    }

    @Override
      public int calcTollFee() 					// 통행료 계산
      {  
         double passengerRate = passengers / BASE_PASSENGER;
        // int distance = destination - departure; 삭제 및 수정
        int distance = getDestination() - getDeparture();
        // int toll = (int) (distance * distanceRate * passengerRate); 삭제 및 수정
        int toll = (int) (distance * getDistanceRate() * passengerRate);
         return toll;                            
      }
    //   public String getNo() { return no; } 삭제
    //   public int getDeparture() { return departure; } 삭제
    //   public int getDestination() { return destination; } 삭제
    public int getPassengers() { return passengers; }
    //   public static void setDistanceRate(int d) { distanceRate = d; } 삭제
    //   public static int getDistanceRate() { return distanceRate; } 삭제
   }
   