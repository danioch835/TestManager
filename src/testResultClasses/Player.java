package testResultClasses;

import java.util.Date;

public class Player {

	private String name;
	private String lastname;
	private Date dateOfBirth;
	
	public Player() {
	}
	
	public Player(String name, String lastname) {
		this.name = name;
		this.lastname = lastname;
	}
	
	public Player(String name, String lastname, Date dateOfBirth) {
		this.name = name;
		this.lastname = lastname;
		this.dateOfBirth = dateOfBirth;
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
	
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	
}
