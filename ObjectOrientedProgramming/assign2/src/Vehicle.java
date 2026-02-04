public abstract class Vehicle {
    private String no;
    private String state = "NR"; // NR=non-reserve RSV=reserved CI=checkin CO=checkout
    private int fee;

    protected Vehicle(String no) {
        this.no = no;
        this.fee = calcFee();
    }
    public String getNo() {
        return no;
    }
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
    public int getFee() {
        return calcFee();
    }
    public abstract char getType();

    public abstract int calcFee();
}
