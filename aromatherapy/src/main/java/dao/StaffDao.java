package dao;

import java.util.List;

import model.Staff;

public interface StaffDao {
	// creat
	public void add(Staff staff);

	// read
	public List<Staff> all();
	public Staff idView(String staffNumber);
	public Staff userLogin(String username, String password);
	public Boolean checkUsername(String username);// true->有人、false->沒人

	// update
	public Staff update(Staff staff);
	public void updatePassword(String staffNumber, String password);// 修改密碼
	public void updatePhone(String staffNumber, String name, String phone);

	// delete
	public void delete(String staffNumber);
}
