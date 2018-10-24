import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.StringTokenizer;

/**
 * This class implements the user interface for the WasherCompany project. The
 * commands are encoded as integers using a number of static final variables. A
 * number of utility methods exist to make it easier to parse the input.
 * 
 * @author Jose Morales, Jonathan Tseng, Stephen Thomas and Xeng Vang
 *
 */
public class UserInterface {

	private static UserInterface userInterface;
	private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	private static WasherCompany washerCompany;
	private static final int EXIT = 0;
	private static final int ADD_CUSTOMER = 1;
	private static final int ADD_WASHER = 2;
	private static final int ADD_INVENTORY = 3;
	private static final int PURCHASE = 4;
	private static final int LIST_CUSTOMERS = 5;
	private static final int LIST_WASHERS = 6;
	private static final int DISPLAY_TOTAL = 7;
	private static final int SAVE = 8;
	private static final int HELP = 9;

	/**
	 * Made private for singleton pattern. Conditionally looks for any saved data.
	 * Otherwise, it gets a singleton WasherCompany object.
	 */
	private UserInterface() {
		if (yesOrNo("Look for saved data and  use it?")) {
			retrieve();
		} else {
			washerCompany = WasherCompany.instance();
		}
	}

	/**
	 * Supports the singleton pattern
	 * 
	 * @return the singleton object
	 */
	public static UserInterface instance() {
		if (userInterface == null) {
			return userInterface = new UserInterface();
		} else {
			return userInterface;
		}
	}

	/**
	 * Gets a token after prompting
	 * 
	 * @param prompt
	 *            - whatever the user wants as prompt
	 * @return - the token from the keyboard
	 * 
	 */
	public String getToken(String prompt) {
		do {
			try {
				System.out.println(prompt);
				String line = reader.readLine();
				StringTokenizer tokenizer = new StringTokenizer(line, "\n\r\f");
				if (tokenizer.hasMoreTokens()) {
					return tokenizer.nextToken();
				}
			} catch (IOException ioe) {
				System.exit(0);
			}
		} while (true);
	}

	/**
	 * Queries for a yes or no and returns true for yes and false for no
	 * 
	 * @param prompt
	 *            The string to be prepended to the yes/no prompt
	 * @return true for yes and false for no
	 * 
	 */
	private boolean yesOrNo(String prompt) {
		String more = getToken(prompt + " (Y|y)[es] or anything else for no");
		if (more.charAt(0) != 'y' && more.charAt(0) != 'Y') {
			return false;
		}
		return true;
	}

	/**
	 * Converts the string to a number of type integer
	 * 
	 * @param prompt
	 *            the string for prompting
	 * @return the integer corresponding to the string
	 * 
	 */
	public int getIntegerNumber(String prompt) {
		do {
			try {
				String item = getToken(prompt);
				Integer number = Integer.valueOf(item);
				return number.intValue();
			} catch (NumberFormatException nfe) {
				System.out.println("Please input a number ");
			}
		} while (true);
	}

	/**
	 * Converts the string to a number of type double
	 * 
	 * @param prompt
	 *            the string for prompting
	 * @return the double corresponding to the string
	 */
	public double getDoubleNumber(String prompt) {
		do {
			try {
				String item = getToken(prompt);
				Double number = Double.valueOf(item);
				return number.doubleValue();
			} catch (NumberFormatException nfe) {
				System.out.println("Please input a number ");
			}
		} while (true);
	}

	/**
	 * Prompts for a command from the keyboard
	 * 
	 * @return a valid command
	 * 
	 */
	public int getCommand() {
		do {
			try {
				int value = Integer.parseInt(getToken("Enter command (" + HELP + " for help):"));
				if (value >= EXIT && value <= HELP) {
					return value;
				}
			} catch (NumberFormatException nfe) {
				System.out.println("Enter a number");
			}
		} while (true);
	}

	/**
	 * Displays the help screen
	 * 
	 */
	public void help() {
		System.out.println("Enter a number between 0 and 9 as explained below:\n");
		System.out.println(EXIT + " to Exit");
		System.out.println(ADD_CUSTOMER + " to add a customer");
		System.out.println(ADD_WASHER + " to add a washer");
		System.out.println(ADD_INVENTORY + " to add washer inventory");
		System.out.println(PURCHASE + " to process a purchase");
		System.out.println(LIST_CUSTOMERS + " to get a list of all the customers");
		System.out.println(LIST_WASHERS + " to get a list of all the washers");
		System.out.println(DISPLAY_TOTAL + " to display the total of all sales");
		System.out.println(SAVE + " to save data");
		System.out.println(HELP + " for help\n");
	}

	/**
	 * Method to be called for adding a customer. Prompts the user for the
	 * appropriate values and uses the appropriate WasherCompany method for adding a
	 * customer.
	 * 
	 */
	public void addCustomer() {
		String name = getToken("Enter customer name");
		String phone = getToken("Enter phone number");
		Customer result;
		result = washerCompany.addCustomer(name, phone);
		if (result == null) {
			System.out.println("Could not add customer.\n");
		}
		System.out.println("Customer added!");
		System.out.println(result + "\n");
	}

	/**
	 * Method to be called for adding a washer. Prompts the user for the appropriate
	 * values and uses the appropriate WasherCompany method for adding a washer.
	 * 
	 */
	public void addWashers() {
		Washer result;
		do {
			String brand = getToken("Enter brand");
			String model = getToken("Enter model");
			double price = getDoubleNumber("Enter price");
			result = washerCompany.addWasher(brand, model, price);
			if (result != null) {
				System.out.println("Washer was added!");
				System.out.println(result + "\n");
			} else {
				System.out.println("Washer could not be added.\n");
			}
		} while (yesOrNo("Add more washers?"));
	}

