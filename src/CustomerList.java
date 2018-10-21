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
	 * @return the Customer object within the list iff the customer exists
	 */
	public Customer search(String customerId) {
		for (Iterator iterator = customers.iterator(); iterator.hasNext();) {
			Customer customer = (Customer) iterator.next();
			if (customer.getId().equals(customerId)) { // may need to change equals method in Customer
				return customer;
			}
		}
		return null;
	}

	// might not need may use toString method instead
	public char[] getCustomer() {
		// TODO Auto-generated method stub
		return null;
	}

	public String toString() {
		return customers.toString(); // test to ensure it is the proper toString
	}

	public boolean insertCustomer(Customer customer) {
		customers.add(customer);
		return true;
	}

	// might not need may use toString method instead
	public Iterator getCustomerList() {
		// TODO Auto-generated method stub
		return null;
	}

}
