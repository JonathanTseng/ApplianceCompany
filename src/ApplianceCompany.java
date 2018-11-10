import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Iterator;

/**
 * This class implements Serializable. It contains the methods that are used to
 * organize business processes in the WasherCompany project.
 * 
 * @author Jose Morales, Jonathan Tseng, Stephen Thomas, and Xeng Vang
 *
 */
public class ApplianceCompany implements Serializable {

	private static final long serialVersionUID = 1L;
	public static final int WASHER_NOT_FOUND = 1;
	public static final int OPERATION_FAILED = 2;
	public static final int OPERATION_COMPLETED = 3;
	public static final int BACKORDER_PLACED = 4;
	public static final int CUSTOMER_NOT_FOUND = 5;

	public static final int CLOTHES_WASHER = 1;
	public static final int CLOTHES_DRYER = 2;
	public static final int KITCHEN_RANGE = 3;
	public static final int DISHWASHER = 4;
	public static final int REFRIGERATOR = 5;
	public static final int FURNACE = 6;
	private CustomerList customerList;
	private ApplianceList applianceList;
	private static ApplianceCompany applianceCompany;
	private double totalSales = 0;

	/**
	 * Private for the singleton pattern, it creates the customer list and the
	 * washer list collection objects.
	 */
	private ApplianceCompany() {
		customerList = CustomerList.instance();
		applianceList = ApplianceList.instance();
	}

	/**
	 * Supports the singleton pattern
	 * 
	 * @return the singleton object
	 */
	public static ApplianceCompany instance() {
		if (applianceCompany == null) {
			MemberIdServer.instance();
			return (applianceCompany = new ApplianceCompany());
		} else {
			return applianceCompany;
		}
	}

	/**
	 * Organizes the operations for adding a customer
	 * 
	 * @param name        name of customer
	 * @param phoneNumber customer phone number
	 * @return the Customer object created
	 * 
	 */
	public Customer addCustomer(String name, String phoneNumber) {
		Customer customer = new Customer(name, phoneNumber);
		if (customerList.insertCustomer(customer)) {
			return (customer);
		}
		return null;
	}

	/**
	 * Organizes the operations for adding inventory.
	 * 
	 * @param brand    appliance brand
	 * @param model    appliance model
	 * @param quantity quantity of appliances
	 * @return the result of the operation
	 */
	public int addInventory(String brand, String model, int quantity) {

		int result = OPERATION_FAILED;
		int quantityToAdd = quantity;

		ApplianceItem appliance = applianceList.search(brand, model);
		if (appliance == null) {
			result = WASHER_NOT_FOUND;
		} else {

			while (quantityToAdd > 0) {
				appliance.addQuantity(1);
				if (appliance.hasBackOrder()) {
					BackOrder backOrder = appliance.getNextBackOrder();
					purchaseWasher(backOrder.getCustomer().getId(), backOrder.getApplianceItem().getBrand(),
							backOrder.getApplianceItem().getModel(), 1);
				}
				quantityToAdd--;
			}

			// What is the purpose of the following segment of code
			BackOrder backOrder = appliance.getNextBackOrder();
			if (backOrder == null) {
				result = OPERATION_COMPLETED;
			} else {
				purchaseWasher(backOrder.getCustomer().getName(), backOrder.getApplianceItem().getBrand(),
						backOrder.getApplianceItem().getModel(), backOrder.getApplianceItem().getQuantity());
				result = OPERATION_COMPLETED;
			}

		}
		return result;
	}

	/**
	 * Organizes the operations for adding an appliance item
	 * 
	 * @param type                 the type of appliance that is to be added
	 * @param model                the model of the appliance
	 * @param brand                the brand of the appliance
	 * @param price                the price of the appliance
	 * @param repairPlanPrice      the repair plan price if applicable
	 * @param capacity             the appliance capacity if applicable
	 * @param maximumHeatingOutput the maximum heating output if applicable
	 * @return the ApplianceItem object that was added
	 */
	public ApplianceItem addApplianceItem(int type, String model, String brand, double price, double repairPlanPrice,
			int capacity, int maximumHeatingOutput) {
		ApplianceItem item = ApplianceItemFactory.instance().createApplianceItem(type, model, brand, price,
				repairPlanPrice, capacity, maximumHeatingOutput);
		if (applianceList.insertApplianceItem(item)) {
			return (item);
		}
		return null;
	}

