package seedu.address.model.task;

import java.time.temporal.ChronoUnit;

public class Recurrence {
    public static final String DAY = "day";
    public static final String WEEK = "week";
    public static final String MONTH = "month";
    public static final String YEAR = "year";

    private final Integer value;
    private final ChronoUnit chronoUnit;

    public Recurrence(Integer value, String timePeriod) {
        this.value = value;

        if (timePeriod.equals(DAY)) {
            this.chronoUnit = ChronoUnit.DAYS;
        } else if (timePeriod.equals(WEEK)) {
            this.chronoUnit = ChronoUnit.WEEKS;
        } else if (timePeriod.equals(MONTH)) {
            this.chronoUnit = ChronoUnit.MONTHS;
        } else if (timePeriod.equals(YEAR)) {
            this.chronoUnit = ChronoUnit.YEARS;
        } else {
            this.chronoUnit = null;
        }
    }

    public Integer getValue() {
        return this.value;
    }

    public ChronoUnit getChronoUnit() {
        return this.chronoUnit;
    }
}
