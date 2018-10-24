import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * This class implements Serializable. It contains the methods that are used to
 * organize business processes in the WasherCompany project.
 * 
 * @author Jose
 *
 */
public class WasherCompany implements Serializable {

	private static final long serialVersionUID = 1L;
	public static final int WASHER_NOT_FOUND = 1;
	public static final int OPERATION_FAILED = 2;
	public static final int OPERATION_COMPLETED = 3;
	public static final int BACKORDER_PLACED = 4;
	public static final int CUSTOMER_NOT_FOUND = 5;
	private CustomerList customerList;
	private WasherList washerList;
	private static WasherCompany washerCompany;
	private double totalSales = 0;
	private ArrayList<Washer> washer = new ArrayList<Washer>();

	/**
	 * Private for the singleton pattern, it creates the customer list and the
	 * washer list collection objects.
	 */
	private WasherCompany() {
		customerList = CustomerList.instance();
		washerList = WasherList.instance();
	}

	/**
	 * Supports the singleton pattern
	 * 
	 * @return the singleton object
	 */
	public static WasherCompany instance() {
		if (washerCompany == null) {
			MemberIdServer.instance();
			return (washerCompany = new WasherCompany());
		} else {
			return washerCompany;
		}
	}

	/**
	 * Organizes the operations for adding a customer
	 * 
	 * @param name
	 *            name of customer
	 * @param phoneNumber
	 *            customer phone number
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
	 * @param brand
	 *            washer brand
	 * @param model
	 *            washer model
	 * @param quantity
	 *            quantity of washers
	 * @return the result of the operation
	 */
	public int addInventory(String brand, String model, int quantity) {

		int result = OPERATION_FAILED;
		int quantityToAdd = quantity;

		Washer washer = washerList.search(brand, model);
		if (washer == null) {
			result = WASHER_NOT_FOUND;
		} else {

			while (quantityToAdd > 0) {
				washer.addQuantity(1);
				if (washer.hasBackOrder()) {
					BackOrder backOrder = washer.getNextBackOrder();
					purchaseWasher(backOrder.getCustomer().getId(), backOrder.getWasher().getBrand(),
							backOrder.getWasher().getModel(), 1);
				}

				quantityToAdd--;
			}

			BackOrder backOrder = washer.getNextBackOrder();
			if (backOrder == null) {
				result = OPERATION_COMPLETED;
			} else {
				purchaseWasher(backOrder.getCustomer().getName(), backOrder.getWasher().getBrand(),
						backOrder.getWasher().getModel(), backOrder.getWasher().getQuantity());
				result = OPERATION_COMPLETED;
			}

		}
		return result;
	}

	/**
	 * Organizes the operations to add a new washer
	 * 
	 * @param brand
	 *            washer brand
	 * @param model
	 *            washer model
	 * @param price
	 *            washer price
	 * @return
	 */
	public Washer addWasher(String brand, String model, double price) {
		Washer washer = new Washer(brand, model, price);
		if (washerList.insertWasher(washer)) {
			return (washer);
		}
		return null;
	}

	/**
	 * Organizes the operations for displaying the total sales
	 * 
	 * @return the amount of total sales
	 */
	public double displayTotal() {
		return totalSales;
	}

	/**
	 * Organizes the operations for displaying all the customers.
	 * 
	 * @return iterator to the collection
	 */
	public Iterator listCustomers() {
		if (customerList.getCustomerList() == null) {
			return (null);
		} else {
			return customerList.getCustomerList();
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
	 * Organizes the purchase of a washer
	 * 
	 * @param customerId
	 *            id of customer
	 * @param brand
	 *            washer brand
	 * @param model
	 *            washer model
	 * @param quantity
	 *            quantity to be purchased
	 * @return a code that represents the outcome of the operation
	 */
	public int purchaseWasher(String customerId, String brand, String model, int quantity) {

		// Have to go back and handle case where some washers were purchased
		// successfully but a back order was also placed.

		int result = OPERATION_FAILED;

		Washer washer = washerList.search(brand, model);
		Customer customer = customerList.search(customerId);

		if (washer == null) {
			return (WASHER_NOT_FOUND);
		}

		if (customer == null) {
			return (CUSTOMER_NOT_FOUND);
		}

		while (quantity > 0) {

			if (washer.getQuantity() > 0) {
				washer.purchase();
				totalSales += washer.getPrice();
				result = OPERATION_COMPLETED;
			} else {
				BackOrder backOrder = new BackOrder(customer, washer);
				washer.placeBackOrder(backOrder);
				result = BACKORDER_PLACED;
			}

			quantity--;

		}
		return result;

	}

	/**
	 * Searches for a given customer
	 * 
	 * @param customerId
	 *            id of customer
	 * @return true if customer is a part of the customer list collection
	 */
	public Customer searchCustomer(String customerId) {
		return customerList.search(customerId);
	}

	/**
	 * This method will try and deserialize a saved file of Washer Company from a
	 * disk
	 * 
	 * @return a WasherCompany object
	 */

	public static WasherCompany retrieve() {

		try {
			FileInputStream file = new FileInputStream("WasherCompanyData");
			ObjectInputStream input = new ObjectInputStream(file);
			washerCompany = (WasherCompany) input.readObject();
			MemberIdServer.retrieve(input);
			return washerCompany;

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
			output.writeObject(washerCompany);
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
		return customerList + "\n" + washerList;

	}

}
