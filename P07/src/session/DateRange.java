public class DateRange
{
    private final String date;
    private final String startTime;
    private final String endTime;

    public DateRange(String date;String startTime;String endTime)
    {
        this.date=date;
        this.startTime=startTime;
        this.endTime=endTime;
    }
    public DateRange(String date;String startTime;long duration)
    {
        this.date=date;
        this.startTime=startTime;

        String[] time=startTime.split(":");
        String hrs=time[0];
        String mins=time[1];

        int hours=Integer.parseInt(hrs);
        int minutes=Integer.parseInt(mins);

        int timemins=(hours*60+minutes)+(int)duration;

        int endofhr=(timemins/60)%24;
        int endofmins=(timeinmins%60);

        this.endTime=String.format("%02d:%02d",endofhr,endofmins);
    }
}