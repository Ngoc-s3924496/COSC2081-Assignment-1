package ASM_Prog1.Event;

import ASM_Prog1.Order.Order;
import ASM_Prog1.Order.OrderList;
import ASM_Prog1.Product.Product;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class EventList {
    private static ArrayList<Event> eventList = new ArrayList<>();
    private static final String delimiter = ",";
    private ArrayList<Event> setEventList() throws IOException, ParseException {
        String filePath = "src/Data/event.csv";
        return EventList.readFile(filePath);
    }
    public EventList() throws IOException, ParseException {
        this.eventList = setEventList();
    }

    public static ArrayList<Event> getEventList() {
        return eventList;
    }
    private static ArrayList<Event> readFile(String csvFile) throws IOException, ParseException {
        ArrayList<Event> finalArr = new ArrayList<>();
        File file = new File(csvFile);
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;
        List<String> arr;
        bufferedReader.readLine();
        while ((line = bufferedReader.readLine()) != null)
        {
            arr = List.of(line.split(delimiter));
            Event event = new Event();
            event.setEventID(arr.get(0));
            event.setEventName(arr.get(1));
            event.setPercentageDiscount(Integer.parseInt(arr.get(2)));
            event.setStartDate(event.stringToDate(arr.get(3)));
            event.setEndDate(event.stringToDate(arr.get(4)));
            event.setEventDescription(arr.get(5));
            event.setEventStatus(arr.get(6));
            finalArr.add(event);
        }
        bufferedReader.close();
        return finalArr;
    }
    public void displayEventList()
    {
        System.out.println("[EVENTID, EVENTNAME, PERCENTAGEDISCOUNT, STARTDATE, ENDDATE, DESCRIPTION, STATUS]");
        for (Event strings : this.eventList)
        {
            System.out.println(strings);
        }
    }
    public void addNewEvent(Event eventInput)
    {
        this.eventList.add(eventInput);
    }
    public void removeEvent(String eventName) {
        for (Event event : this.eventList)
        {
        if (Objects.equals(event.getEventName(), eventName))
        {
            eventList.remove(event);
            System.out.println("Event remove successfully");
            return;
        }
    }
        System.out.println("No event found.");}
}
