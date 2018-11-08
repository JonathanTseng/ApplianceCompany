
public class Furnace {
	private static final long serialVersionUID = 1L;
	private String brand;
	private Object model;
	private int BTU;
	private int price;

	public void Furnance(String brand, String model,int price, int BTU) {
		this.brand = brand;
		this.model = model;
		this.price = price;
		this.BTU = BTU;
	}

	@Override
	public String toString() {
		return "Furnace [brand=" + brand + ", model=" + model + ", BTU=" + BTU + ", price=" + price + "]";
	}
	
}
