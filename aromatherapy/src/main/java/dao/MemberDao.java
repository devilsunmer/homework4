package dao;

import java.util.List;

import model.Member;

public interface MemberDao {
	//creat
	public void add(Member member);
	public void addAddressAndPhone(String memberNumber,String name,String address,String phone);
	
	//read
	public List<Member> all();
	public List<Member> isMember(Integer oneOrZerow);
	public Member idView(String memberNumber);
	public Member nameSearch(String memberName);
	public List<Member> idAllView(String memberNumber);
	public Member userLogin(String username,String password);
	public Member freeUserUse(String memberName,String memberPhone);
	public Boolean checkUsername(String username);//true->有人、false->沒人
	
	//update
	public Member update(Member member);
	public Boolean updatePassword(String username,String password);//修改密碼
	public Boolean updateAddressAndPhone(String memberNumber,String name,String address,String phone);
	
	
	//delete
	public void delete(Member member);
	List<Member> userView(String memberUsername);
}
