

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
						
						//this will check if the date is in right format.
						boolean trueDate = false;
						while(trueDate == false){
						System.out.print("Enter the contact's birthday (MM/dd/yyyy):");
						String birthday = keyboard.nextLine();

						try {
						    contact.birthday = LocalDate.parse(birthday, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
						    trueDate = true;
						} catch (DateTimeParseException e) {
							System.out.println("Invalid date format. Please use MM/dd/yyyy.");
						}
						
						}
						

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
				    LocalDate date = null;
				    
				    //this will check if the date is in right format.
					boolean trueDate = false;
					while(trueDate == false){
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
				       

				    if (contacts.empty() || !contacts.searchContactBirthday(date)) {
				        System.out.println("\nContact not found!");
				        break;
				    } else {
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
					if (contacts.deletContact(contact, events)) {
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
			    contact.name = event.name = keyboard.nextLine();
			    

			    // check if the contact exists or not
			    if (contacts.searchContactName(contact.name)) {
			        boolean trueDate = false;
			        while (!trueDate) {
			            System.out.print("\nEnter event date and time (MM/DD/YYYY HH:MM): ");
			            String dateAndTime = keyboard.nextLine();

			            
			          //this try and catch will check if the date and time in right format.
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

			        //contact = contacts.retrieve();

			        // if the contact has event complexity event would not be added
			        if (contact.addEvent(contact.name ,event, events)) {

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
				
				
				int input3 = 0;
				boolean trueInput3 = false;
				while (!trueInput3) {
			        try {
			            System.out.print("\nEnter your choice: ");
			            input3 = keyboard.nextInt();
			            trueInput3 = true;
			        } catch (InputMismatchException e) {
			            System.out.println("Invalid input. Please enter a valid integer.");
			            keyboard.next(); 
			        }
			    }
				switch (input3) {
				case 1: {
					keyboard.nextLine();
					Event event = new Event();
					System.out.print("\nEnter contact name:");
					event.name = keyboard.nextLine();

					if (contacts.searchContactName(event.name)) {
						System.out.println("\nContact found!");
						

						events.findFirst();

						while (events.current != null) {
						    if (events.current.data.name.equalsIgnoreCase(event.name)) {
						        System.out.println(events.retrieve());
						    }
						    events.findNext();
						}
						if (events.empty())
							System.out.println("\n this contact has no events!");
					} else
						System.out.println("\n Cantcat does not exists!");

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

			// Print contacts by first name
			case 6: {
			    keyboard.nextLine();
			    System.out.print("\nEnter the first name:");
			    String firstName = keyboard.nextLine();

			    if (contacts.empty()) {
			        System.out.println("\nNo Contacts have the first name!");
			        break; 
			    }

			    contacts.findFirst();
			    boolean found = false; //to check if at least one contact was found

			    while (contacts.current != null) {
			        String fullName = contacts.retrieve().name;
			        String[] allNames = fullName.split(" ");

			        if (allNames.length > 0 && allNames[0].equalsIgnoreCase(firstName)) {
			            System.out.println(contacts.retrieve() + "\n");
			            found = true; //true if a contact was found
			        }

			        contacts.findNext();
			    }

			    if (!found) {
			        System.out.println("No Contacts found with the first name: " + firstName);
			    }

			    break;
			}

			// Print all events alphabetically
			case 7: {
				
				if (!events.empty()) {
					//events will be printed directly because they were inserted in alphabetical order
					events.printEvents();
				} else
					System.out.println("\nevents list is empty!");

				break;
			}

			case 8:
				System.out.println("\nGoodbye!");
				break;
			default:
				System.out.println("\n enter a number between 1 and 8");

			}

		} while (input1 != 8);

		
	}
}
