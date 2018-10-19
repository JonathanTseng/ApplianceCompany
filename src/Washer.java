import java.io.Serializable;

public class Washer implements Serializable {
	
	private double price;
	private int quantity = 0;
	private String brand;
	private String model;
	
	public Washer(String brand, String model, double price) {
		// TODO Auto-generated constructor stub
	}
	
	public double getPrice() {
		
		return price;
	}

	public void purchase() {
		// decrement quantity,update total, holds
		
		
	}

	public static void addQuantity(int quantity) {
		// TODO Auto-generated method stub

	}
	public int getQuantity() {
		
		return quantity;
		
	}

	public void placeBackOrder(BackOrder backOrder) {
		// TODO Auto-generated method stub
		
	}
	
	

}
