
/**
 * The interface to check if two items are "equal." This circumvents the
 * restrictions of the equals method, which must always implement an equivalence
 * relation.
 * 
 * @author Stephen Thomas, Jonathan Tseng, Jose Morales
 *
 * @param <K>
 *            the key type
 */
public interface Matchable<K> {
	/**
	 * Checks whether an item's key matches the given key.
	 * 
	 * @param key
	 *            the key value
	 * @return true iff the item's key matches the given key
	 */
	public boolean matches(K key1, K key2, K key3);
}
