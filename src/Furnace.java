import java.io.Serializable;

/**
 * Represents a single furnace.
 * 
 * @author Stephen Thomas
 *
 */
public class Furnace extends ApplianceItem implements Serializable, Matchable<String> {
	private static final long serialVersionUID = 1L;
	private int maximumHeatingOutput;

	/**
	 * Creates a furnace with the given brand, model, price, and maximum heating
	 * output.
	 * 
	 * @param brand                furnace brand
	 * @param model                furnace model
	 * @param price                furnace price
	 * @param maximumHeatingOutput furnace maximum heating output in BTU
	 */
	public Furnace(String brand, String model, int price, int maximumHeatingOutput) {
		super(brand, model, price);
		this.maximumHeatingOutput = maximumHeatingOutput;
	}

	/**
	 * Getter for the maximum heating output
	 * 
	 * @return the maximum heating output of the furnace in BTU
	 */
	public int getMaximumHeatingOutput() {
		return maximumHeatingOutput;
	}

	/**
	 * Setter for the furnace maximum heating output
	 * 
	 * @param maximumHeatingOutput furnace maximum heating output in BTU
	 */
	public void setMaximumHeatingOutput(int maximumHeatingOutput) {
		this.maximumHeatingOutput = maximumHeatingOutput;
	}

	/**
	 * String form of furnace
	 * 
	 */
	@Override
	public String toString() {
		return "Furnace Information: \n" + super.toString();
	}

	// I think I need to implement something for the visitor pattern

}
