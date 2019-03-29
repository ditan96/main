package seedu.address.model.project;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The date used in the pocket project that allows flexible entries.
 */

public class FlexibleDate {

    private LocalDateTime targetDate;

    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public FlexibleDate() {
        this.targetDate = LocalDateTime.now();
    }

    public String currentDate() {
        return DATE_FORMAT.format(targetDate);
    }

    public String dateNumDaysLater(long numDays) {
        LocalDateTime newDate = targetDate.plusDays(numDays);
        return DATE_FORMAT.format(newDate);
    }

    public String dateNumDaysBefore(long numDays) {
        LocalDateTime newDate = targetDate.minusDays(numDays);
        return DATE_FORMAT.format(newDate);
    }

    public String dateNumWeeksLater(long numWeeks) {
        LocalDateTime newDate = targetDate.plusWeeks(numWeeks);
        return DATE_FORMAT.format(newDate);
    }

    public String dateNumWeeksBefore(long numWeeks) {
        LocalDateTime newDate = targetDate.minusWeeks(numWeeks);
        return DATE_FORMAT.format(newDate);
    }

    public String dateNumMonthsLater(long numMonths) {
        LocalDateTime newDate = targetDate.plusMonths(numMonths);
        return DATE_FORMAT.format(newDate);
    }

    public String dateNumMonthsBefore(long numMonths) {
        LocalDateTime newDate = targetDate.minusMonths(numMonths);
        return DATE_FORMAT.format(newDate);
    }

    public String dateNumYearsLater(long numYears) {
        LocalDateTime newDate = targetDate.plusYears(numYears);
        return DATE_FORMAT.format(newDate);
    }

    public String dateNumYearsBefore(long numYears) {
        LocalDateTime newDate = targetDate.minusYears(numYears);
        return DATE_FORMAT.format(newDate);
    }

}
