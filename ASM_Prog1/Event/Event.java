package ASM_Prog1.Event;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;



public class Event
{
    private String eventID;
    private String eventName;
    private int percentageDiscount;
    private String eventDescription;
    private String startDate;
    private String endDate;
    private boolean eventStatus;

    public Event()
    {
    }

    public Event(String eventName, int percentageDiscount, String startDate, String endDate, String eventDescription) throws ParseException {
        this.eventID = "E" + ((int) (Math.random() * 999999) + 1);
        this.eventName = eventName;
        this.percentageDiscount = percentageDiscount;
        this.startDate = startDate;
        this.endDate = endDate;
        this.eventDescription = eventDescription;
        this.eventStatus = this.dayCheck(new Date(), stringToDate(this.endDate));
    }

    public boolean dayCheck(Date today, Date endDate)
    {
        return (today.before(endDate));
    }

    public Date stringToDate(String date) throws ParseException
    {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return simpleDateFormat.parse(date);
    }

    public String CSVString()
    {
        return String.format("%s,%s,%d,%s,%s,%s,%b", getEventID(), getEventName(), getPercentageDiscount(), getStartDate(),
                getEndDate(), getEventDescription(), getEventStatus());
    }

    @Override
    public String toString()
    {
        return "[" +
                eventID + ", " + eventName + ", " + eventDescription + ", " + startDate + ", " + endDate + ", " +
                eventStatus + ']';
    }

    public String getEventName()
    {
        return eventName;
    }

    public void setEventName(String eventName)
    {
        this.eventName = eventName;
    }

    public int getPercentageDiscount()
    {
        return percentageDiscount;
    }

    public void setPercentageDiscount(int percentageDiscount)
    {
        this.percentageDiscount = percentageDiscount;
    }

    public boolean isEventStatus()
    {
        return eventStatus;
    }

    public void setEventStatus(boolean eventStatus)
    {
        this.eventStatus = eventStatus;
    }

    public String getEventID()
    {
        return eventID;
    }

    public void setEventID(String eventID)
    {
        this.eventID = eventID;
    }

    public String getEventDescription()
    {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription)
    {
        this.eventDescription = eventDescription;
    }

    public String getStartDate()
    {
        return startDate;
    }

    public void setStartDate(String startDate)
    {
        this.startDate = startDate;
    }

    public String getEndDate()
    {
        return endDate;
    }

    public void setEndDate(String endDate)
    {
        this.endDate = endDate;
    }

    public boolean getEventStatus()
    {
        return eventStatus;
    }

    public void setEventStatus(String eventStatus) throws ParseException {
        this.eventStatus = this.dayCheck(new Date(), stringToDate(this.endDate));
    }
}
