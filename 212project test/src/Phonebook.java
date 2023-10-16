
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
				keyboard.nextLine();
				Contact contact = new Contact();
				System.out.print("\nEnter the contact's name: ");
				contact.name = keyboard.nextLine();

				if (contacts.searchContactName(contact.name)) {

					System.out.println("\nContact name already exists!!");
					break;
				} else {
					System.out.print("Enter the contact's phone number:");
					contact.phonenumber = keyboard.nextLine();

					if (contacts.searchContactPhonenumber(contact.phonenumber)) {
						System.out.println("Contact phone number already exists!!");
						break;
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

				switch (input2) {
				case 1: {
					keyboard.nextLine();
					Contact contact = new Contact();
					System.out.print("\nEnter the contact's name: ");
					contact.name = keyboard.nextLine();

					if (contacts.empty() || contacts.searchContactName(contact.name) == false) {
						System.out.println("\nContact not found!");
						break;
					}
					else {
						contacts.findFirst();
						while (contacts.current != null) {
							if (contacts.searchContactName(contact.name)) {

								System.out.println("\nContact found!");
								System.out.println(contacts.retrieve());
								break;
							}
							contacts.findNext();
						}
						
					}
					break;
				}

				case 2: {
					keyboard.nextLine();
					Contact contact = new Contact();
					System.out.print("\nEnter the contact's phone number:");
					contact.phonenumber = keyboard.nextLine();

					if (contacts.empty() || contacts.searchContactPhonenumber(contact.phonenumber) == false) {
						System.out.println("\nContact not found!");
						break;
					} else {
						contacts.findFirst();
						while (contacts.current != null) {
							if (contacts.searchContactPhonenumber(contact.phonenumber)) {
								System.out.println("\nContact found!");
								System.out.println(contacts.retrieve());

								break;
							}
							contacts.findNext();
						}
					}
					break;

				}
				case 3: {
					keyboard.nextLine();
					Contact contact = new Contact();
					System.out.print("\nEnter the contact's email address:");
					contact.email = keyboard.nextLine();

					if (contacts.empty() || contacts.searchContactEmail(contact.email) == false) {
						System.out.println("\nContact not found!");
						break;
					}
					else {
						contacts.findFirst();
						while (contacts.current != null) {
							if (contacts.searchContactEmail(contact.email)) {
								System.out.println("\nContact found!");
								System.out.println(contacts.retrieve());
								// deleted break because emails are not unique
							}
							contacts.findNext();
						
						}

					}
					break;
				}
				case 4: {
					keyboard.nextLine();
					Contact contact = new Contact();
					System.out.print("\nEnter the contact's address:");
					contact.address = keyboard.nextLine();

					if (contacts.empty() || contacts.searchContactAddress(contact.address) == false) {
						System.out.println("\nContact not found!");
						break;
					}
					else {
						contacts.findFirst();
						while (contacts.current != null) {

							if (contacts.searchContactAddress(contact.address)) {
								System.out.println("\nContact found!");
								System.out.println(contacts.retrieve());

							}
							contacts.findNext();
						}
	
					}
					break;
				}
				// to search for a contact by it's birthday
				case 5: {
					keyboard.nextLine();
					String birthday = new String();
					System.out.print("\nEnter the contact's birthday(MM/dd/yyyy):");
					birthday = keyboard.nextLine();
					SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");// to get from String to Date
					Date date = null;
					try {
						date = dateFormat.parse(birthday);
					} catch (ParseException e) {
						System.out.println("\nInvalid date format. Please use MM/dd/yyyy.");
					}
					if (contacts.empty() || contacts.searchContactBirthday(date) == false) {
						System.out.println("\nContact not found!");
						break;
					}
					else {
						contacts.findFirst();
						while (contacts.current != null) {

							if (contacts.searchContactBirthday(date)) {
								System.out.println("\nContact found!");
								System.out.println(contacts.retrieve());
								// deleted break because birthdays are not unique
							}
							contacts.findNext();
						}

					}
					break;
				}
				}
				break;
			}

			// Delete a contact and his events
			case 3: {
				keyboard.nextLine();
				Contact contact = new Contact();

				System.out.print("\nEnter the contact's name: ");
				contact.name = keyboard.nextLine();

				if (contacts.empty())
					System.out.println("\nContact not found!");
				else {
					if (contacts.deletContact(contact)) {
						System.out.println("\nContact Deleted Successfully!");
						break;
					} else
						System.out.println("\nContact not found!");
				}
				break;
			}

			// Schedule an event
			case 4: {
				keyboard.nextLine();
				Event event = new Event();
				Contact contact = new Contact();
				System.out.print("\nEnter event title:");
				event.title = keyboard.nextLine();

				System.out.print("\nEnter contact name:");
				contact.name = keyboard.nextLine();

				// check if the contact exists or not
				if (contacts.searchContactName(contact.name)) {
					System.out.print("\nEnter event date and time (MM/DD/YYYY HH:MM): ");
					event.date = new Date(keyboard.next());
//					keyboard.nextLine();

					System.out.print("\nEnter event location:");
					event.location = keyboard.nextLine();

					contact = contacts.retrieve();

					// if the contact has event complexity event would not be added
					if (contact.addEvent(event) && contact.events.insertEvent(event)) {

						System.out.println("\n Event scheduled successfully!");
						// here event will be added to contact's own event list

					} else
						System.out.println("\n conflicted Events with the contact");
				} else
					System.out.println("\n Cantcat does not exists!");

				break;
			}

			// Print event details
			case 5: {
				keyboard.nextLine();
				System.out.println("Enter search criteria:");
				System.out.println("1. contact name");
				System.out.println("2. Event title");
				System.out.print("\nEnter your choice:");
				int input3 = keyboard.nextInt();
				switch (input3) {
				case 1: {
					keyboard.nextLine();
					Contact contact = new Contact();
					System.out.print("\nEnter contact name:");
					contact.name = keyboard.nextLine();

					if (contacts.searchContactName(contact.name)) {
						System.out.println("\nContact found!");
						contact = contacts.retrieve();

						contact.events.findFirst();

						while (contact.events.current != null) {
							System.out.println(contact.events.retrieve());
							contact.events.findNext();
						}
						if (contact.events.empty())
							System.out.println("\nthis contact has no events!");
					} else
						System.out.println("\nCantcat does not exists!");

					break;
				}

				case 2: {
					keyboard.nextLine();
					System.out.print("\nEnter the event title:");
					String title = keyboard.nextLine();

					if (!events.empty() && events.searchEventTitle(title)) {
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
				keyboard.nextLine();
				System.out.print("\nEnter the first name:");
				String firstName = keyboard.nextLine();

				if (contacts.empty()) {
					System.out.println("\nNo Contacts have the first name!");
					break; // Exit the case if the list is empty
				}

				contacts.findFirst();

				while (contacts.current != null) {
					String fullName = contacts.retrieve().name;
					String[] allNames = fullName.split(" ");

					if (allNames.length > 0 && allNames[0].equalsIgnoreCase(firstName)) {
						System.out.println(contacts.retrieve() + "\n");
					}

					contacts.findNext();
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

			case 8:
				break;
			default:
				System.out.println("\nenter a number between 1 and 8");

			}

		} while (input1 != 8);

		System.out.println("\nGoodbye!");
	}
} 