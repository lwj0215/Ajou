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
    }

    public void add(int hh, int mm) {
        // 3-(나)
    }

    public void add(Time t) {
        // 3-(나)
    }
}
