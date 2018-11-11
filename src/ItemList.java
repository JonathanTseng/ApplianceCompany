import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Maintains a list of items with type T and key of type K. Subclassed by
 * CustomerList and ApplianceList
 * 
 * @author Stephen Thomas
 * @param <T> type of item
 * @param <K> key of item
 */
public class ItemList<T extends Matchable<K>, K> implements Serializable {
	private static final long serialVersionUID = 1L;
	// private List<T> list = new LinkedList<T>(); changed to array list instead of
	// linked list
	private ArrayList<T> list = new ArrayList<T>();

	/**
	 * Checks whether an item with a given id exists.
	 * 
	 * @param key1 the first id of the item
	 * @param key2 the second id of the item if needed
	 * @return the item iff the item exists
	 * 
	 */
	public T search(K key1, K key2, K key3) {
		for (T item : list) {
			if (item.matches(key1, key2, key3)) {
				return item;
			}
		}
		return null;
	}
	

	/**
	 * Adds an item to the list.
	 * 
	 * @param item the item to be added
	 * @return true iff the item could be added
	 */
	public boolean add(T item) {
		return list.add(item);
	}

	/**
	 * Removes the item from the list
	 * 
	 * @param item the item to be removed
	 * @return true iff the item could be removed
	 */
	public boolean remove(T item) {
		return list.remove(item);
	}

	/**
	 * Returns an iterator for the list
	 * 
	 * @return iterator for the collection
	 */
	public Iterator<T> iterator() {
		return list.iterator();
	}
}
