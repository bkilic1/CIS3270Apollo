package users;

public class Customer extends User{

	public Customer(int ssn, String firstName, String lastName, String email, String address, int zip, String state, String userName,
			String password, String securityQuestion, boolean isEmployee) {
		super(ssn, firstName, lastName, email, address, zip, state, userName, password, securityQuestion, isEmployee);
		// TODO Auto-generated constructor stub
	}

	public void bookFlightFromAccount() {
		//do some code TBD
	}
	
	public void deleteFlightFromAccount() {
		//do some code TBD
	}
}
