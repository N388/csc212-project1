

import java.util.Date;

public class Event implements Comparable<Event> {

	String title;
	String name;
	Date date;
	String time;
	String location;
	LinkedList<Event> Events;
//	LinkedList<String> contactsNames = new LinkedList<>();

	public Event() {
		super();
		this.title = null;
		this.name = null;
		this.date = null;
		this.time = null;
		this.location = null;
	}

	public Event(String title, String name, String date, String time, String location) {
		this.title = title;
		this.name = name;
		this.date = new Date();
		this.time = time;
		this.location = location;

	}

	@Override
	public String toString() {
		String eventData = "\n Event title: " + title + "\n  contact name : " + name
				+ "\n Event date and time (MM/DD/YYYY HH:MM): " + date + time + "\n Event location: " + location;
		return eventData;
	}

	@Override
	// this method will compare event title and the contact name
	// and time, it will return 0 if one
	// of them is right and other number otherwise
	public int compareTo(Event event) {
		int titleComparison = this.title.compareToIgnoreCase(event.title);
		int nameComparison = this.name.compareToIgnoreCase( event.name);
		int timeComparison = this.name.compareToIgnoreCase( event.time);

		if (titleComparison == 0 || nameComparison == 0 || timeComparison == 0) {
			return 0;
		} else {
			return titleComparison;
		}
	}
	
	//this method will compare event's date it will return 0 if the two dates are the same
	public int compareTo(Date date) {
		
		int eventDateComparison = this.date.compareTo(date);

			return eventDateComparison;
		
		}

	public static void printEventsAlphabetically(LinkedList<Event> events) {
		events.EventsInAlphabeticalOrder();
	}

}
