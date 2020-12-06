package users;

public class User {

	private String firstName;
	private String lastName;
	private String email;
	private String address;
	private int zip;
	private String state; //two letter code
	private String userName;
	private String password;
	private int ssn; // social security number
	private String securityQuestion;
	private boolean isEmployee;
	
	public User(int ssn, String firstName, String lastName, String email, String address, int zip, String state,
			String userName, String password, String securityQuestion, boolean isEmployee) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.address = address;
		this.zip = zip;
		this.state = state;
		this.userName = userName;
		this.password = password;
		this.ssn = ssn;
		this.securityQuestion = securityQuestion;
		this.isEmployee = false;
	}
	

	@Override
	public String toString() {
		
		return "The name of the user is: " + firstName + lastName + "the email address is: " + email + "the username is: " + userName;
		
	}
}
