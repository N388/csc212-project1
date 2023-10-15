

import java.util.Date;
public class LinkedList<T extends Comparable<T>> {

	public Node<T> head;
	public Node<T> current;
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
	
	
	// change insert to insert contact*************************
	public boolean insertContact(T val) {
		Node<T> tmp = null;
		if (empty())
			head = current = new Node<T>(val);
		else {
			if (head.data.compareTo(val) > 0) {
				tmp = new Node<T>(val);
				tmp.next = head;
				head = tmp;
			} else {
				Node<T> pre = head;
				current = head.next;
				tmp = new Node<T>(val);
				while (current != null) {
					if (current.data.compareTo(val) <= 0) {
						pre = current;
						current = current.next;
					} else {
						pre.next = tmp;
						tmp.next = current;
						current = tmp;
					}

				}
				if (current == null)
					current = pre.next = tmp;
			}
		}
		return true;
	}
	
	
	//this method will search for a contact by 4 of his attributes
	//true if found, false if not
	public boolean searchContact(String contact) {
		if (empty())      
			return false;
		Node<T> cur = head;
		while (cur != null) {
			if (((Contact) cur.data).name.compareTo(contact) == 0
					||((Contact) cur.data).email.compareTo(contact) == 0
					|| ((Contact) cur.data).address.compareTo(contact) == 0
					|| ((Contact) cur.data).phonenumber.compareTo(contact) == 0)  {
						this.current = cur;																	
				return true;
			}
			cur = cur.next;
		}
		return false;
	}
	
	//this method will search for a contact using it's birthday
	//true if found, false if not
	public boolean searchContactBirthday(Date date) {
		if (empty())
			return false;
		Node<T> cur = head;
		while (cur != null) {
			if (((Contact) cur.data).birthday.compareTo(date) == 0) {
											this.current=cur; //****add this							
				return true;
			}
			cur = cur.next;
		}
		return false;
	}
	
	
	
	public Contact deletContact(Contact contact) {
		Node<T> pre = head;
		Node<T> cur = head.next;
		if (searchContact(contact.name) == true)
			return null;
		else if (searchContact(contact.name) == false && current.data == head.data) {
			head = head.next;
			return contact;    
		} else {
			while (cur != null) {
				if (searchContact(contact.name) == false && current.data == cur.data) {
					pre.next = cur.next;
					return contact; 
				}
				pre = cur;
				cur = cur.next;
			}
			return null;
		}
	}
	
	// insert Events at the beginning*****************
	public boolean insertEvent(T val) {
	    Node<T> tmp = new Node<>(val);

	    if (empty()) {
	        current = head = tmp;
	    } else {
	        tmp.setNext(head);
	        head = tmp;
	    }

	    return true;
	}
	
	//this method will search for an event by his title of contactsName
	//true if found, false if not
	public boolean serchEvent(Event event) {
		if (empty())
			return false;
		Node<T> cur = head;
		while (cur != null) {
			if (((Event) cur.data).name.compareTo(event.name) == 0
					|| ((Event) cur.data).title.compareTo(event.title) == 0) {
				this.current = cur;
				return true;
			}
				
			cur = cur.next;
		}
		return false;
	}
	
	
	//this method to print all events in alphabetical order (should be called from class Event??)
	 public void EventsInAlphabeticalOrder() {
	        // Bubble sort to sort events alphabetically
	        boolean swapped;
	        Node<T> temp;
	        

	        if (head == null) {
	            return;
	        }

	        do {
	            swapped = false;
	            Node<T> current = head;

	            while (current.next != null) {
	                if (((Event)current.data).title.compareToIgnoreCase(((Event)current.next.data).title) > 0) {
	                    temp = current;
	                    current = current.next;
	                    temp.setNext(current.next);
	                    current.setNext(temp);
	                    if (current == head) {
	                        head = temp = current;
	                    } else {
	                        temp = temp.next;
	                    }
	                    swapped = true;
	                } else {
	                    current = current.next;
	                }
	            }
	        } while (swapped);

	        // Print the sorted events
	        current = head;
	        while (current != null) {
	            System.out.println(((Event)current.data).title);
	            current = current.next;
	        }
	    }
	 
	 public Event deletEvent(Event event) {
			Node<T> pre = head;
			Node<T> cur = head.next;
			if (searchContact(event.title) == true)
				return null;
			else if (searchContact(event.title) == false && current.data == head.data) {
				head = head.next;
				return event;    
			} else {
				while (cur != null) {
					if (searchContact(event.title) == false && current.data == cur.data) {
						pre.next = cur.next;
						return event; 
					}
					pre = cur;
					cur = cur.next;
				}
				return null;
			}
		}
	



	public int compareTo(LinkedList<String> contactsNames) {
		// TODO Auto-generated method stub
		return 0;
	}

	
}
