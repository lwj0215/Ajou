public class Reservation {
    private Car car;
    private Customer customer;
    private Date date;
    private int period;
    private int fee;
    
    private int[] days = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    public Reservation(Car car, Customer customer, Date date, int period) {
        this.car = car;
        this.customer = customer;
        this.date = date;
        this.period = period;
        updateFee();
    }
    public Car getCar() {
        return car;
    }
    public void setCar(Car car) {
        this.car = car;
    }
    public Customer getCustomer() {
        return customer;
    }
    public void setCustomer(Customer customer) {
        this.customer = customer;
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
