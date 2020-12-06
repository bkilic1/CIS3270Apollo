package users;

<<<<<<< HEAD
public class Customer extends User{
=======
public class Customer extends User  {
>>>>>>> branch 'master' of https://github.com/mrjoemen/CIS3270Project2.git

	public Customer(int ssn, String firstName, String lastName, String email, String address, int zip, String state, String userName,
			String password, String securityQuestion, boolean isEmployee) {
		super(ssn, firstName, lastName, email, address, zip, state, userName, password, securityQuestion, isEmployee);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void addFlight() {
		//do some code TBD
	}
	@Override
	public void deleteFlight() {
		//do some code TBD
	}
}
