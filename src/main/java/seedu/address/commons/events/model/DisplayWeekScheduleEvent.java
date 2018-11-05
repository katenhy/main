package seedu.address.commons.events.model;

import java.util.List;

import seedu.address.commons.events.BaseEvent;
import seedu.address.model.timetable.Date;

/**
 * Lists a weekly schedule.
 */
public class DisplayWeekScheduleEvent extends BaseEvent {

    private final List<Date> dates;

    public DisplayWeekScheduleEvent(List<Date> dates) {
        this.dates = dates;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }

    public List<Date> getDisplayedWeekSchedule() {
        return dates;
    }
}
