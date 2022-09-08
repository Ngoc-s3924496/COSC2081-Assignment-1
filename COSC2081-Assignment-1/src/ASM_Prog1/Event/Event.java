package ASM_Prog1.Event;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;



public class Event
{
    private final String eventID;
    private String eventName;
    private int percentageDiscount;
    private String eventDescription;
    private String startDate;
    private String endDate;
    private boolean eventStatus;

    public Event(String eventID)
    {
        this.eventID = eventID;
    }

    public Event(String eventName, int percentageDiscount, String startDate, String endDate,
                 String eventDescription) throws ParseException {
        String ID = "E" + ((int) (Math.random() * 999999) + 1);
        while (IDCheck(ID)){
            ID = "E" + ((int) (Math.random() * 999999) + 1);
        }
        this.eventID = ID;
        this.eventName = eventName;
        this.percentageDiscount = percentageDiscount;
        this.startDate = startDate;
        this.endDate = endDate;
        this.eventDescription = eventDescription;
        this.eventStatus = (this.dayCheck(new Date(), stringToDate(this.startDate), stringToDate(this.endDate)));
    }

    public boolean dayCheck(Date today, Date startDate, Date endDate)
    {
        return (today.before(endDate) && today.after(startDate));
    }
    public boolean IDCheck(String ID) {
        for (Event event : EventList.getEventList()) {
            if (ID.equals(event.getEventID())) {
                return true;
            }
        }
        return false;
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
                eventID + ", " + eventName + ", " + percentageDiscount + ", " + eventDescription + ", " + startDate + ", " +
                endDate + ", " + eventStatus + ']';
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

    public String getEventID()
    {
        return eventID;
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

    public void setEventStatus() throws ParseException {
        this.eventStatus = (this.dayCheck(new Date(), stringToDate(this.startDate), stringToDate(this.endDate)));
    }
}
