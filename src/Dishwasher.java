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
	 * @param brand dishwasher brand
	 * @param model dishwasher model
	 * @param price dishwasher price
	 */
	public Dishwasher(String brand, String model, int price) {
		super(brand, model, price);
	}

	/**
	 * String form of dishwasher
	 * 
	 */
	@Override
	public String toString() {
		return "Dishwasher Information: \n" + super.toString();
	}

	// I think I need to implement something for the visitor pattern

}
