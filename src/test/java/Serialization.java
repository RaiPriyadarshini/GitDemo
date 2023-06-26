
public class Serialization {

	// for JSon needs jackson, jacson2 and Gson or JohnZon library in the class path and for xml needs  JAXB
//	Al+Shift+s  generates getters and setters
//	For serialization need to create class for each inner json and for array need to create class and in its main class needs to return list of array List<Api>
	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
