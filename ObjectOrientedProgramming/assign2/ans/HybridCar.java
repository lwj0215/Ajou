public class HybridCar extends Car{

    public HybridCar(int cc, String no){
        super(cc, no);
    }

    public char getType(){
        return 'h';
    }

    @Override
    public String toString(){
        return "하이브리드 승용차, " + super.getFullClass() + "(" + super.getCc() + "cc), " + super.getNo() + ", ";
    }

    @Override
    public int calcFee(){
        return (int)(super.calcFee() * 2 / 3); 
    }
}
