import java.io.Serializable;

/**
 * Represents a single furnace.
 * 
 * @author Stephen Thomas, Jonathan Tseng, Jose Morales
 *
 */
public class Furnace extends ApplianceItem implements Serializable, Matchable<String> {
	private static final long serialVersionUID = 1L;
	private int maximumHeatingOutput;

	/**
	 * Creates a furnace with the given brand, model, price, and maximum heating
	 * output.
	 * 
	 * @param brand
	 *            furnace brand
	 * @param model
	 *            furnace model
	 * @param price
	 *            furnace price
	 * @param maximumHeatingOutput
	 *            furnace maximum heating output in BTU
	 */
	public Furnace(String brand, String model, double price, int maximumHeatingOutput) {
		super(brand, model, price);
		this.maximumHeatingOutput = maximumHeatingOutput;
	}

	/**
	 * Performs a purchase of an appliance.
	 * 
	 * @param purchaseQuantity
	 *            the requested quantity to be purchased
	 * @param customer
	 *            the customer who is purchasing
	 * @param appliance
	 *            the appliance item to be purchased
	 * @return the result of integer of the purchase
	 */
	@Override
	public int purchase(int purchaseQuantity, Customer customer, ApplianceItem appliance) {
		int result = ApplianceCompany.OPERATION_FAILED;
		int quantity = super.getQuantity();
		while (purchaseQuantity > 0) {
			if (quantity > 0) {
				super.decrementQuantity();
				quantity--;
				ApplianceCompany.instance().setTotalSales(super.getPrice());
				result = ApplianceCompany.OPERATION_COMPLETED;
			} else {
				System.out.println("Insufficient quantity of furnaces to complete purchase.");
			}
			purchaseQuantity--;
		}
		return result;
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
	 * @param maximumHeatingOutput
	 *            furnace maximum heating output in BTU
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
		return "Furnace: " + super.toString() + "\tMaxiumum Heating Output (BTU) " + maximumHeatingOutput;
	}

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
