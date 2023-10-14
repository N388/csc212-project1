//النسخة النهائية
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.text.ParseException;


public class Phonebook {
	public static void main(String[] args) {

		Scanner keyboard = new Scanner(System.in);
		LinkedList<Contact> contacts = new LinkedList<Contact>();
		LinkedList<Event> events = new LinkedList<Event>();
		int input1 = 0;

		System.out.println("Welcome to the Linked List Phonebook!");

		do {
			System.out.println("\nPlease choose an option:");
			System.out.println("1.Add a contact");
			System.out.println("2.Search for a contact");
			System.out.println("3.Delete a contact");
			System.out.println("4.Schedule an event");
			System.out.println("5.Print event details");
			System.out.println("6.Print contacts by first name");
			System.out.println("7.Print all events alphabetically");
			System.out.println("8.Exit");

			System.out.print("\nEnter your choice: ");

			input1 = keyboard.nextInt();
			switch (input1) {

			// Add a contact
			case 1: {
				Contact contact = new Contact();
				System.out.print("\nEnter the contact's name: ");
				contact.name = keyboard.nextLine();
				keyboard.nextLine();

				if (!contacts.empty() && contacts.searchContact(contact.name)) {
					System.out.println("\nContact name already exists!!1");
					return;
				} else {
					System.out.print("Enter the contact's phone number:");
					contact.phonenumber = keyboard.nextLine();

					if (!contacts.searchContact(contact.phonenumber)) {
						System.out.println("Contact phone number already exists!!2");
						return;
					} else {
						System.out.print("Enter the contact's email address: ");
						contact.email = keyboard.nextLine();

						System.out.print("Enter the contact's address:");
						contact.address = keyboard.nextLine();

						System.out.print("Enter the contact's birthday (MM/dd/yyyy):");
						contact.birthday = new Date(keyboard.nextLine());

						System.out.print("Enter any notes for the contact:");
						contact.notes = keyboard.nextLine();

						if (contacts.insertContact(contact))
							System.out.println("\nContact added successfully!");
					}
				}
				break;
			}

			// Search for a contact
			case 2: {
				System.out.println("Enter search criteria:");
				System.out.println("1. Name");
				System.out.println("2. Phone Number");
				System.out.println("3. Email Address");
				System.out.println("4. Address");
				System.out.println("5. Birthday");

				System.out.print("\nEnter your choice: ");
				int input2 = keyboard.nextInt();
				keyboard.nextLine();

				switch (input2) {
				case 1: {
					Contact contact = new Contact();
					System.out.print("\nEnter the contact's name: ");
					contact.name = keyboard.nextLine();

					if (contacts.empty())
						System.out.println("\nContact not found!");
					else {
						contacts.findFirst();
						while (contacts.current != null ) {
							if(contacts.searchContact(contact.name) == true) {
							
								System.out.println("\nContact found!");
								System.out.println(contacts.retrieve());
								break; //****** add break
							}
								contacts.findNext();// بتطبع زين والا لا؟؟
						}
							 if(contacts.searchContact(contact.name) == false ) {
								 System.out.println("\nContact does not exists!!");
								 break; //***** add break
							}
					}
					break;
				}

				case 2: {
					Contact contact = new Contact();
					System.out.print("\nEnter the contact's phone number:");
					contact.phonenumber = keyboard.nextLine();

					if (contacts.empty())
						System.out.println("\nContact not found!");
					else {
						contacts.findFirst();
						while (contacts.current != null) {
								
							if (!contacts.searchContact(contact.phonenumber))/*add !*/  {
								System.out.println("\nContact found!");
								System.out.println(contacts.retrieve());
								
								break; //***** add break
							} ///***** deleted else
								contacts.findNext();
							}
						
					}
					if(contacts.current==null) //add if
						System.out.println("\nContact does not exists!!");
					break;
				}
				case 3: {
					Contact contact = new Contact();
					System.out.print("\nEnter the contact's email address:");
					contact.email = keyboard.nextLine();

					if (contacts.empty())
						System.out.println("\nContact not found!");
					else {
						contacts.findFirst();
						while (contacts.current != null) {
								
							if (!contacts.searchContact(contact.email))/*add !*/  {
								System.out.println("\nContact found!");
								System.out.println(contacts.retrieve());
								
								break; //***** add break
							} ///***** deleted else
								contacts.findNext();
							}
						
					}
					if(contacts.current==null) //add if
						System.out.println("\nContact does not exists!!");
					break;
				}
				case 4: {
					Contact contact = new Contact();
					System.out.print("\nEnter the contact's address:");
					contact.address = keyboard.nextLine();

					if (contacts.empty())
						System.out.println("\nContact not found!");
					else {
						contacts.findFirst();
						while (contacts.current != null) {
								
							if (!contacts.searchContact(contact.address))/*add !*/  {
								System.out.println("\nContact found!");
								System.out.println(contacts.retrieve());
								
								break; //***** add break
							} ///***** deleted else
								contacts.findNext();
							}
						
					}
					if(contacts.current==null) //add if
						System.out.println("\nContact does not exists!!");
					break;
				}
				//to search for a contact by it's birthday
				case 5: {
					String birthday = new String();
					System.out.print("\nEnter the contact's birthday(MM/dd/yyyy):");
					birthday = keyboard.nextLine();
					SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");//to get from String to Date
					Date date = null;
					try {
						date = dateFormat.parse(birthday);
					} catch (ParseException e) {
						System.out.println("\nInvalid date format. Please use MM/dd/yyyy.");
					}
					if (contacts.empty())
						System.out.println("\nContact not found!");
					else {
						contacts.findFirst();
						while (contacts.current != null) {
								
							if (!contacts.searchContactBirthday(date))/*add !*/  {
								System.out.println("\nContact found!");
								System.out.println(contacts.retrieve());
								
								break; //***** add break
							} ///***** deleted else
								contacts.findNext();
							}
						
					}
					if(contacts.current==null) //add if
						System.out.println("\nContact does not exists!!");
					break;
				}

				}

				break;
			}

			// Delete a contact and his name from involved events
			case 3: {
				Contact contact = new Contact();

				System.out.print("\nEnter the contact's name: ");
				contact.name = keyboard.nextLine();

				if (contacts.empty())
					System.out.println("\nContact not found!");
				else {
					contact = contacts.deletContact(contact);
					if (contact == null)
						System.out.println("\nContact not found!");
					else {
						if (!contact.events.empty()) {
							contact.events.findFirst();
							while (contact.events.current != null) {
								Event tempEvent = contact.events.retrieve();
								if (!events.serchEvent(tempEvent)) {
									Event Update_Event = events.retrieve();
									Update_Event.contactsNames.deletEventContactsName(contact.name);
									if (Update_Event.contactsNames.empty()) {
										events.deletEvent(tempEvent);
										System.out.println("\nDelete event, No cantact particapate");
									} else
										events.update(Update_Event);

								}
								contact.events.findNext();
							}

						}
						System.out.println("\nContact Deleted Successfully!");
						System.out.println(contact);
					}
				}
				break;
			}

			// Schedule an event
			case 4: {
				Event event = new Event();

				System.out.print("\nEnter event title:");
				event.title = keyboard.nextLine();

				Contact contact = new Contact();
				contact.name = keyboard.nextLine(); //*** add scanner
				System.out.print("\nEnter contact name:");
				contact.name = keyboard.nextLine();
				

				boolean eventInitialised = false;
				boolean eventContactsNames = false;

				if (!contacts.empty() && contacts.searchContact(contact.name) == false) {
					System.out.print("\nEnter event date and time (MM/DD/YYYY HH:MM): ");
					event.date = new Date(keyboard.next());
					keyboard.nextLine();

					System.out.print("\nEnter event location:");
					event.location = keyboard.nextLine();

					contact = contacts.retrieve();
					eventContactsNames = contact.addEvent(event);
					if (eventContactsNames) {

						contacts.update(contact);
						if (!events.empty() && events.serchEvent(event)) {
							Event foundEvent = events.retrieve();
							if ((foundEvent.date.compareTo(event.date) == 0)
									&& (foundEvent.time.compareTo(event.time) == 0)
									&& (foundEvent.location.compareTo(event.location) == 0)) {
								foundEvent.contactsNames.insertContact(contact.name);
								events.update(foundEvent);
								eventInitialised = true;
							}
						}
						if (!eventInitialised) {

							event.contactsNames.insertContact(contact.name);
							events.insertEvent(event);
						}
						System.out.println("\nEvent scheduled successfully!");
					} else
						System.out.println("\nconflicted Events with the contact");
				} else
					System.out.println("\nCantcat does not exists!");

				break;
			}

			// Print event details
			case 5: {
				System.out.println("Enter search criteria:");
				System.out.println("1. contact name");
				System.out.println("2. Event tittle");
				System.out.print("\nEnter your choice:");
				int input3 = keyboard.nextInt();
				switch (input3) {
				case 1: {

					Contact contact = new Contact();
					System.out.print("\nEnter contact name:");
					contact.name = keyboard.nextLine();

					if (!contacts.empty()) {
						if (contacts.searchContact(contact.name) == false) {
							System.out.println("\nContact found!");
							contact = contacts.retrieve();

							contact.events.findFirst();

							while (contact.events.current != null) {
								Event event = contact.events.retrieve();
								if (!events.empty() && events.serchEvent(event))
									System.out.println(events.retrieve());
								contact.events.findNext();
							}
							if (contact.events.empty())
								System.out.println("\nthis contact has no events!");
						} else
							System.out.println("\nCantcat does not exists!");
					} else
						System.out.println("\nCantcat does not exists!");
					break;
				}

				case 2: {
					Event event = new Event();
					System.out.print("\nEnter the event title:");
					event.title = keyboard.nextLine();

					if (!events.empty() && events.serchEvent(event)) {
						System.out.println("Event found!");
						System.out.println(events.retrieve());
					} else
						System.out.println("\nEvent does not exists!");
					break;
				}

				}
				break;
			}

			// Print contacts by first name
			case 6: {
				System.out.print("\nEnter the first name:");
				String firstName = keyboard.nextLine();

				if (contacts.empty())
					System.out.println("\nNo Contacts have the first name!");

				contacts.findFirst();
				int i = 0;
				while (contacts.current != null) {
					String name = contacts.retrieve().name;
					String[] allFirstName = name.split(" ");

					if (allFirstName[i].compareToIgnoreCase(firstName) == 0) {
						System.out.println(contacts.retrieve() + "\n");
					}
					contacts.findNext();
					i++;
				}
				break;
			}

			// Print all events alphabetically
			case 7: {
				int i = 0;
				if (!events.empty()) {
					events.findFirst();
					while (events.current != null) {
						System.out.println("\n" + (i + 1) + ". Event : " + events.retrieve().title);
						events.findNext();
						i++;
					}
				} else
					System.out.println("\nevents list is empty!");

				break;
			}
			default:
				System.out.println("\nenter a number between 1 and 8");

			}

		} while (input1 != 8);

		System.out.println("\nGoodbye!");
	}
} 