package service.impl;

import java.util.List;

import dao.impl.MemberDaoImpl;
import model.Member;
import service.MemberService;

public class MemberServiceImpl implements MemberService {

	public static void main(String[] args) {

	}

	public static MemberDaoImpl memberDaoImpl = new MemberDaoImpl();

	@Override
	public Boolean addMember(Member member) {
		if (member != null) {
			if (checkUsername(member.getMemberUsername()) && memberDaoImpl.idView(member.getMemberNumber()) == null) {
				memberDaoImpl.add(member);
				return true;
			}
		}
		return false;
	}
	

	@Override
	public void addCust(Member member) {
		if(member.getMemberUsername()==null) member.setMemberUsername("No");
		if(member.getMemberPassword()==null) member.setMemberPassword("No");
		member.setMemberOrNot(false);
		memberDaoImpl.add(member);
	}

	@Override
	public Boolean addAddressAndPhone(String memberNumber, String name, String address, String phone) {
		if (memberDaoImpl.idView(memberNumber) != null) {
			memberDaoImpl.addAddressAndPhone(memberNumber, name, address, phone);
			return true;
		}
		return false;
	}

	@Override
	public List<Member> all() {
		return memberDaoImpl.all();
	}

	@Override
	public List<Member> userView(String username) {
		return memberDaoImpl.userView(username);
	}
	
	@Override
	public List<Member> itsMember() {
		return memberDaoImpl.isMember(1);
	}

	@Override
	public List<Member> notMember() {
		return memberDaoImpl.isMember(0);
	}

	@Override
	public List<Member> idAllView(String memberNumber) {
		return memberDaoImpl.idAllView(memberNumber);
	}

	@Override
	public Member userSomeView(String memberNumber)
	{
		return memberDaoImpl.idView(memberNumber);
	}
	
	@Override
	public Member userView(String username, String password)
	{
		return memberDaoImpl.userLogin(username, password);
	}
	
	@Override
	public Boolean userLogin(String username, String password) {
		if (username!=null&&password!=null) {
			memberDaoImpl.userLogin(username, password);
			return true;
		}
		return false;
	}

	@Override
	public Member freeUserUse(String memberName, String memberPhone) {
		Member member = null;
		if (memberDaoImpl.checkUsername(memberName)) {
			member = memberDaoImpl.freeUserUse(memberName, memberPhone);
		}
		return member;
	}

	@Override
	public String getMemberNumber(String memberName) {
		Member	member = memberDaoImpl.nameSearch(memberName);
		String number=member.getMemberNumber();
		return number;
	}

	@Override
	public String getMemberName(String memberNumber) {
		Member	member = memberDaoImpl.idView(memberNumber);
		String name=member.getMemberName();
		return name;
	}
	
	@Override
	public Boolean checkUsername(String username) {
		return memberDaoImpl.checkUsername(username);
	}

	@Override
	public Boolean update(Member member) {
		if (memberDaoImpl.idView(member.getMemberNumber()) != null) {

			return true;
		}
		return false;
	}

	@Override
	public Boolean updatePassword(String username, String password) {
		if (memberDaoImpl.checkUsername(username)) {
			memberDaoImpl.updatePassword(username, password);
			return true;
		}
		return false;
	}

	@Override
	public Boolean updateAddressAndPhone(String memberNumber, String name, String address, String phone) {
		if (memberDaoImpl.idView(memberNumber) != null) {
			memberDaoImpl.updateAddressAndPhone(memberNumber, name, address, phone);
			return true;
		}
		return false;
	}

	@Override
	public Boolean delete(Member member) {
		if (memberDaoImpl.idView(member.getMemberNumber()) != null) {
			memberDaoImpl.delete(member);
			return true;
		}
		return false;
	}



}
