import java.util.Calendar;
import java.util.GregorianCalendar;

public class BackOrder {

	private Washer washer;
	private Customer customer;
	private Calendar date;
	private int quantity;

	public BackOrder(Customer customer, Washer washer, int backOrderQuantity) {
		this.customer = customer;
		this.washer = washer;
		this.setBackOrderQuantity(backOrderQuantity);
	}

	public BackOrder(Customer customer, Washer washer) {
		this.customer = customer;
		this.washer = washer;
	}

	/**
	 * The member and book are stored. The date is computed by adding the duration
	 * days to the current date.
	 * 
	 * @param member   who places the hold
	 * @param book     the book on which hold is placed
	 * @param duration for how long the hold is valid
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

	public void setBackOrderQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getQuantity() {

		return quantity;
	}

}
