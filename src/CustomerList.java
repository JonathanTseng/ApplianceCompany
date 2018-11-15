import java.io.IOException;

/**
 * The collection class for Customer objects
 * 
 * @author Stephen Thomas, Jose Morales, Jonathan Tseng, and Xeng Vang
 *
 */
public class CustomerList extends ItemList<Customer, String> {
	// private static final long serialVersionUID = 1L;
	// private ArrayList<Customer> customers = new ArrayList<Customer>();
	private static CustomerList customerList;

	/**
	 * Private constructor for singleton pattern.
	 */
	private CustomerList() {
	}

	/**
	 * Supports the singleton pattern.
	 * 
	 * @return the singleton object
	 */
	public static CustomerList instance() {
		if (customerList == null) {
			return (customerList = new CustomerList());
		} else {
			return customerList;
		}
	}

	/**
	 * Checks whether a customer with a given customer id exists.
	 * 
	 * @param customerId
	 *            the id of the customer
	 * @return the Customer object within the list iff the customer exists
	 */
	@Override
	public Customer search(String customerId, String blankId1, String blankId2) {
		return super.search(customerId, blankId1, blankId2);
	}

	/**
	 * Insert the customer to the list.
	 * 
	 * @param customer
	 * @return true if successful
	 */
	public boolean insertCustomer(Customer customer) {
		return super.add(customer);
	}

	/**
	 * Supports serialization
	 * 
	 * @param output
	 *            the stream to be written to
	 */
	private void writeObject(java.io.ObjectOutputStream output) throws IOException {
		output.defaultWriteObject();
		output.writeObject(customerList);
	}

	/**
	 * Supports serialization
	 * 
	 * @param input
	 *            the stream to be read from
	 */
	private void readObject(java.io.ObjectInputStream input) throws IOException, ClassNotFoundException {
		input.defaultReadObject();
		if (customerList == null) {
			customerList = (CustomerList) input.readObject();
		} else {
			input.readObject();
		}
	}

}
