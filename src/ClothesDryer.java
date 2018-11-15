import java.io.Serializable;
import java.text.NumberFormat;

/**
 * Represents a single clothes dryer.
 * 
 * @author Stephen Thomas, Jose Morales, Jonathan Tseng
 *
 */
public class ClothesDryer extends ApplianceItem implements Serializable, Matchable<String> {
	private static final long serialVersionUID = 1L;
	private NumberFormat moneyFormat = NumberFormat.getCurrencyInstance();
	private double repairPlanPrice;

	/**
	 * Creates a clothes dryer with the given brand, model, price, and repair plan
	 * price.
	 * 
	 * @param brand
	 *            dryer brand
	 * @param model
	 *            dryer model
	 * @param price
	 *            dryer price
	 * @param repairPlanPrice
	 *            repair plan price
	 */
	public ClothesDryer(String brand, String model, double price, double repairPlanPrice) {
		super(brand, model, price);
		this.repairPlanPrice = repairPlanPrice;
	}

	/**
	 * Getter for the repair plan price
	 * 
	 * @return the price of a repair plan for the dryer
	 */
	public double getRepairPlanPrice() {
		return repairPlanPrice;
	}

	/**
	 * Setter for the repair plan price
	 * 
	 * @param repairPlanPrice
	 *            dryer repair plan price
	 */
	public void setRepairPlanPrice(double repairPlanPrice) {
		this.repairPlanPrice = repairPlanPrice;
	}

	/**
	 * String form of clothes dryer
	 * 
	 */
	@Override
	public String toString() {
		return "Clothes Dryer: " + super.toString() + "\tRepair Plan Price " + moneyFormat.format(repairPlanPrice);
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
