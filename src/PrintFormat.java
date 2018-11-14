/**
 * 
 * @author Jose
 *
 */
public class PrintFormat implements ApplianceItemVisitor {
	private BackOrder backOrderQueue;
	private static PrintFormat visitor;

	/**
	 * The constructor is for the singleton pattern
	 */
	private PrintFormat() {
	}

	/**
	 * Returns the only instance of the class
	 * 
	 * @return the instance of the class
	 */
	public static PrintFormat instance() {
		if (visitor == null) {
			visitor = new PrintFormat();
		}
		return visitor;
	}

	/**
	 * This is not a fully implemented method. It essentially prints the object's
	 * string representation.
	 */
	@Override
	public void visit(ApplianceItem item) {
		System.out.println("Print " + item + " formatted");
	}

	@Override
	public void visit(Refrigerator refrigerator) {
		// TODO Auto-generated method stub
		backOrderQueue = refrigerator.getNextBackOrder();
		if (backOrderQueue == null) {
			System.out.println("No back orders for refrigerators!");
		} else {
			System.out.println("Refrigerator brand: " + refrigerator.getBrand() + "\tRefrigerator model: "
					+ refrigerator.getModel());
		}

	}

	@Override
	public void visit(ClothesDryer clothesDryer) {
		// TODO Auto-generated method stub
		backOrderQueue = clothesDryer.getNextBackOrder();
		if (backOrderQueue == null) {
			System.out.println("No back orders for dryers!");
		} else {
			System.out.println("Dryer brand: " + clothesDryer.getBrand() + "\tDryer model: " + clothesDryer.getModel());
		}

	}

	@Override
	public void visit(ClothesWasher clothesWasher) {
		// TODO Auto-generated method stub
		backOrderQueue = clothesWasher.getNextBackOrder();
		if (backOrderQueue == null) {
			System.out.println("No back orders for washers!");
		} else {
			System.out.println(
					"Washer brand: " + clothesWasher.getBrand() + "\tWasher model: " + clothesWasher.getModel());
		}
	}

	@Override
	public void visit(Furnace furnance) {
		// TODO Auto-generated method stub
		System.out.println("Furnances can't have back orders.");

	}

	@Override
	public void visit(KitchenRange kitchenRange) {
		// TODO Auto-generated method stub
		backOrderQueue = kitchenRange.getNextBackOrder();
		if (backOrderQueue == null) {
			System.out.println("No back orders for kitchen ranges");

		} else {
			System.out.println("Kitchen range brand: " + kitchenRange.getBrand() + "\tKitchen range model "
					+ kitchenRange.getModel());
		}

	}

	@Override
	public void visit(Dishwasher dishWasher) {
		// TODO Auto-generated method stub
		backOrderQueue = dishWasher.getNextBackOrder();
		if (backOrderQueue == null) {
			System.out.println("No back orders for dish washers!");
		} else {
			System.out.println(
					"Dishwasher brand: " + dishWasher.getBrand() + "\tDishwasher model: " + dishWasher.getModel());
		}

	}

}
