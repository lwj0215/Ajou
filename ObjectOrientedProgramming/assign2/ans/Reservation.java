public class Reservation {
    private int vehicleIndex;
    private Customer customer;
    private Date date;
    private int period;
    private int fee;

    public Reservation(int vehicleIndex, Date date, int period, Customer customer) {
        this.vehicleIndex = vehicleIndex;
        this.customer = customer;
        this.date = date;
        this.period = period;
        updateFee();
    }
    public int getVehicleIndex() {
        return vehicleIndex;
    }
    public Customer getCustomer() {
        return customer;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public int getPeriod() {
        return period;
    }
    public void setPeriod(int period) {
        this.period = period;
    }
    public int getFee() {
        return fee;
    }
    public void setFee(int fee) {
        this.fee = fee;
    }
    
    public void updateFee(){
        if(this.period > 5) this.fee = this.car.getFee()*5 + (this.car.getFee()-10000)*(this.period - 5);
        else this.fee = this.car.getFee()*this.period;
    }

    @Override
    public String toString() {
        return "";
    }
}
