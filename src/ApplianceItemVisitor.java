/**
 * This is the standard Visitor interface for the visitor pattern.
 * 
 * @author Jonathan Tseng
 *
 */
public interface ApplianceItemVisitor {
	/**
	 * This is the method for catching all ApplianceItems objects that may be added
	 * in the future.
	 * 
	 * @param item
	 *            the ApplianceItem to be processed
	 */
	public void visit(ApplianceItem item);

	/**
	 * This method processes Refrigerator objects
	 * 
	 * @param refrigerator
	 *            the refrigerator to be processed
	 */
	public void visit(Refrigerator refrigerator);

	/**
	 * The method processes ClothesDryer objects
	 * 
	 * @param clothesDryer
	 *            the ClothesDryer to be processed
	 */
	public void visit(ClothesDryer clothesDryer);

	/**
	 * The method processes ClothesWasher objects
	 * 
	 * @param clothesWasher
	 *            the clothes washer to be processed
	 */
	public void visit(ClothesWasher clothesWasher);

	/**
	 * The method processes Furnace objects
	 * 
	 * @param furnance
	 *            the furnace to be processed
	 */
	public void visit(Furnace furnance);

	public void visit(KitchenRange kitchenRange);

	public void visit(Dishwasher dishWasher);

}