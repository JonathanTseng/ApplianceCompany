import java.io.Serializable;
import java.util.LinkedList;
import java.util.Queue;

public class Washer implements Serializable {
	
	private double price;
	private int quantity = 0;
	private String brand;
	private String model;
	public Queue<BackOrder> backOrderList = new LinkedList<BackOrder>();
	
	public Washer(String brand, String model, double price) {
		this.brand = brand;
		this.model = model;
		this.price = price;
				
	}
	
	public double getPrice() {
		
		return price;
	}

	public void purchase() {
		// decrement quantity,update total, holds
		
		
	}

	public int getQuantity() {
		
		return quantity;
		
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public void placeBackOrder(BackOrder backOrder) {
		backOrderList.add(backOrder);
		
	}

	public static void addQuantity(int quantity2) {
		// TODO Auto-generated method stub
		
	}

	
	
	

}
