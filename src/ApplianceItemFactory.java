
/**
 * Creates different types of ApplianceItem objects. When a new ApplianceItem is
 * introduced, the constructor for that class must be invoked from here. This is
 * a singleton.
 * 
 * @author Stephen Thomas, Jonathan Tseng, Jose Morales
 *
 */
public class ApplianceItemFactory {
	private static ApplianceItemFactory factory;

	/**
	 * Private for singleton
	 */
	private ApplianceItemFactory() {
	}

	/**
	 * Returns the only instance of the class.
	 * 
	 * @return the instance
	 */
	public static ApplianceItemFactory instance() {
		if (factory == null) {
			factory = new ApplianceItemFactory();
		}
		return factory;
	}

	/**
	 * Creating an appliance which can be a washer, dryer, range, dishwasher,
	 * refrigerator or a furnace.
	 * 
	 * @param type
	 * @param model
	 * @param brand
	 * @param price
	 * @param repairPlanPrice
	 * @param capacity
	 * @param maximumHeatingOutput
	 * @return
	 */
	public ApplianceItem createApplianceItem(int type, String model, String brand, double price, double repairPlanPrice,
			int capacity, int maximumHeatingOutput) {
		switch (type) {
		case ApplianceCompany.CLOTHES_WASHER:
			return new ClothesWasher(brand, model, price, repairPlanPrice);
		case ApplianceCompany.CLOTHES_DRYER:
			return new ClothesDryer(brand, model, price, repairPlanPrice);
		case ApplianceCompany.KITCHEN_RANGE:
			return new KitchenRange(brand, model, price);
		case ApplianceCompany.DISHWASHER:
			return new Dishwasher(brand, model, price);
		case ApplianceCompany.REFRIGERATOR:
			return new Refrigerator(brand, model, price, capacity);
		case ApplianceCompany.FURNACE:
			return new Furnace(brand, model, price, maximumHeatingOutput);
		default:
			return null;
		}
	}

}
