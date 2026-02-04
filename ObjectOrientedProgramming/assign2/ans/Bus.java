public class Bus extends Vehicle {
    private int maxPassenger;

    public Bus(int maxPassenger, String no) {
        super(no);
        this.maxPassenger = maxPassenger;
    }

    public int getMaxPassenger() {
        return maxPassenger;
    }
    public char getType(){
        return 'b';
    }

    @Override
    public String toString(){
        return "버스, " + this.maxPassenger + "인승, " + super.getNo() + ", ";
    }
    
    @Override
    public int calcFee(){
        int m = this.maxPassenger;
        if(m < 12) return 100000;
        else if(m <24) return 150000;
        else return 200000;
    }
}
