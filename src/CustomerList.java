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

	// not sure if this method is required considering the super class already has
	// search method
	/**
	 * Checks whether a customer with a given customer id exists.
	 * 
	 * @param customerId the id of the customer
	 * @return the Customer object within the list iff the customer exists
	 */
	@Override
	public Customer search(String customerId, String blankId) {
		return super.search(customerId, blankId);
	}
	/*
	 * prior to extending the ItemList class search method public Customer
	 * search(String customerId) { for (Iterator iterator = customers.iterator();
	 * iterator.hasNext();) { Customer customer = (Customer) iterator.next(); if
	 * (customer.getId().equals(customerId)) { return customer; } } return null; }
	 */

	/**
	 * Insert the customer to the list.
	 * 
	 * @param customer
	 * @return true if successful
	 */
	public boolean insertMember(Customer customer) {
		return super.add(customer);
	}
	/*
	 * prior to extending the ItemList class insert method public boolean
	 * insertCustomer(Customer customer) { customers.add(customer); return true; }
	 */

	// I believe the following two methods are required for serialization

	/**
	 * Supports serialization
	 * 
	 * @param output the stream to be written to
	 */
	private void writeObject(java.io.ObjectOutputStream output) throws IOException {
		output.defaultWriteObject();
		output.writeObject(customerList);
	}

	/**
	 * Supports serialization
	 * 
	 * @param input the stream to be read from
	 */
	private void readObject(java.io.ObjectInputStream input) throws IOException, ClassNotFoundException {
		input.defaultReadObject();
		if (customerList == null) {
			customerList = (CustomerList) input.readObject();
		} else {
			input.readObject();
		}
	}

	/*
	 * method no longer necessary /** Returns an iterator to all customers
	 * 
	 * @return iterator to the customer list
	 *
	 * public Iterator getCustomerList() { if (customers.isEmpty()) { return null; }
	 * return customers.iterator(); }
	 */

}
