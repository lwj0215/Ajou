import java.util.*;
import java.lang.Integer.*;

public class Validator {
    ArrayList<Vehicle> vehicleList;

    public Validator(ArrayList<Vehicle> vehicleList){
        this.vehicleList = vehicleList;
    }    
    public void setVehicleList(ArrayList<Vehicle> vehicleList) {
        this.vehicleList = vehicleList;
    }

    public boolean validVechicle(char vehicleType, String carProperty){
        switch(vehicleType) {
            case 'g': return validGasoline(carProperty);
            case 'h': return validHybrid(carProperty);
            case 'b': return validBus(carProperty);
            case 't': return validTruck(carProperty);
            default: {
                System.out.println("Invalid vehicle input"); 
                return false;
            }
        }
    }

    public boolean validGasoline(String carProperty){
        try{
            int cc = Integer.parseInt(carProperty);
        }
        catch(NumberFormatException e){
            if(carProperty.equals("e") || carProperty.equals("s") || carProperty.equals("p") || carProperty.equals("l")){
                return true;
            }
            System.out.println("Invalid Gasoline car class input");
            return false;
        }
        return true;
    }

    public boolean validHybrid(String carProperty){
        try{
            int cc = Integer.parseInt(carProperty);
        }
        catch(NumberFormatException e){
            return false;
        }
        return true;
    }

    public boolean validBus(String carProperty){
        try{
            int maxPassenger = Integer.parseInt(carProperty);
        }
        catch(NumberFormatException e){
            return false;
        }
        return true;
    }

    public boolean validTruck(String carProperty){
        try{
            int ton = Integer.parseInt(carProperty);
        }
        catch(NumberFormatException e){
            return false;
        }
        return true;
    }
}
