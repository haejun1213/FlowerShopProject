package flowershop.model;

public class Admin {

	private String id = "haejun";
	private String password= "!@!#";
	
	public boolean login(String id, String password) {
		if(this.id.equals(id) && this.password.equals(password))
			return true;
		return false;
	}
}
