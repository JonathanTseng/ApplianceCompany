import java.io.Serializable;

/**
 * Represents a Repair Plan for a clothes washer or dryer appliance.
 * 
 * @author Stephen Thomas
 *
 */
public class RepairPlan implements Matchable<String>, Serializable {
	private Customer customer;
	private ApplianceItem appliance;
	private double price;

	public RepairPlan(Customer customer, ApplianceItem appliance) {
		this.setCustomer(customer);
		this.setAppliance(appliance);
		this.setPrice(appliance.getPrice());
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public ApplianceItem getAppliance() {
		return appliance;
	}

	public void setAppliance(ApplianceItem appliance) {
		this.appliance = appliance;
	}

	public double getPrice() {
		return price;
	}

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
