
public class Range {
	private static final long serialVersionUID = 1L;
	private String brand;
	private Object model;
	private int price;

	public void Range(String brand, String model,int price) {
		this.brand = brand;
		this.model = model;
		this.price = price;
		
	}

	@Override
	public String toString() {
		return "Range [brand=" + brand + ", model=" + model + ", price=" + price + "]";
	}
	
}
