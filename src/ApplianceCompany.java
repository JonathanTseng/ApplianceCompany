import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Iterator;

/**
 * This class implements Serializable. It contains the methods that are used to
 * organize business processes in the ApplianceCompany project.
 * 
 * @author Jose Morales, Jonathan Tseng, Stephen Thomas, and Xeng Vang
 *
 */
public class ApplianceCompany implements Serializable {

	private static final long serialVersionUID = 1L;
	private NumberFormat moneyFormat = NumberFormat.getCurrencyInstance();
	public static final int APPLIANCE_NOT_FOUND = 1;
	public static final int OPERATION_FAILED = 2;
	public static final int OPERATION_COMPLETED = 3;
	public static final int BACKORDER_PLACED = 4;
	public static final int CUSTOMER_NOT_FOUND = 5;
	public static final int REPAIR_PLAN_NOT_FOUND = 6;
	public static final int ALREADY_EXISTS = 7;

	public static final int CLOTHES_WASHER = 1;
	public static final int CLOTHES_DRYER = 2;
	public static final int KITCHEN_RANGE = 3;
	public static final int DISHWASHER = 4;
	public static final int REFRIGERATOR = 5;
	public static final int FURNACE = 6;
	private CustomerList customerList;
	private ApplianceList applianceList;
	private RepairPlanList repairPlanList;
	private static ApplianceCompany applianceCompany;
	private double totalApplianceSales = 0;
	private double totalRepairPlanSales = 0;

