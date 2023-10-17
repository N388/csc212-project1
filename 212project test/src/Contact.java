

import java.time.LocalDate;


public class Contact implements Comparable<Contact> {

	public String name, phonenumber, email, address, notes;
	public LocalDate birthday;
	//LinkedList<Event> events = new LinkedList<Event>();

	public Contact() {
		name = null;
		phonenumber = "";
		email = null;
		address = null;
		notes = null;
		birthday = null;
		
	}

	public Contact(String name, String phonenumber, String email, String address, String notes, LocalDate birthady) {
		super();
		this.name = name;
		this.phonenumber = phonenumber;
		this.email = email;
		this.address = address;
		this.notes = notes;
		this.birthday = birthday;
	}

	


	public int compareTo(LocalDate date) {
		
		int birthdayComparison = this.birthday.compareTo(date);

			return birthdayComparison;
		
		}
	
	
	//this method will check if the contact does not have events complexity
	//it will return false if he has and true if the event added successfully
	public boolean addEvent(String name, Event event, LinkedList<Event> events)  
    {  
        if (events.empty() == false)  {  
            events.findFirst();  
            while(events.current != null) { 
            	if(events.current.data.name.equalsIgnoreCase(name)) {
            	//check if contact has events complexity
            		if ((events.current.data.date.equals(event.date)) 
            			&& (events.current.data.time.equals(event.time)))
                    return false;  
            	}
            	events.findNext();
            }  
      } 
        events.insertEvent(event);  
        return true;  
        } 
    
	

	@Override
	public String toString() {
		return "Name:" + name + "\nPhonenumber:" + phonenumber + "\nEmail Address:" + email + "\nAddress:" + address
				+ "\nBirthady:" + birthday + "\nNotes:" + notes ;
	}

	public int compareTo(Contact contact) {
	    return this.name.compareTo(contact.name);
	}
	
	

}
