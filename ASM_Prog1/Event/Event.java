package ASM_Prog1.Event;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;



public class Event
{
    private String eventID;
    private String eventName;
    private int percentageDiscount;
    private String eventDescription;
    private Date startDate;
    private Date endDate;
    private boolean eventStatus;

    public Event()
    {
    }

    public Event(String eventName, int percentageDiscount, Date startDate, Date endDate, String eventDescription)
    {
        this.eventID = "E" + ((int) (Math.random() * 999999) + 1);
        this.eventName = eventName;
        this.percentageDiscount = percentageDiscount;
        this.startDate = startDate;
        this.endDate = endDate;
        this.eventDescription = eventDescription;
        this.eventStatus = this.dayCheck(new Date(), this.startDate, this.endDate);
    }

    public boolean dayCheck(Date today, Date startDate, Date endDate)
    {
        return (today.after(startDate) && today.before(endDate));
    }

    public Date stringToDate(String date) throws ParseException
    {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return simpleDateFormat.parse(date);
    }

    public String dateToString(Date date) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(date);
    }

    public Event checkEvent(String date) throws IOException, ParseException {
        Date currentDate = stringToDate(date);

        EventCSVReader csvReader = new EventCSVReader("../event.csv", true);
        ArrayList<Event> eventList = new ArrayList<>();
        Event line;
        while ((line = csvReader.read()) != null) {
            eventList.add(line);
        }
        for (Event i : eventList) {
            if (i.dayCheck(currentDate, i.startDate, i.endDate)) {
                return i;
            }
        }
        return null;
    }

    @Override
    public String toString()
    {
        return "Event{" +
                "eventID='" + eventID + '\'' +
                ", eventName='" + eventName + '\'' +
                ", eventDescription='" + eventDescription + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", eventStatus=" + eventStatus +
                '}';
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

    public Date getStartDate()
    {
        return startDate;
    }

    public void setStartDate(Date startDate)
    {
        this.startDate = startDate;
    }

    public Date getEndDate()
    {
        return endDate;
    }

    public void setEndDate(Date endDate)
    {
        this.endDate = endDate;
    }

    public boolean getEventStatus()
    {
        return eventStatus;
    }

    public void setEventStatus(String eventStatus)
    {
        this.eventStatus = this.dayCheck(new Date(),this.startDate, this.endDate);
    }
}
