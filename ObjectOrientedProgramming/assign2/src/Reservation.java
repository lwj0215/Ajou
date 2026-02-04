public class Reservation implements Comparable<Reservation> {
    private int vehicleIndex;
    private Customer customer;
    private Date date;
    private int period;
    private int basicFee;
    private int fee;

    public Reservation(int vehicleIndex, Date date, int period, Customer customer, int basicFee) {
        this.vehicleIndex = vehicleIndex;
        this.customer = customer;
        this.date = date;
        this.period = period;
        this.basicFee = basicFee;
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
        if(this.period > 5 && this.basicFee < 100000) this.fee = this.basicFee*5 + (this.basicFee-10000)*(this.period - 5);
        else this.fee = this.basicFee*this.period;
    }

    @Override
    public String toString() {
        return this.date.toString() + ", " + this.customer.toString();
    }

    @Override
    public int compareTo(Reservation r){
        return this.date.toInt() - r.date.toInt();
    }
}
