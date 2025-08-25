package model;

import java.io.Serializable;

public class Staff implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String staffNumber;
	String staffName;
	String staffUsername;
	String staffPassword;
	String staffPhone;

	public Staff() {
		super();
	}

	public Staff(String staffName, String staffUsername, String staffPassword, String staffPhone) {
		super();
		this.staffName = staffName;
		this.staffUsername = staffUsername;
		this.staffPassword = staffPassword;
		this.staffPhone = staffPhone;
	}

	public String getStaffNumber() {
		return staffNumber;
	}

	public void setStaffNumber(String staffNumber) {
		this.staffNumber = staffNumber;
	}

	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public String getStaffUsername() {
		return staffUsername;
	}

	public void setStaffUsername(String staffUsername) {
		this.staffUsername = staffUsername;
	}

	public String getStaffPassword() {
		return staffPassword;
	}

	public void setStaffPassword(String staffPassword) {
		this.staffPassword = staffPassword;
	}

	public String getStaffPhone() {
		return staffPhone;
	}

	public void setStaffPhone(String staffPhone) {
		this.staffPhone = staffPhone;
	}

	
}
