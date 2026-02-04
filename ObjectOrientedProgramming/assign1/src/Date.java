public class Date {
    private int year;
    private int month;
    private int day;
    private boolean valid;
    private int[] monthDays = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    public Date(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.valid = (day <= monthDays[month]);
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public boolean getValid(){
        return valid;
    }

    public void changeDate(int y, int m, int d){
        this.year = y;
        this.month = m;
        this.day = d;
    }

    public boolean compare(Date d){
        if(this.year == d.getYear()){
            if(this.month == d.getMonth()){
                return this.day > d.getDay();
            }
            return this.month > d.getMonth();
        }
        return this.year > d.getYear();
    }

    public int toInt(){
        int r = 365*this.year + this.day;
        for(int i = 0;i<this.month-1;i++){
            r += monthDays[i];
        }
        return r;
    }
}
