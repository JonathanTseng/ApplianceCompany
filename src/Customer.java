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
		id = MEMBER_STRING + (MemberIdServer.instance()).getId();		
	}

	
//	public boolean purchase(Washer washer) {
//		// TODO Auto-generated method stub
//		return false;
//	}

}
