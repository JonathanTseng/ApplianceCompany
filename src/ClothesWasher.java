import java.io.Serializable;

/**
 * Represents a single clothes washer.
 * 
 * @author Stephen Thomas
 *
 */
public class ClothesWasher extends ApplianceItem implements Serializable, Matchable<String> {
	private static final long serialVersionUID = 1L;
	private double repairPlanPrice;

	/**
	 * Creates a clothes washer with the given brand, model, price, and repair plan
	 * price.
	 * 
	 * @param brand           washer brand
	 * @param model           washer model
	 * @param price           washer price
	 * @param repairPlanPrice washer repair plan price
	 */
	public ClothesWasher(String brand, String model, double price, double repairPlanPrice) {
		super(brand, model, price);
		this.repairPlanPrice = repairPlanPrice;
	}

	/**
	 * Getter for the repair plan price
	 * 
	 * @return the price of a repair plan for the washer
	 */
	public double getRepairPlanPrice() {
		return repairPlanPrice;
	}

	/**
	 * Setter for the repair plan price
	 * 
	 * @param repairPlanPrice washer repair plan price
	 */
	public void setRepairPlanPrice(double repairPlanPrice) {
		this.repairPlanPrice = repairPlanPrice;
	}

	/**
	 * String form of clothes washer
	 * 
	 */
	@Override
	public String toString() {
		return "Clothes Washer Information: \n" + super.toString() + "\tRepair Plan Price " + repairPlanPrice;
	}

	// I think I need to implement something for the visitor pattern
}
