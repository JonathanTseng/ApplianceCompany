import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * The collection class for Customer objects
 * 
 * @author Stephen Thomas
 *
 */
public class CustomerList implements Serializable {
	// do I need the serialVersionUID?
	private ArrayList<Customer> customers = new ArrayList<Customer>();
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
	 * @param customerId the id of the customer
	 * @return true iff customer exists
	 */
	public Customer search(String customerId) {
		for (Iterator iterator = customers.iterator(); iterator.hasNext();) {
			Customer customer = (Customer) iterator.next();
			if (customer.getId().equals(customerId)) {
				return customer;
			}
		}
		return null;
	}

	public char[] getCustomer() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean insertCustomer(Customer member) {
		customers.add(member);
		return false;
	}

	public Iterator getCustomerList() {
		// TODO Auto-generated method stub
		return null;
	}

}
