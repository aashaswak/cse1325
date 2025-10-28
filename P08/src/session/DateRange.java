package session;

/**
 * Represents a span of time on a specific date, mainly used for defining
 * the schedule of a tutoring session or other time-based activities.
 *
 * @author      Aashaswa Raj Khakurel
 * @version     1.0
 * @since       1.0
 * @license.agreement   ARK License 1.0
 */
public class DateRange
{
    private String date;
    private String startTime;
    private String endTime;

    /**
     * Builds a DateRange object using an explicit start and end time on a given date.
     *
     * @param date the calendar date when the session or event takes place
     * @param startTime the time the session begins
     * @param endTime the time the session concludes
     * @throws IllegalArgumentException if any argument is null or blank
     * @since 1.0
     */
    public DateRange(String date, String startTime, String endTime)
    {
        if (date == null || date.isEmpty() || startTime == null || startTime.isEmpty() || endTime == null || endTime.isEmpty())
        {
            throw new IllegalArgumentException("Date, start time, or end time cannot be null or empty");
        }
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Builds a DateRange object using a start time and duration,
     * automatically computing the end time based on the total length.
     *
     * @param date the date on which the scheduled activity occurs
     * @param startTime the time at which the session starts
     * @param duration the total duration of the session in minutes
     * @since 1.0
     */
    public DateRange(String date, String startTime, long duration)
    {
        this.date = date;
        this.startTime = startTime;

        String[] time = startTime.split(":");
        String hr = time[0];
        String mins = time[1];

        int hour = Integer.parseInt(hr);
        int minutes = Integer.parseInt(mins);

        int totalMinutes = (hour * 60 + minutes) + (int) duration;

        int endHour = (totalMinutes / 60) % 24;
        int endMins = (totalMinutes % 60);

        this.endTime = String.format("%02d:%02d", endHour, endMins);
    }

    /**
     * Determines the total duration, in minutes, between the stored
     * start and end times of this DateRange.
     *
     * @return the calculated duration in minutes
     * @since 1.0
     */
    public long duration()
    {
        String[] starttime = startTime.split(":");
        String[] endtime = endTime.split(":");

        int startTimeMin = (Integer.parseInt(starttime[0])) * 60 + (Integer.parseInt(starttime[1]));
        int endTimeMin = (Integer.parseInt(endtime[0])) * 60 + (Integer.parseInt(endtime[1]));

        int diff = endTimeMin - startTimeMin;

        if (diff < 0)
        {
            diff = diff + (24 * 60);
        }
        return diff;
    }

    /**
     * Returns a string description of this DateRange in a readable format.
     *
     * @return a string showing the date, start time, end time, and duration
     * @since 1.0
     */
    @Override
    public String toString()
    {
        return date + " " + startTime + " - " + endTime + " (" + duration() + " minutes)";
    }
}
