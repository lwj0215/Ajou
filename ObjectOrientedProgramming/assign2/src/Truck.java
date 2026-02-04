public class Truck extends Vehicle{
    private int ton;

    public Truck(int ton, String no) {
        super(no);
        this.ton = ton;
    }

    public int getTon() {
        return ton;
    }

    public char getType(){
        return 't';
    }
    
    @Override
    public String toString(){
        return "버스, " + this.ton + "톤, " + super.getNo();
    }

    public int calcFee(){
        int t = this.ton;
        if(t<1) return 60000;
        else if(t < 4) return 100000;
        else return 150000;
    }
}
