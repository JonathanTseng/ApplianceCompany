
public class Dryer {
	private static final long serialVersionUID = 1L;
	private String brand;
	private Object model;
	private int price;

	public void Dryer(String brand, String model,int price) {
		this.brand = brand;
		this.model = model;
		this.price = price;
		
	}

	@Override
	public String toString() {
		return "Dryer [brand=" + brand + ", model=" + model + ", price=" + price + "]";
	}
}
