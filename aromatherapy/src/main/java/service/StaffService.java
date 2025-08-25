package service;

import java.util.List;

import model.Staff;

public interface StaffService {
	// creat
		public Boolean addStaff(Staff staff);

		// read
		public List<Staff> allStaff();
		public List<String> staffNameView();
		public Staff viewSomeone(String staffNumber);
		public Staff staffLogin(String username, String password);
		public Boolean checkStaffUsername(String username);// 可以直接確認有沒有被使用

		// update
		public Boolean updateAll(Staff staff);
		public Boolean updateOnlyPassword(String staffNumber, String password);// 修改密碼
		public Boolean updatePhone(String staffNumber, String name, String phone);

		// delete
		public Boolean deleteStaff(String staffNumber);

}
