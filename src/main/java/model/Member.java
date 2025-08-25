package model;

import java.io.Serializable;

public class Member implements Serializable{
	private static final long serialVersionUID = 1L;
	String memberNumber;
	String memberName;
	String memberUsername;
	String memberPassword;
	String memberAddress;
	String memberPhone;
	Boolean memberOrNot;
	
	public Member() {
		super();
	}

	public Member(String memberName, String memberUsername, String memberPassword,
			String memberAddress, String memberPhone, Boolean memberOrNot) {
		super();
		this.memberName = memberName;
		this.memberUsername = memberUsername;
		this.memberPassword = memberPassword;
		this.memberAddress = memberAddress;
		this.memberPhone = memberPhone;
		this.memberOrNot = memberOrNot;
	}

	public String getMemberNumber() {
		return memberNumber;
	}

	public void setMemberNumber(String memberNumber) {
		this.memberNumber = memberNumber;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getMemberUsername() {
		return memberUsername;
	}

	public void setMemberUsername(String memberUsername) {
		this.memberUsername = memberUsername;
	}

	public String getMemberPassword() {
		return memberPassword;
	}

	public void setMemberPassword(String memberPassword) {
		this.memberPassword = memberPassword;
	}

	public String getMemberAddress() {
		return memberAddress;
	}

	public void setMemberAddress(String memberAddress) {
		this.memberAddress = memberAddress;
	}

	public String getMemberPhone() {
		return memberPhone;
	}

	public void setMemberPhone(String memberPhone) {
		this.memberPhone = memberPhone;
	}

	public Boolean getMemberOrNot() {
		return memberOrNot;
	}

	public void setMemberOrNot(Boolean memberOrNot) {
		this.memberOrNot = memberOrNot;
	}
	
}
