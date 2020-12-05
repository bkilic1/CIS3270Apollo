package users;

public class User {

	private String firstName;
	private String lastName;
	private String id; // added id
	private String email;
	private String address;
	private int zip;
	private String state; //two letter code
	private String userName;
	private String password;
	private String ssn; // social security number
	private String securityQuestion;
	
	public User(String firstName, String lastName, String id, String email, String address, int zip, String state, 
			String userName, String password, String ssn, String securityQuestion) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.id = id;
		this.email = email;
		this.address = address;
		this.zip = zip;
		this.state = state;
		this.userName = userName;
		this.password = password;
		this.ssn = ssn;
		this.securityQuestion = securityQuestion;
	}
	
	
	
	public void addFlight() {
		// do some code
	}
	
	public void deleteFlight() {
		
		
	}
	
	public void updateFlight() {
		
		
	}
	
	@Override
	public String toString() {
		
		return "The name of the user is: " + firstName + lastName + "the email address is: " + email + "the username is: " + userName;
		
	}
}
