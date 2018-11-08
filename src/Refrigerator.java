
public class Refrigerator {
	
	private static final long serialVersionUID = 1L;
	private String brand;
	private String model;
	private int price;
	private int capacity;

	public void Furnance(String brand, String model,int price, int capacity) {
		this.brand = brand;
		this.model = model;
		this.price = price;
		this.capacity = capacity;
	}

	@Override
	public String toString() {
		return "Refrigerator [brand=" + brand + ", model=" + model + ", price=" + price + ", capacity=" + capacity
				+ "]";
	}
	
}
