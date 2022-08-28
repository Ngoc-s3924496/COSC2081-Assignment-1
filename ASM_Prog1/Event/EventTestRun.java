package ASM_Prog1.Event;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.ArrayList;

public class EventTestRun
{

    public static void main(String[] args) throws IOException, URISyntaxException, ParseException
    {
        // The format date in the start/end dates when read in is different with the date when write to CSV because
        // some format that I cannot fix ~~
        // But I make it doing correct so do not worry
        // The last parameter of event is auto defined. If it is in range of start/end date, it will be true and vice
        // versa.
        // Read an event from CSV File and parse it into ArrayList

        EventCSVReader eventCSVReader = new EventCSVReader("src/event.txt", false);
        Event readEvent = eventCSVReader.read();

        ArrayList<Event> eventArrayList = new ArrayList<>();
        while (readEvent != null)
        {
            eventArrayList.add(readEvent);
            readEvent = eventCSVReader.read();
        }

        // Remove an event from that ArrayList

        String eventID = "E411033"; // Make this an input from user

        for (int i = 0; i < eventArrayList.size(); i++)
        {
            if (eventID.equals(eventArrayList.get(i).getEventID()))
            {
                eventArrayList.remove(eventArrayList.get(i));
            }
        }

        // Add an event to that ArrayList

        Event event = new Event();
        String startDate = "1/1/2022";
        String endDate = "31/12/2022";
        Event testEvent = new Event("Testing", 69, event.stringToDate(startDate), event.stringToDate(endDate), "Sale " +
                "69%");

        eventArrayList.add(testEvent);

        // Write that event Array List to CSV File

        Event writeEvent = new Event();
        EventCSVWriter eventCSVWriter = new EventCSVWriter("src/event.txt");

        eventCSVWriter.write(eventArrayList);
        eventCSVWriter.close();

        // Print out an example event

        System.out.println(eventArrayList);


    }
}
