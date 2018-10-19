import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
//test comment

public class CustomerList implements Serializable {
	
	private ArrayList<Customer> customers = new ArrayList<Customer>();

	public static CustomerList instance() {
		// TODO Auto-generated method stub
		return null;
	}

	public Customer search(String customerId) {
		// TODO Auto-generated method stub
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
