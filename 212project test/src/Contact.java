
import java.util.Date;

//النسخة النهائية
public class Contact implements Comparable<Contact> {

	public String name, phonenumber, email, address, notes;
	public Date birthday;
	LinkedList<Event> events = new LinkedList<Event>();// عرفته بطريقة ثانية

	public Contact() {
		name = null;
		phonenumber = null;
		email = null;
		address = null;
		notes = null;
		birthday = null;
		
	}

	public Contact(String name, String phonenumber, String email, String address, String notes, String birthady) {
		super();
		this.name = name;
		this.phonenumber = phonenumber;
		this.email = email;
		this.address = address;
		this.notes = notes;
		this.birthday = birthday;
	}

	

	// this method will compare contact attributes if one of them is right it will
	// return 0
	// and other number otherwise
//	public int compareTo(T contact) {
//		if (this.name.equalsIgnoreCase(contact.name) == 0)
//			if (this.phonenumber.compareToIgnoreCase(contact.phonenumber) == 0)
//				if (this.email.compareToIgnoreCase(contact.email) == 0)
//					if (this.address.compareToIgnoreCase(contact.address) == 0)
//						return 0;
//		
//		return -1;
//		
//	}
	public int compareTo(Date date) {
		
		int birthdayComparison = this.birthday.compareTo(date);

			return birthdayComparison;
		
		}
	
	
	//this method will check if the contact does not have events complexity
	//it will return false if he has and true if the event added successfully
	public boolean addEvent( Event event)  
    {  
        if (! events.empty())  
        {  
            events.findFirst();  
            while(events.current != null)   { 
            	//check if contact has events complexity
                if ((events.retrieve().compareTo(event)  == 0) 
                	&& (events.retrieve().compareTo(event.date) == 0))
                    return false;  
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

}