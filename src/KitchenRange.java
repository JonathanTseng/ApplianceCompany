import java.io.Serializable;

/**
 * Represents a single kitchen range.
 * 
 * @author Stephen Thomas
 *
 */
public class KitchenRange extends ApplianceItem implements Serializable, Matchable<String> {
	private static final long serialVersionUID = 1L;

	/**
	 * Creates a kitchen range with the given brand, model, and price.
	 * 
	 * @param brand kitchen range brand
	 * @param model kitchen range model
	 * @param price kitchen range price
	 */
	public KitchenRange(String brand, String model, int price) {
		super(brand, model, price);
	}

	/**
	 * String form of kitchen range
	 * 
	 */
	@Override
	public String toString() {
		return "Kitchen Range Information: \n" + super.toString();
	}

	// I think I need to implement something for the visitor pattern

}
