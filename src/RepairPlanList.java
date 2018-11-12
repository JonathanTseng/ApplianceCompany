import java.io.IOException;

/**
 * The collection class of all repair plan objects.
 * 
 * @author stephenthomas
 *
 */
public class RepairPlanList extends ItemList<RepairPlan, String> {
	private static RepairPlanList repairPlanList;

	/**
	 * Private constructor for singleton pattern
	 */
	private RepairPlanList() {
	}

	/**
	 * Supports the singleton pattern
	 * 
	 * @return the singleton object
	 */
	public static RepairPlanList instance() {
		if (repairPlanList == null) {
			return (repairPlanList = new RepairPlanList());
		} else {
			return repairPlanList;
		}
	}

	/**
	 * Removes a repair plan from the repair plan list
	 * 
	 * @param applianceModel the appliance's model
	 * @param applianceBrand the appliance's brand
	 * @param customerId     the id of the customer that has the repair plan
	 * @return true iff appliance could be removed
	 */
	public boolean removeRepairPlan(String applianceBrand, String applianceModel, String customerId) {
		RepairPlan repairPlan = search(applianceBrand, applianceModel, customerId);
		if (repairPlan == null) {
			return false;
		} else {
			return super.remove(repairPlan);
		}
	}

	/**
	 * Inserts a repair plan into the collection
	 * 
	 * @param repairPlan the repair plan to be inserted
	 * @return true iff the repair plan could be inserted.
	 */
	public boolean insertRepairPlan(RepairPlan repairPlan) {
		return super.add(repairPlan);
	}

	/**
	 * Supports serialization
	 * 
	 * @param output the stream to be written to
	 */
	private void writeObject(java.io.ObjectOutputStream output) throws IOException {
		output.defaultWriteObject();
		output.writeObject(repairPlanList);
	}

	/**
	 * Supports deserialization
	 * 
	 * @param input the stream to be read from
	 */
	private void readObject(java.io.ObjectInputStream input) throws IOException, ClassNotFoundException {
		input.defaultReadObject();
		if (repairPlanList == null) {
			repairPlanList = (RepairPlanList) input.readObject();
		} else {
			input.readObject();
		}
	}
}
