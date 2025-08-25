package service;

import java.util.List;

import model.Member;

public interface MemberService {
	//creat
		public Boolean addMember(Member member);
		public void addCust(Member member);
		public Boolean addAddressAndPhone(String memberNumber,String name,String address,String phone);
		
		//read
		public List<Member> all();
		public List<Member> itsMember();
		public List<Member> notMember();
		public List<Member> idAllView(String memberNumber);
		public Member userSomeView(String memberNumber);
		public String getMemberNumber(String memberName);
		public Member userView(String username, String password);
		public Boolean userLogin(String username,String password);
		public Member freeUserUse(String memberName,String memberPhone);
		public Boolean checkUsername(String username);//true->有人、false->沒人
		
		//update
		public Boolean update(Member member);
		public Boolean updatePassword(String username,String password);//修改密碼
		public Boolean updateAddressAndPhone(String memberNumber,String name,String address,String phone);
		
		
		//delete
		public Boolean delete(Member member);
		List<Member> userView(String username);
		String getMemberName(String memberNumber);
}
