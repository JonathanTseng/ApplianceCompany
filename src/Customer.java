import java.io.Serializable;

public class Customer implements Serializable {
	private static final String MEMBER_STRING = "C";
	private String id;
	private String phoneNumber;
	private String name;

	
	public Customer(String name, String phoneNumber) {
		// TODO Auto-generated constructor stub
		this.name = name;
		this.phoneNumber = phoneNumber;
		setId(MEMBER_STRING + (MemberIdServer.instance()).getId());		
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}

	
//	public boolean purchase(Washer washer) {
//		// TODO Auto-generated method stub
//		return false;
//	}

}
