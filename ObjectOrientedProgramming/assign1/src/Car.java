public class Car {
    private char classification; //e s p l
    private String no;
    private int cc;
    private String state = "NR"; // NR=non-reserve RSV=reserved CI=checkin CO=checkout
    private int fee;

    public Car(int cc, String no){
        this.cc = cc;
        this.no = no;
        this.classification = calcClassfication(cc);
        this.fee = calcFee();
    }

    public char getClassification() {
        return classification;
    }

    public void setClassification(char type) {
        this.classification = type;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public int getCc() {
        return cc;
    }

    public void setCc(int cc) {
        this.cc = cc;
    }

    public void setState(String state){
        this.state = state;
    }

    public String getState(){
        return this.state;
    }

    public int getFee(){ return this.fee; }

    @Override
    public String toString(){
        return this.findClassfication() + "(" + this.cc + "), " + this.no + ", ";
    }

    private char calcClassfication(int cc){
        if(cc >= 3000) return 'l';
        else if(cc >= 2000) return 'p';
        else if(cc >= 1000) return 's';
        else return 'e';
    }

    public String findClassfication(){
        char c = this.classification;
        switch(c){
            case 'e': return "economy";
            case 's': return "standard";
            case 'p': return "premium";
            case 'l': return "luxury";
            default: return "ERR";
        }
    }

    private int calcFee(){
        switch(this.classification){
            case 'e': return 30000;
            case 's': return 40000;
            case 'p': return 50000;
            case 'l': return 60000;
            default: return -1;
        }
    }
}
