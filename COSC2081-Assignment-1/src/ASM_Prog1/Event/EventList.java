/*
Author: Doan Khanh Luan
Student ID: s3927181
Instructor: Dr. Minh Vu
Version: OpenJDK 1.8 (Java 18)
Date: 02/09/2022
Purpose: EventList class contains methods that work with the list of events in the database
 */
package ASM_Prog1.Event;

import java.io.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class EventList {
    private static ArrayList<Event> eventList = new ArrayList<>();
    private static final String delimiter = ",";
    private ArrayList<Event> setEventList() throws IOException, ParseException {
        String filePath = "src/Data/events.csv";
        return EventList.readFile(filePath);
    }
    public EventList() throws IOException, ParseException {
        eventList = setEventList();
    }

    public static ArrayList<Event> getEventList() {
        return eventList;
    }
    public static ArrayList<Event> readFile(String csvFile) throws IOException, ParseException {
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
            Event event = new Event(arr.get(0));
            event.setEventName(arr.get(1));
            event.setPercentageDiscount(Integer.parseInt(arr.get(2)));
            event.setStartDate(arr.get(3));
            event.setEndDate(arr.get(4));
            event.setEventDescription(arr.get(5));
            event.setEventStatus();
            finalArr.add(event);
        }
        bufferedReader.close();
        return finalArr;
    }
    public void displayEventList()
    {
        System.out.println("[EVENT_ID,EVENT_NAME,PERCENTAGE_DISCOUNT,DESCRIPTION,START_DATE,END_DATE,STATUS]");
        for (Event strings : eventList)
        {
            System.out.println(strings);
        }
    }
    public void addNewEvent(Event eventInput)
    {
        eventList.add(eventInput);
        System.out.println("Event add successfully");
    }
    public void removeEvent(String eventID) {
        for (Event event : eventList)
        {
        if (Objects.equals(event.getEventID(), eventID))
        {
            eventList.remove(event);
            System.out.println("Event remove successfully");
            return;
        }
    }
        System.out.println("No event found.");
    }
    public void saveToCSV() throws IOException
    {
        File fileSrc = new File("src/Data/events.csv");
        FileWriter fileWriterSrc = new FileWriter(fileSrc);
        fileWriterSrc.write("EVENT_ID,EVENT_NAME,PERCENTAGE_DISCOUNT,START_DATE,END_DATE,DESCRIPTION,STATUS" + "\n");
        for (Event event : eventList)
        {
            fileWriterSrc.write(event.CSVString() + "\n");
        }
        fileWriterSrc.close();
    }
}
