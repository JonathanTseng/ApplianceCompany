import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

/**
 * Generates member ids.
 * 
 * @author Brahma Dathan and Sarnath Ramnath
 *
 */
public class MemberIdServer implements Serializable {
	private int idCounter;
	private static MemberIdServer server;

	/**
	 * Private constructor for singleton pattern
	 * 
	 */
	private MemberIdServer() {
		idCounter = 1;
	}

	/**
	 * Supports the singleton pattern
	 * 
	 * @return the singleton object
	 */
	public static MemberIdServer instance() {
		if (server == null) {
			return (server = new MemberIdServer());
		} else {
			return server;
		}
	}

	/**
	 * Getter for id
	 * 
	 * @return id of the member
	 */
	public int getId() {
		return idCounter++;
	}

	/**
	 * String form of the collection
	 * 
	 */
	@Override
	public String toString() {
		return ("IdServer" + idCounter);
	}

	/**
	 * Retrieves the server object
	 * 
	 * @param input inputstream for deserialization
	 */
	public static void retrieve(ObjectInputStream input) {
		try {
			server = (MemberIdServer) input.readObject();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (Exception cnfe) {
			cnfe.printStackTrace();
		}
	}

}
