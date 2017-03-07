package classes;

public class Player {

	private String name;
	private String lastname;
	
	public Player() {
	}
	
	public Player(String name, String lastname) {
		this.name = name;
		this.lastname = lastname;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	public String getLastname() {
		return this.lastname;
	}
	
}
