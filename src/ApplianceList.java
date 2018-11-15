import java.io.IOException;

/**
 * The collection class for all ApplianceItem objects
 * 
 * @author Stephen Thomas, Jonathan Tseng, Jose Morales
 *
 */
public class ApplianceList extends ItemList<ApplianceItem, String> {
	private static ApplianceList applianceList;

	/**
	 * Private constructor for singleton pattern
	 */
	private ApplianceList() {
	}

	/**
	 * Supports the singleton pattern
	 * 
	 * @return the singleton object
	 */
	public static ApplianceList instance() {
		if (applianceList == null) {
			return (applianceList = new ApplianceList());
		} else {
			return applianceList;
		}
	}

	/**
	 * Removes an appliance from the appliance list
	 * 
	 * @param applianceModel
	 *            the appliance's model
	 * @param applianceBrand
	 *            the appliance's brand
	 * @return true iff appliance could be removed
	 */
	public boolean removeApplianceItem(String applianceModel, String applianceBrand) {
		ApplianceItem applianceItem = search(applianceModel, applianceBrand, "");
		if (applianceItem == null) {
			return false;
		} else {
			return super.remove(applianceItem);
		}
	}

	/**
	 * Inserts an appliance into the collection
	 * 
	 * @param applianceItem
	 *            the appliance to be inserted
	 * @return true iff the appliance could be inserted.
	 */
	public boolean insertApplianceItem(ApplianceItem applianceItem) {
		return super.add(applianceItem);
	}

	/**
	 * Supports serialization
	 * 
	 * @param output
	 *            the stream to be written to
	 */
	private void writeObject(java.io.ObjectOutputStream output) throws IOException {
		output.defaultWriteObject();
		output.writeObject(applianceList);
	}

	/**
	 * Supports deserialization
	 * 
	 * @param input
	 *            the stream to be read from
	 */
	private void readObject(java.io.ObjectInputStream input) throws IOException, ClassNotFoundException {
		input.defaultReadObject();
		if (applianceList == null) {
			applianceList = (ApplianceList) input.readObject();
		} else {
			input.readObject();
		}
	}
}