	/**
	 * Method to be called for adding inventory of a washer. Prompts the user for
	 * the appropriate washer information and uses the appropriate WasherCompany
	 * method for adding inventory.
	 * 
	 */
	public void addInventory() {
		int result;
		do {
			String brand = getToken("Enter washer brand");
			String model = getToken("Enter washer model");
			int quantity = getIntegerNumber("Enter quantity");
			result = washerCompany.addInventory(brand, model, quantity);
			switch (result) {
			case WasherCompany.WASHER_NOT_FOUND:
				System.out.println("No such washer in inventory\n");
				break;
			case WasherCompany.OPERATION_FAILED:
				System.out.println("Washer quantity could not be updated.\n");
				break;
			case WasherCompany.OPERATION_COMPLETED:
				System.out.println("Washer quantity was updated.");
				System.out.println("Any back orders on this washer were processed.\n");
				break;
			default:
				System.out.println("An error has occurred\n");
			}
			if (!yesOrNo("Add more quantity for other washers?")) {
				break;
			}
		} while (true);
	}

	/**
	 * Method to be called for purchasing washers. Prompts the user for the
	 * appropriate values and uses the appropriate WasherCompany method for
	 * processing purchases.
	 * 
	 */
	public void purchase() {

		int result;
		String customerId = getToken("Enter customer id");
		if (washerCompany.searchCustomer(customerId) == null) {
			System.out.println("No such customer\n");
			return;
		}
		do {
			String brand = getToken("Enter washer brand");
			String model = getToken("Enter washer model");
			int quantity = getIntegerNumber("Enter quantity");
			result = washerCompany.purchaseWasher(customerId, brand, model, quantity);
			if (result == WasherCompany.OPERATION_COMPLETED) {
				System.out.println("Washer was successfully purchased.\n");
			} else if (result == WasherCompany.BACKORDER_PLACED) {
				System.out.println("A back order was placed.\n");
			} else if (result == WasherCompany.WASHER_NOT_FOUND) {
				System.out.println("Washer not found!");
				System.out.println("Purchase could not be completed.\n");
			} else {
				System.out.println("Purchase could not be completed.\n");
			}
		} while (yesOrNo("Buy more washers?"));
	}

	/**
	 * Method to be called to display a list of all the customers.
	 * 
	 */
	public void displayCustomerList() {
		Iterator result = washerCompany.listCustomers();
		if (result == null) {
			System.out.println("No customers to print.\n");
		} else {
			System.out.println("Here is the list of customers: ");
			while (result.hasNext()) {
				Customer customer = (Customer) result.next();
				System.out.println(customer.toString());
			}
			System.out.println();
		}
	}

	/**
	 * Method to be called to display a list of all the washers.
	 * 
	 */
	public void displayWasherList() {
		Iterator result = washerCompany.listWashers();
		if (result == null) {
			System.out.println("No washers to print.\n");
		} else {
			System.out.println("Here is the list of washers: ");
			while (result.hasNext()) {
				Washer washer = (Washer) result.next();
				System.out.println(washer.toString());
			}
			System.out.println();
		}
	}

	/**
	 * Method to be called for displaying the total sales amount.
	 * 
	 */
	public void displayTotalSales() {
		System.out.println("Total Sales $" + washerCompany.displayTotal());
	}

	/**
	 * Method to be called for saving the WasherCompany object. Uses the appropriate
	 * WasherCompany method for saving.
	 * 
	 */
	private void save() {
		if (washerCompany.save()) {
			System.out.println(" The WasherCompany has been successfully saved in the file WasherCompanyData \n");
		} else {
			System.out.println(" There has been an error in saving \n");
		}
	}

	/**
	 * Method to be called for retrieving saved data. Uses the appropriate
	 * WasherCompany method for retrieval.
	 * 
	 */
	private void retrieve() {
		try {
			if (washerCompany == null) {
				washerCompany = WasherCompany.retrieve();
				if (washerCompany != null) {
					System.out.println(
							"The WasherCompany has been successfully retrieved from the file WasherCompany \n");
				} else {
					System.out.println("File doesnt exist; creating new WasherCompany");
					washerCompany = WasherCompany.instance();
				}
			}
		} catch (Exception cnfe) {
			cnfe.printStackTrace();
		}
	}

	/**
	 * Orchestrates the whole process. Calls the appropriate method for the
	 * different functionalities.
	 * 
	 */
	public void process() {
		int command;
		help();
		while ((command = getCommand()) != EXIT) {
			switch (command) {
			case ADD_CUSTOMER:
				addCustomer();
				break;
			case ADD_WASHER:
				addWashers();
				break;
			case ADD_INVENTORY:
				addInventory();
				break;
			case PURCHASE:
				purchase();
				break;
			case LIST_WASHERS:
				displayWasherList();
				break;
			case LIST_CUSTOMERS:
				displayCustomerList();
				break;
			case DISPLAY_TOTAL:
				displayTotalSales();
				break;
			case SAVE:
				save();
				break;
			case HELP:
				help();
				break;
			}
		}
	}

	/**
	 * The method to start the application. Simply calls process().
	 * 
	 * @param args
	 *            not used
	 */
	public static void main(String[] args) {
		UserInterface.instance().process();
	}
}