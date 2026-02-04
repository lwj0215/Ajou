public class Time {
    private int hour;
    private int minute;

    public Time(int hh, int mm) // 0<=hh<24, 0<=mm<60
    {
        hour = hh; minute = mm;
    }

    // Getter
    public int getHour() { return hour; }
    public int getMinute() { return minute; }

    @Override
    public String toString() {
        // 3-(가), 반환 형식은 hh:mm
        return String.format("%02d:%02d",this.hour,this.minute);
    }

    public void add(int hh, int mm) {
        // 3-(나)
        this.minute += mm;
        this.hour += (hh + this.minute/60);
        this.minute %= 60;
        this.hour %= 24;
    }

    public void add(Time t) {
        // 3-(나)
        this.add(t.hour, t.minute);
    }
}