	/**
	 * Private for the singleton pattern, it creates the customer list, the appliance
	 * list collection, and the repair plan list objects.
	 */
	private ApplianceCompany() {
		customerList = CustomerList.instance();
		applianceList = ApplianceList.instance();
		repairPlanList = RepairPlanList.instance();
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
	 *            appliance brand
	 * @param model
	 *            appliance model
	 * @param quantity
	 *            quantity of appliances
	 * @return the result of the operation
	 */
	public int addInventory(String brand, String model, int quantity) {

		int result = OPERATION_FAILED;
		int quantityToAdd = quantity;

		ApplianceItem appliance = applianceList.search(brand, model, "");
		if (appliance == null) {
			result = APPLIANCE_NOT_FOUND;
		} else {

			while (quantityToAdd > 0) {
				appliance.addQuantity(1);
				if (appliance.hasBackOrder()) {
					BackOrder backOrder = appliance.getNextBackOrder();
					purchaseAppliance(backOrder.getCustomer().getId(), backOrder.getApplianceItem().getBrand(),
							backOrder.getApplianceItem().getModel(), 1);
				}
				quantityToAdd--;
			}

			BackOrder backOrder = appliance.getNextBackOrder();
			if (backOrder == null) {
				result = OPERATION_COMPLETED;
			} else {
				purchaseAppliance(backOrder.getCustomer().getName(), backOrder.getApplianceItem().getBrand(),
						backOrder.getApplianceItem().getModel(), backOrder.getApplianceItem().getQuantity());
				result = OPERATION_COMPLETED;
			}

		}
		return result;
	}

	/**
	 * Organizes the operations for adding an appliance item
	 * 
	 * @param type
	 *            the type of appliance that is to be added
	 * @param model
	 *            the model of the appliance
	 * @param brand
	 *            the brand of the appliance
	 * @param price
	 *            the price of the appliance
	 * @param repairPlanPrice
	 *            the repair plan price if applicable
	 * @param capacity
	 *            the appliance capacity if applicable
	 * @param maximumHeatingOutput
	 *            the maximum heating output if applicable
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

	/**
	 * Getter for the total appliance sales
	 * 
	 * @return the amount of total appliance sales
	 */
	public double getTotalApplianceSales() {
		return totalApplianceSales;
	}

	/**
	 * Setter for the total appliance sales
	 * 
	 * @param additionalSaleTotal
	 *            the amount to be added to the total appliance sales
	 */
	public void setTotalSales(double additionalSaleTotal) {
		totalApplianceSales += additionalSaleTotal;
	}

	/**
	 * Getter for the total repair plan sales
	 * 
	 * @return the amount of total repair plan sales
	 */
	public double getTotalRepairPlanSales() {
		return totalRepairPlanSales;
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
	 * Organizes the operations for displaying the appliances
	 * 
	 * @param type
	 *            the type of appliance requested
	 * @return a String of the requested list of appliances or the appropriate
	 *         result
	 */
	public String listAppliances(int type) {
		Iterator applianceIterator = applianceList.iterator();
		String applianceList = "";
		if (applianceIterator.hasNext() == false) {
			applianceList = "There are no appliances that exist.\n";
		} else {
			applianceList = "Here is the list of your requested appliances:\n";
			while (applianceIterator.hasNext()) {
				ApplianceItem appliance = (ApplianceItem) applianceIterator.next();
				switch (type) {
				case (0):
					applianceList += appliance.toString() + "\n";
					break;
				case (CLOTHES_WASHER):
					if (appliance instanceof ClothesWasher) {
						applianceList += appliance.toString() + "\n";
					}
					break;
				case (CLOTHES_DRYER):
					if (appliance instanceof ClothesDryer) {
						applianceList += appliance.toString() + "\n";
					}
					break;
				case (KITCHEN_RANGE):
					if (appliance instanceof KitchenRange) {
						applianceList += appliance.toString() + "\n";
					}
					break;
				case (DISHWASHER):
					if (appliance instanceof Dishwasher) {
						applianceList += appliance.toString() + "\n";
					}
					break;
				case (REFRIGERATOR):
					if (appliance instanceof Refrigerator) {
						applianceList += appliance.toString() + "\n";
					}
					break;
				case (FURNACE):
					if (appliance instanceof Furnace) {
						applianceList += appliance.toString() + "\n";
					}
					break;

				}
			}
		}
		return applianceList;
	}

	/**
	 * Organizes the purchase of an appliance
	 * 
	 * @param customerId
	 *            id of customer
	 * @param brand
	 *            appliance brand
	 * @param model
	 *            appliance model
	 * @param quantity
	 *            quantity to be purchased
	 * @return a code that represents the outcome of the operation
	 */
	public int purchaseAppliance(String customerId, String brand, String model, int quantity) {
		int result = OPERATION_FAILED;
		ApplianceItem appliance = applianceList.search(brand, model, "");
		Customer customer = customerList.search(customerId, "", "");

		if (appliance == null) {
			return (APPLIANCE_NOT_FOUND);
		}

		if (customer == null) {
			return (CUSTOMER_NOT_FOUND);
		}

		result = appliance.purchase(quantity, customer, appliance);

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
		return customerList.search(customerId, "", "");
	}

	/**
	 * Displays the list of all back orders
	 * 
	 * @param visitor
	 */
	public void listBackOrders(ApplianceItemVisitor visitor) {
		for (Iterator<ApplianceItem> itemIterator = applianceList.iterator(); itemIterator.hasNext();) {
			itemIterator.next().accept(visitor);
		}

	}

	/**
	 * This method will list all users currently enrolled in a repair plan.
	 * 
	 * @return a String of the users in repair plans or the appropriate result
	 */
	public String listUserRepairPlans() {
		Iterator repairPlansIterator = repairPlanList.iterator();
		String userRepairPlans = "";
		if (repairPlansIterator.hasNext() == false) {
			userRepairPlans = "No customers in repair plans.\n";
		} else {
			userRepairPlans = "Here is a list of customers in repair plans:\n";
			while (repairPlansIterator.hasNext()) {
				RepairPlan repairPlan = (RepairPlan) repairPlansIterator.next();
				Customer customer = repairPlan.getCustomer();
				ApplianceItem appliance = repairPlan.getAppliance();
				userRepairPlans += customer.toString() + "\tAppliance Account Balance: "
						+ moneyFormat.format(customer.getApplianceAccount()) + "\tRepair Plan Account Balance: "
						+ moneyFormat.format(customer.getRepairPlanAccount()) + "\tAppliance Brand: "
						+ appliance.getBrand() + " Model: " + appliance.getModel() + "\n";
			}
		}
		return userRepairPlans;
	}

	/**
	 * Bills all of the customers in a repair plan.
	 * 
	 * @return a code that represents the outcome of the operation
	 */
	public int billRepairPlans() {
		int result = OPERATION_FAILED;
		Iterator repairPlanIterator = repairPlanList.iterator();
		if (repairPlanIterator == null) {
			result = REPAIR_PLAN_NOT_FOUND;
		} else {
			while (repairPlanIterator.hasNext()) {
				RepairPlan repairPlan = (RepairPlan) repairPlanIterator.next();
				Customer customer = repairPlan.getCustomer();
				totalRepairPlanSales += repairPlan.getPrice();
				customer.chargeRepairPlanAccount(repairPlan.getPrice());
			}
			result = OPERATION_COMPLETED;
		}
		return result;
	}

	/**
	 * Enrolls customer in a repair plan.
	 * 
	 * @param customer
	 *            the customer that is to be enrolled in the plan
	 * @param appliance
	 *            the appliance corresponding to the repair plan
	 * @return a code that represents the outcome of the operation
	 */
	public int enrollInRepairPlan(String customerId, String brand, String model) {
		ApplianceItem appliance = applianceList.search(brand, model, "");
		Customer customer = customerList.search(customerId, "", "");
		RepairPlan repairPlanCheck = repairPlanList.search(brand, model, customerId);
		if (appliance == null) {
			return APPLIANCE_NOT_FOUND;
		}
		if (customer == null) {
			return CUSTOMER_NOT_FOUND;
		}
		if (repairPlanCheck != null) {
			return ALREADY_EXISTS;
		}
		if (appliance instanceof ClothesWasher || appliance instanceof ClothesDryer) {
			RepairPlan repairPlan = new RepairPlan(customer, appliance);
			if (repairPlanList.insertRepairPlan(repairPlan)) {
				customer.setHasRepairPlan(true);
				return OPERATION_COMPLETED;
			}
		}
		return OPERATION_FAILED;
	}

	/**
	 * Removes a customer from a repair plan
	 * 
	 * @param brand
	 *            the appliance brand
	 * @param model
	 *            the appliance model
	 * @param customerId
	 *            the id of the customer tied to the repair plan
	 * @return a code that represents the outcome of the operation
	 */
	public int withdrawRepairPlan(String brand, String model, String customerId) {
		int result = OPERATION_FAILED;
		RepairPlan repairPlan = repairPlanList.search(brand, model, customerId);
		if (repairPlan == null) {
			return REPAIR_PLAN_NOT_FOUND;
		}
		if (repairPlanList.removeRepairPlan(brand, model, customerId)) {
			Customer customer = customerList.search(customerId, "", "");
			customer.setHasRepairPlan(false);
			result = OPERATION_COMPLETED;
		}
		return result;
	}

	/**
	 * This method will try and deserialize a saved file of Appliance Company from a
	 * disk
	 * 
	 * @return a ApplianceCompany object
	 */

	public static ApplianceCompany retrieve() {

		try {
			FileInputStream file = new FileInputStream("ApplianceCompanyData");
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
	 * Will serialize the ApplianceCompany object.
	 * 
	 * @return true if file could be saved somewhere on disk
	 * @return false if it can't be saved to disk
	 */
	public static boolean save() {

		try {
			FileOutputStream file = new FileOutputStream("ApplianceCompanyData");
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
	 * String of the ApplianceCompany
	 * 
	 */
	@Override
	public String toString() {
		return customerList + "\n" + applianceList;

	}

}
