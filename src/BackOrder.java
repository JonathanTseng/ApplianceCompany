import java.io.Serializable;

/**
 * Represents a back order on a washer from a customer
 * 
 * @author Jonathan Tseng, Stephen Thomas, Jose Morales and Xeng Vang
 *
 */
public class BackOrder implements Serializable {
	private Washer washer;
	private Customer customer;
	private int quantity;

	/**
	 * The customer, washer, and back order quantity are stored.
	 * 
	 * @param customer
	 *            name of customer
	 * @param washer
	 *            washer brand
	 * @param backOrderQuantity
	 *            quantity of washers in back order
	 */
	public BackOrder(Customer customer, Washer washer, int backOrderQuantity) {
		this.customer = customer;
		this.washer = washer;
		this.setBackOrderQuantity(backOrderQuantity);
	}

	/**
	 * The customer and washer are stored.
	 *
	 * @param customer
	 *            name of customer
	 * @param washer
	 *            washer name
	 */
	public BackOrder(Customer customer, Washer washer) {
		this.customer = customer;
		this.washer = washer;
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
	 * Getter for Washer
	 * 
	 * @return Washer with back ordered
	 */
	public Washer getWasher() {
		return washer;
	}

	/**
	 * Setting quantity of back order
	 * 
	 * @param quantity
	 *            number of washer put in back order
	 */
	public void setBackOrderQuantity(int quantity) {
		this.quantity = quantity;
	}

	/**
	 * Getter for the number of washers
	 * 
	 * @return number of washers
	 */
	public int getQuantity() {

		return quantity;
	}

}
