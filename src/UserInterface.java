import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.NumberFormat;
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
	private int LIST_REPAIRPLAN;
	private static ApplianceCompany applianceCompany;
	private NumberFormat moneyFormat = NumberFormat.getCurrencyInstance();
	private static final int EXIT = 0;
	private static final int ADD_CUSTOMER = 1;
	private static final int ADD_APPLIANCE = 2;
	private static final int ADD_INVENTORY = 3;
	private static final int PURCHASE = 4;
	private static final int LIST_CUSTOMERS = 5;
	private static final int LIST_APPLIANCE = 6;
	private static final int DISPLAY_TOTAL = 7;
	private static final int ENROLL_REPAIR_PLAN = 8;
	private static final int WITHDRAW_REPAIRPLAN = 9;
	private static final int BILL_REPAIRPLAN = 10;
	private static final int LIST_REPAIRPLANS = 11;
	private static final int LIST_BACKORDER = 12;
	private static final int SAVE = 13;
	private static final int HELP = 14;

	/**
	 * Made private for singleton pattern. Conditionally looks for any saved data.
	 * Otherwise, it gets a singleton WasherCompany object.
	 */
	private UserInterface() {
		if (yesOrNo("Look for saved data and  use it?")) {
			retrieve();
		} else {
			applianceCompany = ApplianceCompany.instance();
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
	 * @param prompt - whatever the user wants as prompt
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
	 * @param prompt The string to be prepended to the yes/no prompt
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
	 * @param prompt the string for prompting
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
	 * @param prompt the string for prompting
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
	 * Prompts the user to enter an appliance type.
	 * 
	 * @return the type of appliance
	 */
	public int getApplianceType() {
		int type;
		do {
			type = getIntegerNumber("Enter \n" + ApplianceCompany.CLOTHES_WASHER + " for Clothes Washer\n"
					+ ApplianceCompany.CLOTHES_DRYER + " for Clothes Dryer\n" + ApplianceCompany.KITCHEN_RANGE
					+ " for Kitchen Range\n" + ApplianceCompany.DISHWASHER + " for Dishwasher\n"
					+ ApplianceCompany.REFRIGERATOR + " for Refrigerator\n" + ApplianceCompany.FURNACE
					+ " for Furnace");
		} while (type != ApplianceCompany.CLOTHES_WASHER && type != ApplianceCompany.CLOTHES_DRYER
				&& type != ApplianceCompany.KITCHEN_RANGE && type != ApplianceCompany.DISHWASHER
				&& type != ApplianceCompany.REFRIGERATOR && type != ApplianceCompany.FURNACE);
		return type;
	}

	/**
	 * Displays the help screen
	 * 
	 */
	public void help() {
		System.out.println("Enter a number between 0 and 9 as explained below:\n");
		System.out.println(EXIT + " to Exit");
		System.out.println(ADD_CUSTOMER + " to add a customer");
		System.out.println(ADD_APPLIANCE + " to add an appliance item");
		System.out.println(ADD_INVENTORY + " to add appliance inventory");
		System.out.println(PURCHASE + " to process a purchase");
		System.out.println(LIST_CUSTOMERS + " to get a list of all the customers");
		System.out.println(LIST_APPLIANCE + " to get a list of all the appliances");
		System.out.println(DISPLAY_TOTAL + " to display the total of all sales");
		System.out.println(ENROLL_REPAIR_PLAN + " to enroll a customer in a repair plan");
		System.out.println(WITHDRAW_REPAIRPLAN + " to withdraw repair plan");
		System.out.println(BILL_REPAIRPLAN + " to bill all users for repair plan");
		System.out.println(LIST_REPAIRPLANS + " to display all user repair plans");
		System.out.println(LIST_BACKORDER + " to display the total of all back orders");
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
		result = applianceCompany.addCustomer(name, phone);
		if (result == null) {
			System.out.println("Could not add customer.\n");
		}
		System.out.println("Customer added!");
		System.out.println(result + "\n");
	}

	/**
	 * Method to be called for adding an appliance item. Prompts the user for the
	 * appropriate values and uses the appropriate ApplianceCompany method for
	 * adding the appliance.
	 * 
	 */
	public void addApplianceItems() {
		ApplianceItem result;
		do {
			int type = getApplianceType();
			String brand = getToken("Enter brand");
			String model = getToken("Enter model");
			double price = getDoubleNumber("EnterPrice");
			double repairPlanPrice = 0.0;
			int capacity = 0;
			int maximumHeatingOutput = 0;
			if (type == ApplianceCompany.CLOTHES_WASHER || type == ApplianceCompany.CLOTHES_DRYER) {
				repairPlanPrice = getDoubleNumber("Enter the price of the repair plan");
			}
			if (type == ApplianceCompany.REFRIGERATOR) {
				capacity = getIntegerNumber("Enter the capacity in liters");
			}
			if (type == ApplianceCompany.FURNACE) {
				maximumHeatingOutput = getIntegerNumber("Enter the maximum heating output in BTU");
			}
			result = applianceCompany.addApplianceItem(type, model, brand, price, repairPlanPrice, capacity,
					maximumHeatingOutput);
			if (result != null) {
				System.out.println(result);
			} else {
				System.out.println("Appliance could not be added");
			}
			if (!yesOrNo("Add more appliances?")) {
				break;
			}
		} while (true);
	}

	/**
	 * Method to be called for adding inventory of an appliance. Prompts the user
	 * for the appropriate appliance information and uses the appropriate
	 * ApplianceCompany method for adding inventory.
	 * 
	 */
	public void addInventory() {
		int result;
		do {
			String brand = getToken("Enter appliance brand");
			String model = getToken("Enter appliance model");
			int quantity = getIntegerNumber("Enter quantity");
			result = applianceCompany.addInventory(brand, model, quantity);
			switch (result) {
			case ApplianceCompany.APPLIANCE_NOT_FOUND:
				System.out.println("No such appliance in inventory\n");
				break;
			case ApplianceCompany.OPERATION_FAILED:
				System.out.println("Appliance quantity could not be updated.\n");
				break;
			case ApplianceCompany.OPERATION_COMPLETED:
				System.out.println("Appliance quantity was updated.");
				System.out.println("Any back orders on this appliance were processed.\n");
				break;
			default:
				System.out.println("An error has occurred\n");
			}
			if (!yesOrNo("Add more quantity for other appliance?")) {
				break;
			}
		} while (true);
	}

	/**
	 * Method to be called for purchasing appliances. Prompts the user for the
	 * appropriate values and uses the appropriate ApplianceCompany method for
	 * processing purchases.
	 * 
	 */
	public void purchase() {

		int result;
		String customerId = getToken("Enter customer id");
		if (applianceCompany.searchCustomer(customerId) == null) {
			System.out.println("No such customer\n");
			return;
		}
		do {
			String brand = getToken("Enter appliance brand");
			String model = getToken("Enter appliance model");
			int quantity = getIntegerNumber("Enter quantity");
			result = applianceCompany.purchaseAppliance(customerId, brand, model, quantity);
			if (result == ApplianceCompany.OPERATION_COMPLETED) {
				System.out.println("Appliance was successfully purchased.\n");
			} else if (result == ApplianceCompany.BACKORDER_PLACED) {
				System.out.println("A back order was placed.\n");
			} else if (result == ApplianceCompany.APPLIANCE_NOT_FOUND) {
				System.out.println("Appliance not found!");
				System.out.println("Purchase could not be completed.\n");
			} else if (result == ApplianceCompany.FURNACE) {
				System.out.println("Unable to place back orders on furnaces.\n");
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
		Iterator result = applianceCompany.listCustomers();
		if (result == null) {
			System.out.println("No customers to print.\n");
		} else {
			System.out.println("Here is the list of customers: ");
			while (result.hasNext()) {
				Customer customer = (Customer) result.next();
				System.out.println(customer.toString() + "\tEnrolled in Repair Plan: " + customer.getHasRepairPlan());
			}
			System.out.println();
		}
	}

	/**
	 * Method to be called to display a list of all the appliances.
	 * 
	 */
	public void displayApplianceList() {
		if (yesOrNo("Would you like to print all appliances? ")) {
			System.out.println(applianceCompany.listAppliances(0));
		} else {
			System.out.println(applianceCompany.listAppliances(getApplianceType()));
		}
	}

	/**
	 * Method to be called for displaying the total sales amount.
	 * 
	 */
	public void displayTotalSales() {
		System.out.println("Total Appliance Sales " + moneyFormat.format(applianceCompany.getTotalApplianceSales()));
		System.out.println("Total Repair Plan Sales " + moneyFormat.format(applianceCompany.getTotalRepairPlanSales()));
	}

// 	NEED TO UPDATE TAKEN FROM LIST CUSTOMERS,NEED TO SHOW ENTIRE BACK ORDER WITH VISIOR PATTERN
	public void listBackOrder() {
		Iterator result = applianceCompany.listCustomers();
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
	 * Method to be called to display a list of all the customers enrolled in repair
	 * plans.
	 */

	public void listUserRepairPlans() {
		System.out.println(applianceCompany.listUserRepairPlans());
	}

	public void billRepairPlans() {
		int result = applianceCompany.billRepairPlans();
		switch (result) {
		case ApplianceCompany.REPAIR_PLAN_NOT_FOUND:
			System.out.println("No repair plans exist\n");
			break;
		case ApplianceCompany.OPERATION_FAILED:
			System.out.println("Could not bill repair plans.\n");
			break;
		case ApplianceCompany.OPERATION_COMPLETED:
			System.out.println("Repair plans were successfully billed.");
			break;
		default:
			System.out.println("An error has occurred\n");
		}
	}

	public void withdrawRepairPlan() {
		int result;
		String customerId = getToken("Enter customer ID");
		String brand = getToken("Enter appliance brand");
		String model = getToken("Enter appliance model");
		result = applianceCompany.withdrawRepairPlan(brand, model, customerId);
		switch (result) {
		case ApplianceCompany.REPAIR_PLAN_NOT_FOUND:
			System.out.println("No such repair plan exists\n");
			break;
		case ApplianceCompany.OPERATION_FAILED:
			System.out.println("Could not withdraw from Repair Plan.\n");
			break;
		case ApplianceCompany.OPERATION_COMPLETED:
			System.out.println("Customer successfully withdrew from Repair Plan.");
			break;
		default:
			System.out.println("An error has occurred\n");
		}
	}

	/**
	 * Method to be called to enroll a customer in a repair plan. Prompts the user
	 * for important appliance and customer information.
	 * 
	 */
	public void enrollInRepairPlan() {
		int result;
		String customerId = getToken("Enter customer ID");
		String brand = getToken("Enter appliance brand");
		String model = getToken("Enter appliance model");
		result = applianceCompany.enrollInRepairPlan(customerId, brand, model);
		switch (result) {
		case ApplianceCompany.APPLIANCE_NOT_FOUND:
			System.out.println("No such washer in inventory\n");
			break;
		case ApplianceCompany.CUSTOMER_NOT_FOUND:
			System.out.println("No such customer in customer list\n");
			break;
		case ApplianceCompany.ALREADY_EXISTS:
			System.out.println("Repair plan already exists\n");
			break;
		case ApplianceCompany.OPERATION_FAILED:
			System.out.println("Could not be enrolled in Repair Plan.\n");
			break;
		case ApplianceCompany.OPERATION_COMPLETED:
			System.out.println("Customer was enrolled in a Repair Plan.");
			break;
		default:
			System.out.println("An error has occurred\n");
		}
	}

	/**
	 * Method to be called for saving the WasherCompany object. Uses the appropriate
	 * WasherCompany method for saving.
	 * 
	 */
	private void save() {
		if (applianceCompany.save()) {
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
			if (applianceCompany == null) {
				applianceCompany = ApplianceCompany.retrieve();
				if (applianceCompany != null) {
					System.out.println(
							"The WasherCompany has been successfully retrieved from the file WasherCompany \n");
				} else {
					System.out.println("File doesnt exist; creating new WasherCompany");
					applianceCompany = ApplianceCompany.instance();
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
			case ADD_APPLIANCE:
				addApplianceItems();
				break;
			case ADD_INVENTORY:
				addInventory();
				break;
			case PURCHASE:
				purchase();
				break;
			case LIST_APPLIANCE:
				displayApplianceList();
				break;
			case LIST_CUSTOMERS:
				displayCustomerList();
				break;
			case DISPLAY_TOTAL:
				displayTotalSales();
				break;
			case ENROLL_REPAIR_PLAN:
				enrollInRepairPlan();
				break;
			case WITHDRAW_REPAIRPLAN:
				withdrawRepairPlan();
				break;
			case BILL_REPAIRPLAN:
				billRepairPlans();
				break;
			case LIST_REPAIRPLANS:
				listUserRepairPlans();
				break;
			case LIST_BACKORDER:
				listBackOrder();
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
	 * @param args not used
	 */
	public static void main(String[] args) {
		UserInterface.instance().process();
	}
}