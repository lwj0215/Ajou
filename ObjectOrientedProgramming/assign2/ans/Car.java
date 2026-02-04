public class Car extends Vehicle{
    private char classification; //e s p l
    private String fullClass;
    private int cc;

    public Car(int cc, String no){
        super(no);
        this.cc = cc;
        this.classification = calcClassfication(cc);
        this.fullClass = findClassfication();
    }

    public char getClassification() {
        return classification;
    }
    public String getFullClass() {
        return fullClass;
    }
    public int getCc() {
        return cc;
    }
    public char getType(){
        return 'g';
    }

    @Override
    public String toString(){
        return "가솔린 승용차, " + this.fullClass + "(" + this.cc + "cc), " + super.getNo() + ", ";
    }

    private char calcClassfication(int cc){
        if(cc >= 3000) return 'l';
        else if(cc >= 2000) return 'p';
        else if(cc >= 1000) return 's';
        else return 'e';
    }

    private String findClassfication(){
        char c = this.classification;
        switch(c){
            case 'e': return "economy";
            case 's': return "standard";
            case 'p': return "premium";
            case 'l': return "luxury";
            default: return "ERR";
        }
    }

    @Override
    public int calcFee(){
        switch(this.classification){
            case 'e': return 30000;
            case 's': return 40000;
            case 'p': return 50000;
            case 'l': return 60000;
            default: return -1;
        }
    }
}
