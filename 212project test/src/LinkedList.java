
import java.time.LocalDate;

public class LinkedList<T extends Comparable<T>> {

	public Node<T> head = new Node<T>();
	public Node<T> current = new Node<T>();
	 //LinkedList<Event> events = new LinkedList<Event>();
	

	public LinkedList() {
		head = current = null;
	}

	public boolean empty() {
		return head == null;
	}

	public boolean last() {
		return current.next == null;
	}

	public boolean full() {
		return false;
	}

	public void findFirst() {
		current = head;
	}

	public void findNext() {
		current = current.next;
	}
	

	public T retrieve() {
		return current.data;
	}

	public void update(T val) {
		current.data = val;
	}
	
	
	// this method will insert contacts in contacts list according
	// to their alphabetical order
	public boolean insertContact(T contact) {
	    Node<T> newNode = new Node<>(contact);

	    if (empty() || head.data.compareTo(contact) > 0) {
	        newNode.next = head;
	        head = newNode;
	        current = newNode;
	        return true;
	    }

	    Node<T> prev = null;
	    Node<T> current = head;

	    while (current != null && current.data.compareTo(contact) <= 0) {
	        prev = current;
	        current = current.next;
	    }

	    prev.next = newNode;
	    newNode.next = current;
	    current = newNode;

	    return true;
	}
	

	
	public boolean searchContactName(String name) {
		if (empty())
			return false;
		Node<T> cur = head;
		while (cur != null) {
			
			if (cur.data != null && ((Contact)cur.data).name != null 
					&& ((Contact)cur.data).name.equalsIgnoreCase(name)) {
				this.current=cur;							
				return true;
			}
			cur = cur.next;
		}
		return false;
	}
	
	public boolean searchContactPhonenumber(String phonenumber) {
		Node<T> cur = head;
		while (cur != null) {
			if (((Contact) cur.data).phonenumber.equalsIgnoreCase(phonenumber)) {
				return true;
			}
			cur = cur.next;
		}
		return false;
	}

	
	//this method will search for a contact using it's birthday
	//true if found, false if not
	public boolean searchContactBirthday(LocalDate date) {
		Node<T> cur = head;
		while (cur != null) {

			if (((Contact) cur.data).birthday.compareTo(date) == 0) {
				return true;
			}
			cur = cur.next;
		}
		return false;
	}
	
	
	public boolean searchContactEmail(String email) {
		Node<T> cur = head;
		while (cur != null) {
			if (((Contact)cur.data).email.equalsIgnoreCase(email)) {
				return true;
			}
			cur = cur.next;
	}
			return false;
	}
	
	public boolean searchContactAddress(String address) {
		Node<T> cur = head;
		while (cur != null) {
			if (((Contact)cur.data).address.equalsIgnoreCase(address)) {
				return true;
			}
			cur = cur.next;
	}
			return false;
	}
	
	
	//this method will return true if the contact was deleted, false if not
	public boolean deletContact(Contact contact, LinkedList<Event> events) {
		Node<T> pre = head;
		Node<T> cur = head.next;
		if (searchContactName(contact.name)  && (current.data == head.data) ) {  
			
			//here will check if the contact has any event,if yes,events will
			//be deleted
			if (searchEventName(contact.name)) {
				events.findFirst();
				while (events.current != null) {
					deletEvent(contact.name);
					events.findNext();
				}
			}
			head = head.next;
			return true;    
		} else {
			while (cur != null) {
				if (searchContactName(contact.name)) {
					
					//here will check if the contact has any event,if yes,events will
					//be deleted
					if (searchEventName(contact.name)) {
						events.findFirst();
						while (events.current != null) {
							deletEvent(contact.name);
							events.findNext();
						}
					}
					pre.next = cur.next;
					return true; 
				}
				pre = cur;
				cur = cur.next;
			}
			return false;
		}
	}
	
	// insert Event in events list
	public boolean insertEventInOrder(T event) {
	    
	    Node<T> tmp = new Node<>(event);
	    
	    
	    if (head == null || ((Event)tmp.data).title.compareTo(((Event)head.data).title) <= 0) {
	        tmp.next = head;
	        head = tmp;
	        return true;
	    } else {
	       
	        Node<T> current = head;
	        while (current.next != null && ((Event)tmp.data).title.compareTo(((Event)current.next.data).title) > 0) {
	            current = current.next;
	        }
	        
	        tmp.next = current.next;
	        current.next = tmp;
	        return true;
	    }
	}

	
	//this method will search for an event by his title
	//true if found, false if not
	public boolean searchEventTitle(String title) {
		if (empty())
			return false;
		Node<T> cur = head;
		while (cur != null) {
			if (((Event) cur.data).title.equalsIgnoreCase(title)) {
				this.current = cur;
				return true;
			}
			cur = cur.next;
		}
		return false;
	}
	
	//this method will search for an event by it's contact Name
	//true if found, false if not
	public boolean searchEventName(String name) {
		if (empty())
			return false;
		Node<T> cur = head;
		while (cur != null) {
			if (((Contact) cur.data).name.equalsIgnoreCase(name)) {
				this.current = cur;
				return true;
			}
			cur = cur.next;
		}
		return false;
	}
	
	
	//this method to print all events in alphabetical order (should be called from class Event??)
	public void printEvents() {
	    
	    if (head == null) {
	        System.out.println("The list is empty.");
	        return;
	    }
	    
	    Node<T> current = head;
	    
	    while (current != null) {
	       
	        System.out.println(((Event) current.data).title);
	        
	        current = current.next;
	    }
	}
	 
	 //this method will delete an event by it's contact name only since the
	 //only way to delete an event is by deleting it's contact from contacts list.
	 //it will return true if the event deleted, false if not.
	 public boolean deletEvent(String name) {
			Node<T> pre = head;
			Node<T> cur = head.next;
			if (searchEventName(name) && current.data == head.data) {
				head = head.next;
				return true;    
			} else {
				while (cur != null) {
					if (searchEventName(name) && current.data == cur.data) {
						pre.next = cur.next;
						return true; 
					}
					pre = cur;
					cur = cur.next;
				}
				return false;
			}
		}
	

}
