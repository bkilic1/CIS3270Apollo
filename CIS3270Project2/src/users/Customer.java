package users;

public class Customer extends User {

	public Customer(String firstName, String lastName, String email, String address, int zip, String state, String userName,
			String password, String ssn, String securityQuestion) {
		super(firstName, lastName, email, address, zip, state, userName, password, ssn, securityQuestion);
		// TODO Auto-generated constructor stub
	}

	public void bookFlightFromAccount() {
		//do some code TBD
	}
	
	public void deleteFlightFromAccount() {
		//do some code TBD
	}
}
