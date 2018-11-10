import java.io.Serializable;
import java.text.NumberFormat;
import java.util.LinkedList;
import java.util.Queue;

/**
 * This class is the super class for all items that can be loaned out by the
 * library. It implements some of the functionality needed to issue, return,
 * remove, and renew a single item.
 * 
 * @author Stephen Thomas
 *
 */
public class ApplianceItem implements Matchable<String>, Serializable {
	private static final long serialVersionUID = 1L;
	private NumberFormat moneyFormat = NumberFormat.getCurrencyInstance();
	private double price;
	private int quantity = 0;
	private String brand;
	private String model;
	public Queue<BackOrder> backOrderList = new LinkedList<BackOrder>();

	/**
	 * Creates an appliance with the given brand, model, and price.
	 * 
	 * @param brand appliance brand
	 * @param model appliance model
	 * @param price appliance price
	 */
	public ApplianceItem(String brand, String model, double price) {
		this.brand = brand;
		this.model = model;
		this.price = price;
	}

	/**
	 * Represents a purchase of an appliance. Thus, the quantity of the appliance is
	 * decremented.
	 * 
	 */
	public void purchase() {
		quantity--;
	}

	/**
	 * Adds the given additional quantity to the quantity of the appliance
	 * 
	 * @param additionalQuantity the quantity of the appliance being added
	 */
	public void addQuantity(int additionalQuantity) {
		quantity += additionalQuantity;
	}

	/**
	 * Adds on more back order to the appliance
	 * 
	 * @param backOrder the new back order on the appliance
	 */
	public void placeBackOrder(BackOrder backOrder) {
		backOrderList.add(backOrder);
	}

	/**
	 * Returns a valid back order
	 * 
	 * @return the next valid back order
	 */
	public BackOrder getNextBackOrder() {
		if (backOrderList.peek() != null) {
			return backOrderList.poll();
		}
		return null;
	}

	/**
	 * Checks whether there is a back order on this appliance
	 * 
	 * @return true iff there is a back order
	 */
	public boolean hasBackOrder() {
		if (backOrderList.peek() != null) {
			return true;
		}
		return false;
	}

	/**
	 * Getter for price
	 * 
	 * @return price of appliance
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * Getter of quantity
	 * 
	 * @return quantity of appliance
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * Getter of brand
	 * 
	 * @return brand of appliance
	 */
	public String getBrand() {
		return brand;
	}

	/**
	 * Getter of model
	 * 
	 * @return model of appliance
	 */
	public String getModel() {
		return model;
	}

	/**
	 * Setter for price
	 * 
	 * @param price appliance's new price
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * Setter for quantity
	 * 
	 * @param quantity appliance's new quantity
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	/**
	 * Setter for brand
	 * 
	 * @param brand appliance's new brand
	 */
	public void setBrand(String brand) {
		this.brand = brand;
	}

	/**
	 * Setter for model
	 * 
	 * @param model appliance's new model
	 */
	public void setModel(String model) {
		this.model = model;
	}

	// needs to be updated
	/**
	 * String form of washer
	 * 
	 */
	public String toString() {
		return "Brand: " + brand + "\tModel: " + model + "\tPrice: " + moneyFormat.format(price) + "\tQuantity "
				+ quantity;
	}

	/**
	 * Returns true if and only if the supplied model and brand are the same as the
	 * id of the model and brand of the item.
	 */
	@Override
	public boolean matches(String model, String brand) {
		return (this.model.equals(model) && this.brand.equals(brand));
	}

}
