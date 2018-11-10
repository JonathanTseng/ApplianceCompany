import java.io.Serializable;

/**
 * Represents a back order on an appliance from a customer
 * 
 * @author Jonathan Tseng, Stephen Thomas, Jose Morales and Xeng Vang
 *
 */
public class BackOrder implements Serializable {
	private ApplianceItem appliance;
	private Customer customer;
	private int quantity;

	/**
	 * The customer, appliance, and back order quantity are stored.
	 * 
	 * @param customer          name of customer
	 * @param appliance         the appliance item
	 * @param backOrderQuantity quantity of appliances in back order
	 */
	public BackOrder(Customer customer, ApplianceItem appliance, int backOrderQuantity) {
		this.customer = customer;
		this.appliance = appliance;
		quantity = backOrderQuantity;
	}

	/**
	 * The customer and appliance are stored.
	 *
	 * @param customer  name of customer
	 * @param appliance the appliance item
	 */
	public BackOrder(Customer customer, ApplianceItem appliance) {
		this.customer = customer;
		this.appliance = appliance;
	}

	/**
	 * Getter for Customer
	 * 
	 * @return Customer who has the back order
	 */
	public Customer getCustomer() {
		return customer;
	}

	/**
	 * Getter for the the appliance item item
	 * 
	 * @return appliance with back ordered
	 */
	public ApplianceItem getApplianceItem() {
		return appliance;
	}

	/**
	 * Setting quantity of back order
	 * 
	 * @param quantity number of appliance items put in back order
	 */
	public void setBackOrderQuantity(int quantity) {
		this.quantity = quantity;
	}

	/**
	 * Getter for the number of appliance items
	 * 
	 * @return number of appliance items
	 */
	public int getQuantity() {
		return quantity;
	}

}
