/**
 * This is the standard Visitor interface for the visitor pattern.
 * 
 * @author Brahma Dathan
 *
 */
public interface ApplianceItemVisitor {
	/**
	 * This is the method for catching all LoanableItem objects that may be
	 * added in the future.
	 * 
	 * @param item
	 *            the item to be processed
	 */
	public void visit(ApplianceItem item);

	/**
	 * This method processes Book objects
	 * 
	 * @param book
	 *            the Book to be processed
	 */
	public void visit(Refrigerator refrigerator);

	/**
	 * The method processes Periodical objects
	 * 
	 * @param periodical
	 *            the Periodical to be processed
	 */
	public void visit(ClothesDryer clothesDryer);
	/**
	 * The method processes Periodical objects
	 * 
	 * @param periodical
	 *            the Periodical to be processed
	 */
	public void visit(ClothesWasher clothesWasher);

	/**
 	* The method processes Periodical objects
 	* 
 	* @param periodical
 	*            the Periodical to be processed
 	*/
	public void visit(Furnace furnance);
	
}