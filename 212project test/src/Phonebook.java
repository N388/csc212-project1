
import java.util.InputMismatchException;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Phonebook {
	public static void main(String[] args) {

		Scanner keyboard = new Scanner(System.in);
		ContactBST<Contact> contactsbBST = new ContactBST<Contact>();
		// LinkedList<Contact> contactsbBST = new LinkedList<Contact>();
		LinkedList<Event> events = new LinkedList<Event>();
		int input1 = 0;

		System.out.println("Welcome to the BST Phonebook!");
		do {
			System.out.println("\nPlease choose an option:");
			System.out.println("1.Add a contact");
			System.out.println("2.Search for a contact");
			System.out.println("3.Delete a contact");
			System.out.println("4.Schedule an event/appointment");
			System.out.println("5.Print event details");
			System.out.println("6.Print contactsbBST by first name");
			System.out.println("7.Print all events alphabetically");
			System.out.println("8.Exit");

			boolean trueInput1 = false;
			while (!trueInput1) {
				try {
					System.out.print("\nEnter your choice: ");
					input1 = keyboard.nextInt();
					trueInput1 = true;
				} catch (InputMismatchException e) {
					System.out.println("Invalid input. Please enter a valid integer.");
					keyboard.next(); // Consume the invalid input
				}
			}

			switch (input1) {

				// Add a contact
				case 1: {
					keyboard.nextLine();
					Contact contact = new Contact();

					System.out.print("\nEnter the contact's name: ");
					contact.name = keyboard.nextLine();

					if (!contactsbBST.empty() && contactsbBST.search_nameContact(contact.name)) {

						System.out.println("\nContact name already exists!!");
						break;
					} else {
						System.out.print("Enter the contact's phone number:");
						contact.phonenumber = keyboard.nextLine();

						if (contactsbBST.search_phoneContact(contact.phonenumber)) {
							System.out.println("Contact phone number already exists!!");
							break;
						} else {

							System.out.print("Enter the contact's email address: ");
							contact.email = keyboard.nextLine();

							System.out.print("Enter the contact's address:");
							contact.address = keyboard.nextLine();

							// this will check if the date is in right format.
							boolean trueDate = false;
							while (trueDate == false) {
								System.out.print("Enter the contact's birthday (MM/dd/yyyy):");
								String birthday = keyboard.nextLine();

								try {
									contact.birthday = LocalDate.parse(birthday,
											DateTimeFormatter.ofPattern("MM/dd/yyyy"));
									trueDate = true;
								} catch (DateTimeParseException e) {
									System.out.println("Invalid date format. Please use MM/dd/yyyy.");
								}

							}

							System.out.print("Enter any notes for the contact:");
							contact.notes = keyboard.nextLine();

							if (contactsbBST.insertContact(contact.name, contact))
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

					int input2 = 0;
					boolean trueInput2 = false;
					while (!trueInput2) {
						try {
							System.out.print("\nEnter your choice: ");
							input2 = keyboard.nextInt();
							trueInput2 = true;
						} catch (InputMismatchException e) {
							System.out.println("Invalid input. Please enter a valid integer.");
							keyboard.next(); // Consume the invalid input
						}
					}

					switch (input2) {
						case 1: {
							keyboard.nextLine();
							Contact contact = new Contact();
							System.out.print("\nEnter the contact's name: ");
							contact.name = keyboard.nextLine();

							if (contactsbBST.empty() || !contactsbBST.search_nameContact(contact.name)) {
								System.out.println("\nContact not found!");
								break;
							} else {

								if (contactsbBST.search_nameContact(contact.name)) {
									System.out.println("\nContact found!");
									System.out.println(contactsbBST.retrive());
									break;
								}

							}
							break;
						}

						case 2: {
							keyboard.nextLine();
							Contact contact = new Contact();
							System.out.print("\nEnter the contact's phone number:");
							contact.phonenumber = keyboard.nextLine();

							if (contactsbBST.empty() || !contactsbBST.search_phoneContact(contact.phonenumber)) {
								System.out.println("\nContact not found!");
								break;
							} else {
								if (contactsbBST.search_phoneContact(contact.phonenumber)) {
									System.out.println("\nContact found!");
									System.out.println(contactsbBST.retrive());
									break;
								}

							}
							break;

						}
						case 3: {
							keyboard.nextLine();
							Contact contact = new Contact();
							System.out.print("\nEnter the contact's email address:");
							contact.email = keyboard.nextLine();

							if (contactsbBST.empty() || !contactsbBST.search_emailContact(contact.email)) {
								System.out.println("\nContact not found!");
								break;
							} else {
								if (contactsbBST.search_emailContact(contact.email)) {
									System.out.println("\nContact found!");
									System.out.println(contactsbBST.retrive());
									break;
								}

							}
							break;
						}
						case 4: {
							keyboard.nextLine();
							Contact contact = new Contact();
							System.out.print("\nEnter the contact's address:");
							contact.address = keyboard.nextLine();

							if (contactsbBST.empty() || !contactsbBST.search_addressContact(contact.address)) {
								System.out.println("\nContact not found!");
								break;
							} else {
								if (contactsbBST.search_addressContact(contact.address)) {
									System.out.println("\nContact found!");
									System.out.println(contactsbBST.retrive());
									break;
								}

							}
							break;
						}
						// to search for a contact by it's birthday
						case 5: {
							keyboard.nextLine();
							LocalDate date = null;

							// this will check if the date is in right format.
							boolean trueDate = false;
							while (trueDate == false) {
								System.out.print("\nEnter the contact's birthday(MM/dd/yyyy):");
								String birthday = keyboard.nextLine();

								DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

								try {
									date = LocalDate.parse(birthday, formatter);
									trueDate = true;
								} catch (DateTimeParseException e) {

									System.out.println("\nInvalid date format. Please use MM/dd/yyyy.");
								}

							}

							if (contactsbBST.empty() || !contactsbBST.search_birhdayContact(date)) {
								System.out.println("\nContact not found!");
								break;
							} else {
								if (contactsbBST.search_birhdayContact(date)) {
									System.out.println("\nContact found!");
									System.out.println(contactsbBST.retrive());
									break;
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

					if (contactsbBST.empty())
						System.out.println("\nContact not found!");
					else {
						if (contactsbBST.deletContact(contact.name, events) == true) {
							System.out.println("\nContact Deleted Successfully!");
							break;
						} else
							System.out.println("\nContact not found!");
					}
					break;
				}

				// Schedule an event or appointment
				case 4: {

					System.out.println("Enter type:");
					System.out.println("1.event");
					System.out.println("2.appoitment");

					// to make sure that the user doesn't type invalid input
					int input4 = 0;
					boolean trueInput4 = false;
					while (!trueInput4) {
						try {
							System.out.print("\nEnter your choice: ");
							input4 = keyboard.nextInt();
							trueInput4 = true;
						} catch (InputMismatchException e) {
							System.out.println("Invalid input. Please enter a valid integer.");
							keyboard.next(); // to Consume the invalid input
						}
					}

					switch (input4) {
						// Schedule an event with one or many contactsbBST
						case 1: {
							keyboard.nextLine();
							Event event = new Event();
							int size;
							event.type = "event";
							// Contact contact = new Contact();
							System.out.print("\nEnter event title:");
							event.title = keyboard.nextLine();

							System.out.print("\nEnter contactsbBST name separated by a comma:");
							String contactNames = keyboard.nextLine();
							event.names = contactNames;
							// Split the input string by commas
							event.contactsNames = contactNames.split(",");
							size = event.contactsNames.length;
							// Trim leading and trailing whitespaces from each name
							for (int i = 0; i < event.contactsNames.length; i++) {
								event.contactsNames[i] = event.contactsNames[i].trim();
								// check if the contact exists or not
								if (contactsbBST.empty() ||
										!contactsbBST.search_nameContact(event.contactsNames[i])) {
									System.out.println("\nCantcat does not exists!");

								}

								boolean trueDate = false;
								while (!trueDate) {
									System.out.print("\nEnter event date and time (MM/DD/YYYY HH:MM): ");
									String dateAndTime = keyboard.nextLine();

									// this try and catch will check if the date and time in right format.
									try {

										// date and time here will separated into two variables
										DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm");

										LocalDateTime dateTime = LocalDateTime.parse(dateAndTime, formatter);

										LocalDate date = dateTime.toLocalDate();
										LocalTime time = dateTime.toLocalTime();
										event.date = date;
										event.time = time;

										trueDate = true;
									} catch (DateTimeParseException e) {
										System.out.println("Invalid date format. Please use MM/dd/yyyy HH:mm.");
									}
								}

								System.out.print("\nEnter event location:");
								event.location = keyboard.nextLine();

								// if the contact has event complexity event would not be added
								if (events.addEvent(event, events, size)) {

									System.out.println("\nEvent scheduled successfully!");
									break;
								} else
									System.out.println("\nconflicted Events with the contact");
							}
							break;

						}
						// Schedule an appointment (with one contact only)
						case 2: {
							keyboard.nextLine();
							int size = 1;
							Event event = new Event();
							event.type = "appointment";
							// Contact contact = new Contact();
							System.out.print("\nEnter appointment title:");
							event.title = keyboard.nextLine();
							event.contactsNames = new String[size];
							System.out.print("\nEnter contact name:");
							String contactNames = keyboard.nextLine();
							event.contactsNames[0] = contactNames;
							event.names = contactNames;

							// check if the contact exists or not
							if (contactsbBST.search_nameContact(event.contactsNames[0])) {
								boolean trueDate = false;
								while (!trueDate) {
									System.out.print("\nEnter appointment date and time (MM/DD/YYYY HH:MM): ");
									String dateAndTime = keyboard.nextLine();

									// this try and catch will check if the date and time in right format.
									try {

										// date and time here will separated into two variables
										DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm");

										LocalDateTime dateTime = LocalDateTime.parse(dateAndTime, formatter);

										LocalDate date = dateTime.toLocalDate();
										LocalTime time = dateTime.toLocalTime();
										event.date = date;
										event.time = time;

										trueDate = true;
									} catch (DateTimeParseException e) {
										System.out.println("Invalid date format. Please use MM/dd/yyyy HH:mm.");
									}
								}

								System.out.print("\nEnter appointment location:");
								event.location = keyboard.nextLine();

								// contact = contactsbBST.retrieve();

								// if the contact has event complexity event would not be added
								if (events.addEvent(event, events, size)) {

									System.out.println("\nappointment scheduled successfully!");
									break;
								} else
									System.out.println("\nconflicted appointment with the contact");
							} else
								System.out.println("\nCantcat does not exists!");
								
							break;
						}
					}
					break;
				}

				// Print event details
				case 5: {
					keyboard.nextLine();
					System.out.println("Enter search criteria:");
					System.out.println("1. contact name");
					System.out.println("2. Event title");

					int input3 = 0;
					boolean trueInput3 = false;
					while (!trueInput3) {
						try {
							System.out.print("\nEnter your choice: ");
							input3 = keyboard.nextInt();
							trueInput3 = true;
						} catch (InputMismatchException e) {
							System.out.println("\nInvalid input. Please enter a valid integer.");
							keyboard.next();
						}
					}
					switch (input3) {
						case 1: {
							keyboard.nextLine();
							String name = new String();
							System.out.print("\nEnter contact name:");
							name = keyboard.nextLine();

							if (contactsbBST.search_nameContact(name)) {
								System.out.println("\nContact found!");

								events.findFirst();

								while (events.current != null) {
									
									for (int i = 0; i < events.current.data.contactsNames.length; i++) {
										if (events.current.data.contactsNames[i].equalsIgnoreCase(name)) {
											System.out.println(events.retrieve());
										}
									}
									events.findNext();
								}
								if (events.empty())
									System.out.println("\nthis contact has no events!");
							} else
								System.out.println("\nCantcat does not exists!");

							break;
						}

						case 2: {
							keyboard.nextLine();
							Event event = new Event();
							System.out.print("\nEnter the event title:");
							event.title = keyboard.nextLine();

							if (events.searchEventTitle(event.title)) {
								System.out.println("Event found!");
								System.out.println(events.retrieve());
							} else
								System.out.println("\nEvent does not exists!");
							break;
						}

					}
					break;
				}

				// Print contactsbBST by first name
				case 6: {
					keyboard.nextLine();
					System.out.print("\nEnter the first name:");
					String firstName = keyboard.nextLine();

					if (contactsbBST.empty()) {
						System.out.println("\nNo contacts have the first name!");
						break;
					}

					boolean found = false; // to check if at least one contact was found

					contactsbBST.findFirstName(firstName, found);

					if (found == false) {
						System.out.println("\nNo contacts found with the first name: " + firstName);
					}

					break;
				}

				// Print all events alphabetically
				case 7: {

					if (!events.empty()) {
						// events will be printed directly because they were inserted in alphabetical
						// order
						events.printEvents();
					} else
						System.out.println("\nevents list is empty!");

					break;
				}

				case 8:
					System.out.println("\nGoodbye!");
					break;
				default:
					System.out.println("\nenter a number between 1 and 8");

			}

		} while (input1 != 8);

	}
}
