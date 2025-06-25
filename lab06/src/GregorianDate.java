public class GregorianDate extends Date {

    private static final int[] MONTH_LENGTHS = {
        31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31
    };

    public GregorianDate(int year, int month, int dayOfMonth) {
        super(year, month, dayOfMonth);
    }



    @Override
    public int dayOfYear() {
        int precedingMonthDays = 0;
        for (int m = 1; m < month; m += 1) {
            precedingMonthDays += getMonthLength(m);
        }
        return precedingMonthDays + dayOfMonth;
    }

    //nextDate returns the new date that is the result of advancing this date by one day

    @Override
    public Date nextDate() {
        // Leap year special case: Feb 28 → Feb 29
        if (this.month == 2 && this.dayOfMonth == 28 &&
                (this.year % 400 == 0 || (this.year % 4 == 0 && this.year % 100 != 0))) {
            return new GregorianDate(this.year, 2, 29);
        }

        // New Year's Eve: Dec 31 → Jan 1 next year
        if (this.month == 12 && this.dayOfMonth == 31) {
            return new GregorianDate(this.year + 1, 1, 1);
        }

        // End of month: 30th/31st → next month 1st
        if (this.dayOfMonth == getMonthLength(this.month)) {
            return new GregorianDate(this.year, this.month + 1, 1);
        }

        // Regular case: just increment day
        return new GregorianDate(this.year, this.month, this.dayOfMonth + 1);
    }


    private static int getMonthLength(int m) {
        return MONTH_LENGTHS[m - 1];
    }
}