package service.impl;

import java.util.ArrayList;
import java.util.List;

import dao.impl.StaffDaoImpl;
import model.Member;
import model.Staff;
import service.StaffService;

public class StaffServiceImpl implements StaffService {

	public static void main(String[] args) {
//		System.out.println(new StaffServiceImpl().viewSomeone("s003"));

//		System.out.println(new StaffServiceImpl().checkStaffUsername("s003"));

//		System.out.println(new StaffServiceImpl().staffLogin("boss1","boss1"));

//		System.out.println(new StaffServiceImpl().viewSomeone("s003").getStaffName());

//		List<String> slist=new StaffServiceImpl().staffNameView();
//		for(String o:slist)
//		{
//			System.out.println(o);
//		}

//		List<Staff> slist=new StaffServiceImpl().allStaff();
//		for(Staff o:slist)
//		{
//			System.out.println(o.getStaffName());
//		}

//		new StaffServiceImpl().addStaff(new Staff("盧卡斯","qoo2","tr543","0977777777"));
	}

	public static StaffDaoImpl staffDaoImpl = new StaffDaoImpl();

	@Override
	public Boolean addStaff(Staff staff) {
		if (staffDaoImpl.idView(staff.getStaffNumber()) == null) {
			if (!staffDaoImpl.checkUsername(staff.getStaffUsername())) {
				staffDaoImpl.add(staff);
				return true;
			}
		}
		return false;
	}

	@Override
	public List<Staff> allStaff() {
		return staffDaoImpl.all();
	}

	@Override
	public List<String> staffNameView() {
		List<Staff> staffList = staffDaoImpl.all();
		List<String> nameList = new ArrayList<>();
		for (Staff o : staffList) {
			nameList.add(o.getStaffName());
		}
		return nameList;
	}

	@Override
	public Staff viewSomeone(String staffNumber) {
		return staffDaoImpl.idView(staffNumber);
	}

	@Override
	public Staff staffLogin(String username, String password) {
		return staffDaoImpl.userLogin(username, password);
	}

	@Override
	public Boolean checkStaffUsername(String username) {
		if (staffDaoImpl.checkUsername(username))
			return true;
		return false;
	}

	@Override
	public Boolean updateAll(Staff staff) {
		Staff view = staffDaoImpl.idView(staff.getStaffNumber());
		if ((view) != null) {
			Staff staffChange = new Staff();
			staffChange.setStaffNumber(staff.getStaffNumber());
			String name = staff.getStaffName() != null ? staff.getStaffName() : view.getStaffName();
			String password = staff.getStaffPassword() != null ? staff.getStaffPassword() : view.getStaffPassword();
			String phone = staff.getStaffPhone() != null ? staff.getStaffPhone() : view.getStaffPhone();
			staffChange.setStaffName(name);
			staffChange.setStaffPassword(password);
			staffChange.setStaffPhone(phone);
			staffDaoImpl.update(staffChange);
			return true;
		}
		return false;
	}

	@Override
	public Boolean updateOnlyPassword(String staffNumber, String password) {
		if (staffDaoImpl.idView(staffNumber) != null) {
			staffDaoImpl.updatePassword(staffNumber, password);
			return true;
		}
		return false;
	}

	@Override
	public Boolean updatePhone(String staffNumber, String name, String phone) {
		if (staffDaoImpl.idView(staffNumber) != null) {
			staffDaoImpl.updatePhone(staffNumber, name, phone);
			return true;
		}
		return false;
	}

	@Override
	public Boolean deleteStaff(String staffNumber) {
		if (staffDaoImpl.idView(staffNumber) != null) {
			staffDaoImpl.delete(staffNumber);
			return true;
		}
		return false;
	}

}
