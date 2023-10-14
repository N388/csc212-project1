

import java.util.Date;


public class Event implements Comparable<Event> {

	String title;
	Date date;
	String time;
	String location;
	LinkedList<Event> Events;
	LinkedList<String> contactsNames = new LinkedList<>();
	
	public Event() {
		super();
		this.title = null;
		this.date = null;
		this.time = null;
		this.location = null;
		this.contactsNames = null;
	}

	public Event(String title, String date, String time, String location, String contact) {
		this.title = title;
		this.date = new Date();
		this.time = time;
		this.location = location;
		contactsNames.insertContact(contact); 

	}

	@Override
	public String toString() {
		String eventData = "\nEvent title: " + title + "\nEvent date and time (MM/DD/YYYY HH:MM): " + date + time
				+ "\nEvent location: " + location + "\n" + "\nContact name: " ;
		contactsNames.findFirst();  
        while(contactsNames.current != null)  
        {  
            eventData += contactsNames.retrieve() + ", ";  
            contactsNames.findNext();  
        }  
         return eventData;
	}

	@Override
	// this method will compare event title and linked list of involved contacts names to the event and return 0 if one
	// of them is right and other number otherwise
	public int compareTo(Event event) {
		int titleComparison = this.title.compareToIgnoreCase(event.title);
		
		String thisContactName = null;
		this.contactsNames.findFirst();  
        while(this.contactsNames.current != null)  
        {  
        	thisContactName += contactsNames.retrieve() + ", ";  
            contactsNames.findNext();  
        }  
        String eventContactName = null;
		event.contactsNames.findFirst();  
        while(event.contactsNames.current != null)  
        {  
        	eventContactName += contactsNames.retrieve() + ", ";  
            contactsNames.findNext();  
        }  
		
		int nameComparison = thisContactName.compareToIgnoreCase(eventContactName);

		if (titleComparison == 0 || nameComparison == 0) {
			return 0;
		} else {
			return titleComparison;
		}
	}
	
	public static void printEventsAlphabetically(LinkedList<Event> events) {
		 events.EventsInAlphabeticalOrder();
	}

}