	/*
	 * no longer needed because of the addAppliance method /** Organizes the
	 * operations to add a new washer
	 * 
	 * @param brand washer brand
	 * 
	 * @param model washer model
	 * 
	 * @param price washer price
	 * 
	 * @return
	 *
	 * public Washer addWasher(String brand, String model, double price) { Washer
	 * washer = new Washer(brand, model, price); if
	 * (washerList.insertWasher(washer)) { return (washer); } return null; }
	 */

	/**
	 * Organizes the operations for displaying the total sales
	 * 
	 * @return the amount of total sales
	 */
	public double displayTotal() {
		return totalSales;
	}

	public void setTotalSales(double additionalSaleTotal) {
		totalSales += additionalSaleTotal;
	}

	/**
	 * Organizes the operations for displaying all the customers.
	 * 
	 * @return iterator to the collection
	 */

	public Iterator listCustomers() {
		if (customerList.iterator() == null) {
			return (null);
		} else {
			return customerList.iterator();
		}
	}

	/**
	 * Organizes the operations for displaying the washers
	 * 
	 * @return iterator to the collection
	 */
	public Iterator listWashers() {
		if (washerList.getWasherList() == null) {
			return (null);
		} else {
			return washerList.getWasherList();
		}

	}

	/**
	 * Organizes the purchase of an appliance
	 * 
	 * @param customerId id of customer
	 * @param brand      appliance brand
	 * @param model      appliance model
	 * @param quantity   quantity to be purchased
	 * @return a code that represents the outcome of the operation
	 */
	public int purchaseWasher(String customerId, String brand, String model, int quantity) {
		int result = OPERATION_FAILED;
		ApplianceItem appliance = applianceList.search(brand, model);
		Customer customer = customerList.search(customerId, "");

		if (appliance == null) {
			return (WASHER_NOT_FOUND);
		}

		if (customer == null) {
			return (CUSTOMER_NOT_FOUND);
		}

		result = appliance.purchase(quantity, customer, appliance);

		/*
		 * code moved to the ApplianceItem and Furnace classes while (quantity > 0) {
		 * 
		 * if (appliance.getQuantity() > 0) { appliance.purchase(); totalSales +=
		 * appliance.getPrice(); result = OPERATION_COMPLETED; } else { BackOrder
		 * backOrder = new BackOrder(customer, appliance);
		 * appliance.placeBackOrder(backOrder); result = BACKORDER_PLACED; }
		 * 
		 * quantity--;
		 * 
		 * }
		 */

		return result;

	}

	/**
	 * Searches for a given customer
	 * 
	 * @param customerId id of customer
	 * @return true if customer is a part of the customer list collection
	 */
	public Customer searchCustomer(String customerId) {
		return customerList.search(customerId, "");
	}

	/**
	 * This method will try and deserialize a saved file of Washer Company from a
	 * disk
	 * 
	 * @return a WasherCompany object
	 */

	public static ApplianceCompany retrieve() {

		try {
			FileInputStream file = new FileInputStream("WasherCompanyData");
			ObjectInputStream input = new ObjectInputStream(file);
			applianceCompany = (ApplianceCompany) input.readObject();
			MemberIdServer.retrieve(input);
			return applianceCompany;

		} catch (IOException ioe) {
			ioe.printStackTrace();
			return null;
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
			return null;
		}
	}

	/**
	 * Will serialize the WasherCompany object.
	 * 
	 * @return true if file could be saved somewhere on disk
	 * @return false if it can't be saved to disk
	 */
	public static boolean save() {

		try {
			FileOutputStream file = new FileOutputStream("WasherCompanyData");
			ObjectOutputStream output = new ObjectOutputStream(file);
			output.writeObject(applianceCompany);
			output.writeObject(MemberIdServer.instance());
			file.close();
			return true;
		} catch (IOException ioe) {
			ioe.printStackTrace();
			return false;
		}
	}

	/**
	 * String of the WasherCompany
	 * 
	 */
	@Override
	public String toString() {
		return customerList + "\n" + applianceList;

	}

}
