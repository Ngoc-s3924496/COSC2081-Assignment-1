package ASM_Prog1;

import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

public class EventCSVWriter
{
    private final FileWriter fileWriter;

    public EventCSVWriter(String path) throws IOException
    {
        this.fileWriter = new FileWriter(path);
    }

    public void writeHeader() throws IOException
    {
        fileWriter.write("Event_ID, Event_Name, Percentage_Discount, Start_Date, End_Date, Description, Status");
    }

    public void write(Event event) throws IOException, ParseException
    {
        Date start = event.getStartDate();
        Date end = event.getEndDate();
        String startString = event.dateToString(start);
        String endString = event.dateToString(end);
        fileWriter.write(String.format("%s,%s,%d,%s,%s,%s,%s\n", event.getEventID(), event.getEventName(),
                event.getPercentageDiscount(),
                startString, endString,
                event.getEventDescription(), event.getEventStatus()));
    }

    public void write(Iterable<Event> events) throws IOException, ParseException
    {
        for (Event event : events)
        {
            write(event);
        }
    }

    public void close() throws IOException
    {
        fileWriter.close();
    }
}
