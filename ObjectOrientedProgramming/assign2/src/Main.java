import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    static ArrayList<Vehicle> vehicleList = new ArrayList<>();
    static ArrayList<Reservation> reservationList = new ArrayList<Reservation>();
    static Date currentTime = new Date(2025,9,25);
    static Scanner in;
    static Scanner inf; 
    static Validator valid = new Validator(vehicleList);

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
        for(int i = 0;i<n;i++){
            char c = inf.next().charAt(0);
            int a = inf.nextInt();
            String b = inf.next();
            switch(c){
                case 'g' : vehicleList.add(new Car(a,b)); break;
                case 'h' : vehicleList.add(new HybridCar(a, b)); break;
                case 'b' : vehicleList.add(new Bus(a,b)); break;
                case 't' : vehicleList.add(new Truck(a,b)); break;
                default : System.out.println("Invalid Vehicle Input");
            }
        }
        valid.setVehicleList(vehicleList);
    }

    public static char readCommand(){
        String var0 = in.next();
        return var0.charAt(0);
    }
    
    public static void makeReservation(){
        char vehicleType = in.next().charAt(0);        
        String carProperty = in.next();       
        int year = in.nextInt();
        int month = in.nextInt();
        int day = in.nextInt();
        int period = in.nextInt();
        String name = in.next();
        String hp = in.next();
        // vehicle input valid
        if(!valid.validVechicle(vehicleType, carProperty)){
            return;
        }
        // date input valid
        Date date = new Date(year, month, day);
        if(!date.getValid() || !date.compare(currentTime)){
            System.out.println("Invalid date input");
            return;
        }
        // custom valid
        Customer customer = new Customer(name, hp);
        if(valid.customerValid(reservationList, customer, date, period)){
            System.out.println("Already have reservation on this day");
            return;
        }
        int carIndex = findVehicle(vehicleType, carProperty, date, period);
        if(carIndex == -1){
            System.out.println("예약가능한 차량 없음");
            return;
        }
        vehicleList.get(carIndex).setState("RSV");
        int basicFee = vehicleList.get(carIndex).getFee();
        reservationList.add(new Reservation(carIndex, date, period, customer, basicFee));
        System.out.println(vehicleList.get(carIndex).toString() + ", " + reservationList.get(reservationList.size()-1).toString() + ", 예약");
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
        for(int i = 0;i<reservationList.size();i++){
            Reservation r = reservationList.get(i);
            if(r.getCustomer().getTel().equals(hp) && r.getDate().getDay() == day && r.getDate().getMonth() == month && r.getDate().getYear() == year && vehicleList.get(r.getVehicleIndex()).getState().equals("RSV")){
                System.out.println(vehicleList.get(r.getVehicleIndex()).toString() + ", " + r.getDate().toString() + "(" + r.getPeriod() + "일), " + r.getCustomer().toString() + ", 취소");
                reservationList.remove(i);
                v = false;
                break;
            }
        }
        if(v) System.out.println("예약한 차량 없음");
    }

    public static void checkIn(){
        String hp = in.next();
        boolean v = true;
        for(Reservation r:reservationList){
            if(r.getCustomer().getTel().equals(hp) && vehicleList.get(r.getVehicleIndex()).getState() == "RSV" && r.getDate().getYear() == currentTime.getYear() && r.getDate().getMonth() == currentTime.getMonth() && r.getDate().getDay() == currentTime.getDay()){
                vehicleList.get(r.getVehicleIndex()).setState("CI");
                System.out.println(vehicleList.get(r.getVehicleIndex()).toString() + ", " + r.getDate().toString() + "(" + r.getPeriod() + "일), " + r.getCustomer().toString() + ", 대여");
                v = false;
                break;
            }
        }
        if(v) System.out.println("예약된 차량 없음");
    }

    public static void checkOut(){
        String hp = in.next();
        boolean v = true;
        for(Reservation r : reservationList){
            if(r.getCustomer().getTel().equals(hp) && vehicleList.get(r.getVehicleIndex()).getState().equals("CI")){
                r.setPeriod(currentTime.toInt() - r.getDate().toInt() + 1);
                r.updateFee();
                vehicleList.get(r.getVehicleIndex()).setState("CO");
                System.out.println(vehicleList.get(r.getVehicleIndex()).toString() + ", " + r.getDate().toString() + "(" + r.getPeriod() + "일), " + r.getCustomer().toString() + ", 반납");
                System.out.printf("대여요금: %,d원\n",r.getFee());
                v=false;
                break;
            
            }
        }
        if(v) System.out.println("현재 대여중인 차량 없음");
    }

    public static void showReservation(){
        reservationList.sort(Comparator.naturalOrder());
        boolean v = true;
        for(Reservation r : reservationList){
            if(vehicleList.get(r.getVehicleIndex()) != null){
                if(vehicleList.get(r.getVehicleIndex()).getState().equals("RSV")){
                    System.out.println(vehicleList.get(r.getVehicleIndex()).toString() + ", " + r.getDate().toString() + "(" + r.getPeriod() + "일), " + r.getCustomer().toString());
                    v = false;
                }
            }
        }
        if(v) System.out.println("예약 차량 없음");
    }
    public static void showCheckIn(){
        reservationList.sort(Comparator.naturalOrder());
        boolean v = true;
        for(Reservation r : reservationList){
            if(vehicleList.get(r.getVehicleIndex()).getState().equals("CI")){
                System.out.println(vehicleList.get(r.getVehicleIndex()).toString() + ", " + r.getDate().toString() + "(" + r.getPeriod() + "일), " + r.getCustomer().toString());
                v = false;
            }
        }
        if(v) System.out.println("현재 대여 차량 없음");
    }

    public static void showIncome(){
        int res = 0;
        for(Reservation r : reservationList){
            int d = r.getDate().toInt() + r.getPeriod();
            int s = new Date(currentTime.getYear(), currentTime.getMonth(), 1).toInt();
            int e = new Date(currentTime.getYear(), currentTime.getMonth() + 1, 0).toInt();
            if(d >= s && d <= e){
                res += r.getFee();
            }
        }
        System.out.printf("%d년 %d월 총수입: %,d원\n",currentTime.getYear(),currentTime.getMonth(),res);
    }

    public static void setDate(){
        int year = in.nextInt();
        int month = in.nextInt();
        int day = in.nextInt();
        currentTime.update(year, month, day);
        for(int i = 0;i<reservationList.size();i++){
            Reservation r = reservationList.get(i);
            if(vehicleList.get(r.getVehicleIndex()) != null){
                if(vehicleList.get(r.getVehicleIndex()).getState().equals("RSV") && r.getDate().toInt() < currentTime.toInt()){
                    vehicleList.get(r.getVehicleIndex()).setState("NR");
                    reservationList.remove(i);
                }                
            }
        }
        System.out.println("현재 날짜를 "+currentTime.toString()+"로 설정");
    }

    public static int findVehicle(char vehicleType, String carProperty, Date date, int period){
        for(int i=0;i<vehicleList.size();i++){
            Vehicle v = vehicleList.get(i);
            boolean flg = false;
            if(v.getType() == vehicleType){
                if(v instanceof Car){
                    int cc;
                    switch(carProperty){
                        case "e": cc= 0; break;
                        case "s": cc= 1000; break;
                        case "p": cc= 2000; break;
                        case "l": cc= 3000; break;
                        default: return -1;
                    }
                    if(cc <= ((Car)v).getCc()){
                        flg = true;
                    }
                }
                else if(v instanceof Bus){
                    int max = Integer.parseInt(carProperty);
                    if(max <= ((Bus)v).getMaxPassenger())
                        flg = true;
                }
                else{
                    int ton = Integer.parseInt(carProperty);
                    if(ton <= ((Truck)v).getTon())
                        flg = true;
                }
                if(flg){
                    boolean flag = true;
                    boolean avail = false;
                    for(Reservation r:reservationList){
                        if(r.getVehicleIndex() == i){
                            flag = false;
                            if(r.getDate().toInt() + r.getPeriod() < date.toInt() || r.getDate().toInt() > date.toInt() + period){
                                avail = true;
                            }
                        }
                    }
                    if(flag) avail = true;
                    if(avail) return i;
                }
            }
        }
        return -1;
    }
}