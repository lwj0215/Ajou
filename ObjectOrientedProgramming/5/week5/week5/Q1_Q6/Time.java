public class Time {
    private int hour;
    private int minute;

    public Time(int hh, int mm) {
        this.hour = hh;
        this.minute = mm;
    }

    // Getter 메소드
    public int getHour() {
        return this.hour;
    }

    public int getMinute() {
        return this.minute;
    }

    public int calculateDifference(Time t)
    {
                //해당부분작성
    }


    @Override
    public String toString() {
        return String.format("%02d:%02d", this.hour, this.minute);
    }


    public void add(int hh, int mm) {
        this.minute += mm;
        if (this.minute >= 60) {
            this.hour += this.minute / 60; 
            this.minute %= 60;            
        }
        
        this.hour += hh;
        this.hour %= 24; 
    }


    public void add(Time t) {

        this.add(t.getHour(), t.getMinute());

    }
}
