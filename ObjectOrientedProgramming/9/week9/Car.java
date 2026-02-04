public class Car
{ 
    public static final double BASE_VOLUME = 2000.0;  // 기준 배기량
    private static int distanceRate = 50; 		// 거리 요율

    private String no;
    private int departure;
    private int destination;
    private int volume;					// 차량 배기량

    public Car(String no, int departure, int destination, int volume)
    {
        this.no = no; 
        this.departure = departure;
        this.destination = destination;
        this.volume = volume;
    }
    public int calcTollFee() 				// 통행료 계산
    { 
         double volumeRate = volume / BASE_VOLUME;
         int distance = getDestination() - getDeparture();
         int toll = (int) ( distance * distanceRate * volumeRate );
         return toll;
    }
    public String getNo() { return no; }
    public int getDeparture() { return departure; }
    public int getDestination() { return destination; }
    public int getVolume() { return volume; }

    public static void setDistanceRate(int d) { distanceRate = d; }
    public static int getDistanceRate() { return distanceRate; }
}