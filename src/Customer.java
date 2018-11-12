import java.io.Serializable;

/**
 * Object class for an individual customer of the Washer Company.
 * 
 * @author Stephen Thomas, Jonathan Tseng, Jose Morales and Xeng Vang
 *
 */
public class Customer implements Serializable, Matchable<String> {
	private static final long serialVersionUID = 1L;
	private static final String MEMBER_STRING = "C";
	private String id;
	private String phoneNumber;
	private String name;

	private boolean hasRepairPlan = false;
	private double repairPlanAccount = 0.0;

	/**
	 * Constructor for an individual customer.
	 * 
	 * @param name        name of the customer
	 * @param phoneNumber phone number of the customer
	 */
	public Customer(String name, String phoneNumber) {
		this.name = name;
		this.phoneNumber = phoneNumber;
		setId(MEMBER_STRING + (MemberIdServer.instance()).getId());
	}

	/**
	 * Getter for id
	 * 
	 * @return customer id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Getter for phone number
	 * 
	 * @return customer phone number
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * Getter for name
	 * 
	 * @return customer name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Setter for id
	 * 
	 * @param id customer's new id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Setter for phone number
	 * 
	 * @param phoneNumber customer's new phone number
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * Setter for name
	 * 
	 * @param name customer's new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Getter for hasRepairPlan;
	 * 
	 * @return whether or not the customer has a repair plan
	 */
	public boolean getHasRepairPlan() {
		return hasRepairPlan;
	}

	/**
	 * Setter for has repair plan
	 * 
	 * @param value the true or false value
	 */
	public void setHasRepairPlan(boolean value) {
		hasRepairPlan = value;
	}

	/**
	 * Getter for repair plan account
	 * 
	 * @return the amount in the repair plan account
	 */
	public double getRepairPlanAccount() {
		return repairPlanAccount;
	}

	public void chargeRepairPlanAccount(double billAmount) {
		repairPlanAccount += billAmount;
	}

	/*
	 * Old equals method /** Checks whether the customer is equal to the one with
	 * the given id
	 * 
	 * @param id id of the customer who should be compared
	 * 
	 * @return true iff the customer ids match
	 *
	 * public boolean equals(String id) { return this.id.equals(id); }
	 */

	/**
	 * Checks whether the customer is equal to another Customer
	 * 
	 * @param object id of the member who should be compared
	 * @return true iff the member ids match
	 */
	@Override
	public boolean equals(Object object) {
		Customer customer = (Customer) object;
		return this.matches(customer.getId(), "", "");
	}

	/**
	 * String form of the customer.
	 */
	@Override
	public String toString() {
		return "Customer name: " + name + "\tPhone number: " + phoneNumber + "\tCustomer ID: " + id;
	}

	/**
	 * To implement the Matchable interface
	 * 
	 * @param key the member id
	 */
	@Override
	public boolean matches(String id, String blankKey1, String blankKey2) {
		return this.id.equals(id);
	}

}
