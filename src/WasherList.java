import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * The collection class for the Washer objects.
 * 
 * @author Stephen Thomas
 *
 */
public class WasherList implements Serializable {
	private ArrayList<Washer> washers = new ArrayList<Washer>();
	private static WasherList washerList;

	/**
	 * Private constructor for singleton pattern
	 * 
	 */
	private WasherList() {
	}

	/**
	 * Supports the singleton pattern
	 * 
	 * @return the singleton object
	 */
	public static WasherList instance() {
		if (washerList == null) {
			return (washerList = new WasherList());
		} else {
			return washerList;
		}
	}

	/**
	 * Checks whether a washer with a given brand and model exists.
	 * 
	 * @param brand the brand of the washer
	 * @param model the model of washer
	 * @return the Washer object in the list iff the washer exists
	 * 
	 */
	public Washer search(String brand, String model) {
		for (Iterator iterator = washers.iterator(); iterator.hasNext();) {
			Washer washer = (Washer) iterator.next();
			if (washer.getBrand().equals(brand) && washer.getModel().equals(model)) { // may need to fix
				return washer;
			}
		}
		return null;
	}

	public char[] getWasher() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Returns an iterator to all books.
	 * 
	 * @return iterator to the collection
	 */
	public Iterator getWasherList() {
		return washers.iterator();
	}

	/**
	 * Inserts a washer into the collection
	 * 
	 * @param washer the washer to be inserted
	 * @return true if the washer could be inserted. Currently always true.
	 */
	public boolean insertWasher(Washer washer) {
		washers.add(washer);
		return true;
	}

	/**
	 * String form of the collection
	 * 
	 */
	public String toString() {
		return washers.toString();
	}

}
