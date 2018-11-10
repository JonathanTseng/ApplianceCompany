import java.io.Serializable;

/**
 * Represents a single refrigerator
 * 
 * @author Stephen Thomas
 *
 */
public class Refrigerator extends ApplianceItem implements Serializable, Matchable<String> {
	private static final long serialVersionUID = 1L;
	private int capacity;

	/**
	 * Creates a refrigerator with the given brand, model, price, and capacity.
	 * 
	 * @param brand    refrigerator brand
	 * @param model    refrigerator model
	 * @param price    refrigerator price
	 * @param capacity refrigerator capacity
	 */
	public Refrigerator(String brand, String model, double price, int capacity) {
		super(brand, model, price);
		this.capacity = capacity;
	}

	/**
	 * Getter for the capacity
	 * 
	 * @return the capacity of the refrigerator in liters
	 */
	public int getCapacity() {
		return capacity;
	}

	/**
	 * Setter for the refrigerator capacity
	 * 
	 * @param capacity refrigerator capacity in liters
	 */
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	/**
	 * String form of refrigerator
	 * 
	 */
	@Override
	public String toString() {
		return "Refrigerator Information: \n" + super.toString();
	}

	// I think I need to implement something for the visitor pattern
	/**
	 * Implements the accept method of the Visitor pattern.
	 * 
	 * @param visitor
	 *            the Visitor that will process the Periodical object
	 */
	@Override
	public void accept(ApplianceItemVisitor visitor) {
		visitor.visit(this);
	}
}
