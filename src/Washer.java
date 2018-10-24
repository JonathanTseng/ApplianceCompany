import java.io.Serializable;
import java.text.NumberFormat;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Object class for an individual washer.
 * 
 * @author Stephen Thomas
 *
 */
public class Washer implements Serializable {
	private static final long serialVersionUID = 1L;
	private NumberFormat moneyFormat = NumberFormat.getCurrencyInstance();
	private double price;
	private int quantity = 0;
	private String brand;
	private String model;
	public Queue<BackOrder> backOrderList = new LinkedList<BackOrder>();

	/**
	 * Creates a washer with the given brand, model, and price.
	 * 
	 * @param brand
	 *            washer brand
	 * @param model
	 *            washer model
	 * @param price
	 *            washer price
	 */
	public Washer(String brand, String model, double price) {
		this.brand = brand;
		this.model = model;
		this.price = price;
	}

	/**
	 * Represents a purchase of a washer. Thus, the quantity of washers is
	 * decremented.
	 * 
	 */
	public void purchase() {
		// decrement quantity,update total, holds
		quantity--;
	}

	/**
	 * Adds the given additional quantity to the quantity of washers
	 * 
	 * @param additionalQuantity
	 *            the quantity of washers being added
	 */
	public void addQuantity(int additionalQuantity) {
		quantity += additionalQuantity;
	}

	/**
	 * Adds on more back order to the washer
	 * 
	 * @param backOrder
	 *            the new back order on the washer
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
	 * Checks whether there is a back order on this washer
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
	 * @return price of washer
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * Getter of quantity
	 * 
	 * @return quantity of washer
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * Getter of brand
	 * 
	 * @return brand of washer
	 */
	public String getBrand() {
		return brand;
	}

	/**
	 * Getter of model
	 * 
	 * @return model of washer
	 */
	public String getModel() {
		return model;
	}

	/**
	 * Setter for price
	 * 
	 * @param price
	 *            washer's new price
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * Setter for quantity
	 * 
	 * @param quantity
	 *            washer's new quantity
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	/**
	 * Setter for brand
	 * 
	 * @param brand
	 *            washer's new brand
	 */
	public void setBrand(String brand) {
		this.brand = brand;
	}

	/**
	 * Setter for model
	 * 
	 * @param model
	 *            washer's new model
	 */
	public void setModel(String model) {
		this.model = model;
	}

	/**
	 * String form of washer
	 * 
	 */
	public String toString() {
		return "Washer brand: " + brand + "\tWasher model: " + model + "\tPrice: " + moneyFormat.format(price)
				+ "\tQuantity " + quantity;
	}

}
