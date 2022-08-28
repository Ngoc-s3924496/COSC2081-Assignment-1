package ASM_Prog1.Event;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public class EventCSVReader
{
    private static final String delimiter = ",";
    private final FileReader fileReader;
    private final BufferedReader bufferedReader;

    public EventCSVReader(String path, boolean headerIncluded) throws IOException
    {
        fileReader = new FileReader(path);
        bufferedReader = new BufferedReader(fileReader);
        if (headerIncluded)
        {
            bufferedReader.readLine();
        }
    }

    public Event read() throws IOException, ParseException
    {

        String line = bufferedReader.readLine();
        if (line == null)
        {
            return null;
        } else
        {
            List<String> arr = List.of(line.split(delimiter));

            Event event = new Event();
            event.setEventID(arr.get(0));
            event.setEventName(arr.get(1));
            event.setPercentageDiscount(Integer.parseInt(arr.get(2)));
            event.setStartDate(event.stringToDate(arr.get(3)));
            event.setEndDate(event.stringToDate(arr.get(4)));
            event.setEventDescription(arr.get(5));
            event.setEventStatus(arr.get(6));
            return event;
        }

    }

    public void close() throws IOException
    {
        fileReader.close();
    }
}
