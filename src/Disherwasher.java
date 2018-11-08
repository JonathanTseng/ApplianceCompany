
public class Disherwasher {
	private static final long serialVersionUID = 1L;
	private String brand;
	private String model;
	private int price;

	public void Disherwasher(String brand, String model,int price) {
		this.brand = brand;
		this.model = model;
		this.price = price;
	}

	@Override
	public String toString() {
		return "Disherwasher [brand=" + brand + ", model=" + model + ", price=" + price + "]";
	}
	
}
