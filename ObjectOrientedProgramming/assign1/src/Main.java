import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    static Car[] carList;
    static Reservation[] reservationList = new Reservation[99999];
    static int reservationCount = 0;
    static Date currentTime = new Date(2025,9,25);
    static Scanner in;
    static Scanner inf; 

    public static void main(String[] args) {
        readCars();
        in = new Scanner(System.in);
        do{
            char cmd = readCommand();
            if(cmd == 'r') makeReservation();
            else if(cmd == 'c') cancelReservation();
            else if(cmd == 'o') checkIn();
            else if(cmd == 'i') checkOut();
            else if(cmd == 'v') showReservation();
            else if(cmd == 'a') showCheckIn();
            else if(cmd == 'p') showIncome();
            else if(cmd == 'd') setDate();
            else if(cmd == 'q') System.exit(0);
            else System.out.println("Invalid Command Input");
        } while(true);
    }

    public static void readCars(){
        try{
            inf = new Scanner(new File("rentalcars.txt"));
        }
        catch(FileNotFoundException e){
            System.out.println(e.getMessage());
        }
        int n = inf.nextInt();
        carList = new Car[n];
        for(int i = 0;i<n;i++){
            int a = inf.nextInt();
            String b = inf.next();
            carList[i] = new Car(a,b);
        }
    }

    public static char readCommand(){
        String var0 = in.next();
        return var0.charAt(0);
    }
    
    public static void makeReservation(){
        char carClass = in.next().charAt(0);        
        int year = in.nextInt();
        int month = in.nextInt();
        int day = in.nextInt();
        int period = in.nextInt();
        String name = in.next();
        String hp = in.next();
        if(carClass != 'e' && carClass != 's' && carClass != 'p' && carClass != 'l'){
            System.out.println("Invalid car input");
            return;
        }
        Date date = new Date(year, month, day);
        if(!date.getValid() || !date.compare(currentTime)){
            System.out.println("Invalid date input");
            return;
        }
        Customer customer = new Customer(name, hp);
        if(customerValid(customer, date, period)){
            System.out.println("Already have reservation on this day");
            return;
        }
        Car car = carSelect(carClass,date, period);
        if(car.getCc() == 0){
            System.out.println("예약가능한 차량 없음");
            return;
        }
        reservationList[reservationCount] = new Reservation(car, customer, date, period);
        System.out.println(car.findClassfication() + ", " + year + "년" + month + "월" + day + "일(" + period+ "일), " + name + "(" + hp + ")" + ", 예약");
        reservationCount++;
    }

    public static void cancelReservation(){
        String hp = in.next();
        int year = in.nextInt();
        int month = in.nextInt();
        int day = in.nextInt();
        if(!new Date(year, month, day).getValid()){
            System.out.println("Invalid date input");
            return;
        }
        boolean v = true;
        for(int i = 0;i<reservationCount;i++){
            Reservation r = reservationList[i];
            if(r.getCustomer() != null){
                if(r.getCustomer().getTel().equals(hp) && r.getDate().getDay() == day && r.getDate().getMonth() == month && r.getDate().getYear() == year && r.getCar().getState().equals("RSV")){
                    System.out.printf("%s, %d년%d월%d일(%d일), %s(%s), 취소\n",r.getCar().findClassfication(), year, month, day, r.getPeriod(), r.getCustomer().getName(), hp);
                    reservationList[i].setCar(null);
                    reservationList[i].setCustomer(null);                    
                    reservationList[i].setDate(null);    
                    reservationList[i].setFee(0);
                    reservationList[i].setPeriod(0);
                    v = false;
                    break;
                }
            }
        }
        if(v) System.out.println("예약한 차량 없음");
    }

    public static void checkIn(){
        String hp = in.next();
        boolean v = true;
        for(int i = 0;i<reservationCount;i++){
            Reservation r = reservationList[i];
            if(r.getCustomer() != null){
                if(r.getCustomer().getTel().equals(hp) && r.getCar().getState() == "RSV" && r.getDate().getYear() == currentTime.getYear() && r.getDate().getMonth() == currentTime.getMonth() && r.getDate().getDay() == currentTime.getDay()){
                    reservationList[i].getCar().setState("CI");
                    System.out.printf("%s(%dcc), %s, %d년%d월%d일(%d일), %s(%s), 대여\n",r.getCar().findClassfication(),r.getCar().getCc(), r.getCar().getNo(), r.getDate().getYear(), r.getDate().getMonth(), r.getDate().getDay(), r.getPeriod(), r.getCustomer().getName(), hp);
                    v = false;
                    break;
                }
            }
        }
        if(v) System.out.println("예약된 차량 없음");
    }

    public static void checkOut(){
        String hp = in.next();
        boolean v = true;
        for(int i = 0;i<reservationCount;i++){
            Reservation r = reservationList[i];
            if(r.getCustomer() != null){
                if(r.getCustomer().getTel().equals(hp) && r.getCar().getState().equals("CI")){
                    System.out.printf("%s(%dcc), %s, %d년%d월%d일(%d일), %s(%s), 반납\n",r.getCar().findClassfication(),r.getCar().getCc(), r.getCar().getNo(), currentTime.getYear(), currentTime.getMonth(), currentTime.getDay(), r.getPeriod(), r.getCustomer().getName(), hp);
                    reservationList[i].setPeriod(currentTime.toInt() - r.getDate().toInt() + 1);
                    reservationList[i].updateFee();
                    System.out.printf("대여요금: %,d원\n",r.getFee());
                    reservationList[i].getCar().setState("CO");
                    v=false;
                    break;
                }
            }
        }
        if(v) System.out.println("현재 대여중인 차량 없음");
    }

    public static void showReservation(){
        for(int i = 0;i<reservationCount;i++){
            Reservation r = reservationList[i];
            if(r.getCar() != null){
                if(r.getCar().getState().equals("RSV")){
                    System.out.printf("%s(%dcc), %s, %d년%d월%d일(%d일), %s, %s\n",r.getCar().findClassfication(),r.getCar().getCc(), r.getCar().getNo(), r.getDate().getYear(), r.getDate().getMonth(), r.getDate().getDay(), r.getPeriod(), r.getCustomer().getName(), r.getCustomer().getTel());
                }
            }
        }
    }
    public static void showCheckIn(){
        for(int i = 0;i<reservationCount;i++){
            Reservation r = reservationList[i];
            if(r.getCar() != null){
                if(r.getCar().getState().equals("CI")){
                    System.out.printf("%s(%dcc), %s, %d년%d월%d일(%d일), %s, %s\n",r.getCar().findClassfication(),r.getCar().getCc(), r.getCar().getNo(), r.getDate().getYear(), r.getDate().getMonth(), r.getDate().getDay(), r.getPeriod(), r.getCustomer().getName(), r.getCustomer().getTel());
                }
            }
        }
    }

    public static void showIncome(){
        int res = 0;
        for(int i = 0;i<reservationCount;i++){
            Reservation r = reservationList[i];
            if(r.getDate() != null){
                int d = r.getDate().toInt() + r.getPeriod();
                int s = new Date(currentTime.getYear(), currentTime.getMonth(), 1).toInt();
                int e = new Date(currentTime.getYear(), currentTime.getMonth() + 1, 0).toInt();
                if(d >= s && d <= e){
                    res += r.getFee();
                }
            }
        }
        System.out.printf("%d년 %d월 총수입: %,d원\n",currentTime.getYear(),currentTime.getMonth(),res);
    }

    public static void setDate(){
        int year = in.nextInt();
        int month = in.nextInt();
        int day = in.nextInt();
        if(!new Date(year, month, day).getValid()){
            System.out.println("Invalid date input");
            return;
        }
        currentTime.setYear(year);
        currentTime.setMonth(month);
        currentTime.setDay(day);
        for(int i = 0;i<reservationCount;i++){
            Reservation r = reservationList[i];
            if(r.getCar() != null){
                if(r.getCar().getState().equals("RSV") && r.getDate().toInt() < currentTime.toInt()){
                    reservationList[i].setCar(null);
                    reservationList[i].setCustomer(null);                    
                    reservationList[i].setDate(null);    
                    reservationList[i].setFee(0);
                    reservationList[i].setPeriod(0);
                }                
            }
        }
    }

    public static Car carSelect(char clas, Date date, int period){
        for(Car c : carList){
            if(c.getClassification() == clas && c.getState().equals("NR")){
                c.setState("RSV");
                return c;
            }
        }
        for(int i = 0;i<reservationCount;i++){
            Reservation r = reservationList[i];
            if(r.getCar() != null){
                if(r.getCar().getClassification() == clas){
                    if(r.getDate().toInt() + r.getPeriod() < date.toInt()){
                        return r.getCar();
                    }
                    if(date.toInt() + period < r.getDate().toInt()){
                        return r.getCar();
                    }
                }
            }
        }
        Car car = new Car(0,"a");
        return car;
    }
    
    public static boolean customerValid(Customer c, Date d, int p){
        for(int i = 0;i<reservationCount;i++){
            Reservation r = reservationList[i];
            if(r.getCustomer() != null){
                if(r.getCustomer().getTel() == c.getTel()){
                    if(r.getDate().toInt() <= d.toInt() &&r.getDate().toInt() + r.getPeriod() >= d.toInt()){
                        return true;
                    }
                    if(d.toInt() <= r.getDate().toInt() && d.toInt() + p >= r.getDate().toInt()){
                        return true;
                    }
                }
            }
        }
        return false;
    }
}