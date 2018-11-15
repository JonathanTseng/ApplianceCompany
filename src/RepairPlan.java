import java.io.Serializable;

/**
 * Represents a Repair Plan for a clothes washer or dryer appliance.
 * 
 * @author Stephen Thomas, Jonathan Tseng, Jose Morales
 *
 */
public class RepairPlan implements Matchable<String>, Serializable {
	private Customer customer;
	private ApplianceItem appliance;
	private double price;

	/**
	 * Creates a repair plan with the given customer and appliance.
	 * 
	 * @param customer
	 * @param appliance
	 */
	public RepairPlan(Customer customer, ApplianceItem appliance) {
		this.setCustomer(customer);
		this.setAppliance(appliance);
		this.setPrice(appliance.getRepairPlanPrice());
	}

	/**
	 * Getter for the customer
	 * 
	 * @return
	 */
	public Customer getCustomer() {
		return customer;
	}

	/**
	 * Setter for the customer
	 * 
	 * @param customer
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	/**
	 * Getter for the appliance
	 * 
	 * @return appliance
	 */
	public ApplianceItem getAppliance() {
		return appliance;
	}

	/**
	 * Setter for the appliance
	 * 
	 * @param appliance
	 */
	public void setAppliance(ApplianceItem appliance) {
		this.appliance = appliance;
	}

	/**
	 * Getter for the price
	 * 
	 * @return price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * Setter for the price
	 * 
	 * @param price
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * Returns true if and only if the supplied model brand, and customerId are the
	 * same as the appliance and customer object fields.
	 */
	@Override
	public boolean matches(String brand, String model, String customerId) {
		String thisBrand = appliance.getBrand();
		String thisModel = appliance.getModel();
		String thisCustomerId = customer.getId();
		return (thisModel.equals(model) && thisBrand.equals(brand) && thisCustomerId.equals(customerId));
	}

}
