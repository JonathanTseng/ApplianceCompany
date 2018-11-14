import java.io.Serializable;

/**
 * Represents a single dishwasher.
 * 
 * @author Stephen Thomas
 *
 */
public class Dishwasher extends ApplianceItem implements Serializable, Matchable<String> {
	private static final long serialVersionUID = 1L;

	/**
	 * Creates a dishwasher with the given brand, model, and price.
	 * 
	 * @param brand
	 *            dishwasher brand
	 * @param model
	 *            dishwasher model
	 * @param price
	 *            dishwasher price
	 */
	public Dishwasher(String brand, String model, double price) {
		super(brand, model, price);
	}

	/**
	 * String form of dishwasher
	 * 
	 */
	@Override
	public String toString() {
		return "Dishwasher: " + super.toString();
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
