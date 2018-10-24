import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Represents a back order on a washer from a customer
 * 
 * @author Jonathan
 *
 */
public class BackOrder {

	private Washer washer;
	private Customer customer;
	private Calendar date;
	private int quantity;

	/**
	 * The customer and washer are stored.
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
	 * **** Jonathan, I'm not sure we need this. *****
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
	 * *** Do we need this????? ********
	 * 
	 * The member and book are stored. The date is computed by adding the duration
	 * days to the current date.
	 * 
	 * @param member
	 *            who places the hold
	 * @param book
	 *            the book on which hold is placed
	 * @param duration
	 *            for how long the hold is valid
	 * @return
	 */
	public void backOrder(Customer customer, Washer washer, int duration) {
		this.washer = washer;
		this.customer = customer;
		date = new GregorianCalendar();
		date.add(Calendar.DATE, duration);
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

	// Get rid of this method
	/**
	 * Getter for date
	 * 
	 * @return date until which the hold is valid
	 */
	public Calendar getDate() {
		return date;
	}

	/**
	 * Checks whether the hold has become invalid because the last date has passed
	 * 
	 * @return true iff the hold is valid
	 */
	public boolean isValid() {
		return (System.currentTimeMillis() < date.getTimeInMillis());
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
