
import java.time.LocalDate;
import java.time.LocalTime;

public class Event implements Comparable<Event> {
	String type;
	String title;
	// String name;
	String[] contactsNames;
	String names;
	LocalDate date;
	LocalTime time;
	String location;

	public Event() {
		super();
		this.type = null;
		this.title = null;
		this.contactsNames = null;
		this.names = null;
		this.date = null;
		this.time = null;
		this.location = null;
	}

	public Event(String type, String title, String[] contacts,String names, LocalDate date, LocalTime time, String location) {
		this.type = type;
		this.title = title;
		this.contactsNames = contacts;
		this.names = names;
		this.date = date;
		this.time = time;
		this.location = location;

	}

	@Override
	public String toString() {
	String eventData = "\nEvent title: " + title + "\ncontact/s name/s : " +
	names
	+ "\nEvent date and time (MM/DD/YYYY HH:MM): " + date + " " + time + "\nEvent location: " + location;
	return eventData;
	}

	@Override

	// this method will compare event title and the contact name
	// and time, it will return 0 if one
	// of them is right and other number otherwise
	public int compareTo(Event event) {
		int titleComparison = this.title.compareToIgnoreCase(event.title);
		int nameComparison = compareContactNames(this.contactsNames, event.contactsNames);

		if (titleComparison == 0 || nameComparison == 0) {
			return 0;
		} else {
			return titleComparison;
		}
	}

	// this method will compare event's date it will return 0 if the two dates are
	// the same
	public int compareTo(LocalDate date) {

		int eventDateComparison = this.date.compareTo(date);

		return eventDateComparison;

	}

	// this method will compare event's time it will return 0 if the two times are
	// the same
	public int compareTo(LocalTime time) {

		int eventTimeComparison = this.date.compareTo(date);

		return eventTimeComparison;

	}

	public static void printAllContactNames(Event event) {
		for (int i = 0; i < event.contactsNames.length; i++) {
			System.out.println(event.contactsNames[i] + ", ");
		}
	}

	// this method will chick if any contact is on other event list and return 0
	// if true or -1 otherwise.
	public static int compareContactNames(String[] contacts1, String[] contacts2) {
		for (int i = 0; i < contacts1.length; i++) {
			String name1 = contacts1[i];
			for (int j = 0; j < contacts1.length; j++) {
				String name2 = contacts2[j];

				if (name1.compareToIgnoreCase(name2) == 0) {
					return 0;
				}
			}
		}
		return -1;
	}

	// // this method will check if the contact does not have events complexity
	// // it will return false if he has and true if the event added successfully
	// public boolean addEvent(Event event, LinkedList<Event> events) {
	// 	if (events.empty() == false) {
	// 		events.findFirst();
	// 		while (events.current != null) {
	// 			for (int i = 0; i < events.current.data.contactsNames.length; i++) {
	// 				if (compareContactNames(event.contactsNames, events.current.data.contactsNames) == 0) {
	// 					// check if contact has events complexity
	// 					if ((events.current.data.date.equals(event.date))
	// 							&& (events.current.data.time.equals(event.time)))
	// 						return false;
	// 				}
	// 			}
	// 			events.findNext();
	// 		}
	// 	}
	// 	events.insertEventInOrder(event, event.title);
	// 	return true;
	// }

}
