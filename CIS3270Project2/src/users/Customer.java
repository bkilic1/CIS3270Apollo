package users;


public class Customer extends User  {

	public Customer(int ssn, String firstName, String lastName, String email, String address, int zip, String state, String userName,
			String password, String securityQuestion, boolean isEmployee) {
		super(ssn, firstName, lastName, email, address, zip, state, userName, password, securityQuestion, isEmployee);
		// TODO Auto-generated constructor stub
	}
	
	public void addFlight() {
		//do some code TBD
	}
	
	public void deleteFlight() {
		//do some code TBD
	}
	
}
