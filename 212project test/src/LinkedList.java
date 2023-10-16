

import java.util.Date;
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
	public boolean insertContact(T val) {
		
		Node<T> tmp = null;
		if (empty()) {
			head = current = new Node<T>(val);
			
		}else {
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
//	public boolean searchContact(T contact) {
//		if (empty())      
//			return false;
//		Node<T> cur = head;
//		while (cur != null) {
//			if (((Contact) cur.data).compareTo(contact) == 0) {
////					||((Contact) cur.data).email.compareTo(contact) == 0
////					|| ((Contact) cur.data).address.compareTo(contact) == 0
////					|| ((Contact) cur.data).phonenumber.compareTo(contact) == 0)  {
//						this.current = cur;																	
//				return true;
//			}
//			cur = cur.next;
//		}
//		return false;
//	}
	
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
		Node<T> cur = this.current;
		while (cur != null) {
			if (((Contact) current.data).phonenumber.equalsIgnoreCase(phonenumber)) {
				return true;
			}
			cur = cur.next;
		}
		return false;
	}

	
	//this method will search for a contact using it's birthday
	//true if found, false if not
	public boolean searchContactBirthday(Date date) {
		Node<T> cur = this.current;
		while (cur != null) {

			if (((Contact) current.data).birthday.compareTo(date) == 0) {
				return true;
			}
			cur = cur.next;
		}
		return false;
	}
	
	
	public boolean searchContactEmail(String email) {
		Node<T> cur = this.current;
		while (cur != null) {
			if (((Contact)current.data).email.equalsIgnoreCase(email)) {
				return true;
			}
			cur = cur.next;
	}
			return false;
	}
	
	public boolean searchContactAddress(String address) {
		Node<T> cur = this.current;
		while (cur != null) {
			if (((Contact)current.data).address.equalsIgnoreCase(address)) {
				return true;
			}
			cur = cur.next;
	}
			return false;
	}
	
	
	//this method will return true if the contact was deleted, false if not
	public boolean deletContact(Contact contact) {
		Node<T> pre = head;
		Node<T> cur = head.next;
		if (searchContactName(contact.name)  && (current.data == head.data) ) { // هل استخدام == هنا صحيح؟ 
			//لو نختصر خطوة حذف ايفتات الكونتاكت مع اللي بعده ب١٠ سطور يكون افضل**********************
			//here will check if the contact has any event,if yes,events will
			//be deleted
			if (!contact.events.empty()) {
				contact.events.findFirst();
				while (contact.events.current != null) {
					deletEvent(contact.name);
					contact.events.findNext();
				}
			}
			head = head.next;
			return true;    
		} else {
			while (cur != null) {
				if (searchContactName(contact.name)) {
					//لو نختصر ذي مع اللي فوق**********************
					//here will check if the contact has any event,if yes,events will
					//be deleted
					if (!contact.events.empty()) {
						contact.events.findFirst();
						while (contact.events.current != null) {
							deletEvent(contact.name);
							contact.events.findNext();
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
	
	//this method will search for an event by his title
	//true if found, false if not
	public boolean searchEventTitle(String title) {
		if (empty())
			return false;
		Node<T> cur = head;
		while (cur != null) {
			if (((Event) cur.data).name.equalsIgnoreCase(title)) {
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
			if (((Event) cur.data).name.equalsIgnoreCase(name)) {
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
