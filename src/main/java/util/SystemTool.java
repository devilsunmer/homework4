package util;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import model.Member;
import model.OrderAll;
import model.OrderData;
import model.OrderItem;
import model.Product;
import model.ProductStock;
import model.ProductSystemView;
import model.Staff;
import service.impl.MemberServiceImpl;
import service.impl.OrderAllServiceImpl;
import service.impl.OrderItemServiceImpl;
import service.impl.ProductServiceImpl;
import service.impl.StaffServiceImpl;

public class SystemTool {

	public static void main(String[] args) {

	}

	public static String[] changeForJTable(Object object) {
		String[] some = null;
		if (object instanceof Member) {
			some = new String[] { "會員編號", "姓名", "帳號", "密碼", "地址", "電話", "是否會員" };
		} else if (object instanceof OrderData) {
			some = new String[] { "訂單編號", "會員編號", "訂購產品", "訂購數量", "訂購產品小計總價", "訂單總金額", "訂單日期" };
		} else if (object instanceof Product) {
			some = new String[] { "產品編號", "產品名稱", "品種分類", "產品介紹大綱", "產品進價", "產品定價" };
		} else if (object instanceof ProductStock) {
			some = new String[] { "產品編號", "產品進貨量", "產品出口量", "產品進出貨日期" };
		} else if (object instanceof ProductSystemView) {
			some = new String[] { "產品編號", "產品名稱", "總計進口量", "總計出口量", "目前庫存量" };
		} else if (object instanceof Staff) {
			some = new String[] { "員工編號", "員工姓名", "員工帳號", "員工密碼", "員工電話" };
		}
		return some;
	}

	public static boolean setChangeJTable(Object object, int columnIndex) {
		boolean[] some = null;
		if (object instanceof Member) {
			some = new boolean[] { false, true, true, true, true, true, false };
		} else if (object instanceof OrderData) {
			some = new boolean[] { false, false, true, true, true, true, false };
		} else if (object instanceof Product) {
			some = new boolean[] { false, true, true, true, true, true };
		} else if (object instanceof ProductStock) {
			some = new boolean[] { false, true, true, false };
		} else if (object instanceof ProductSystemView) {
			some = new boolean[] { false, false, false, false, false };
		} else if (object instanceof Staff) {
			some = new boolean[] { false, true, true, true, true };
		}

		if (some != null && columnIndex >= 0 && columnIndex < some.length) {
			return some[columnIndex];
		}

		return false;
	}

	public static JTable setForObject(List<Object> objectList, Object object) {
		String[] columns = changeForJTable(object);
		return new JTable(new AbstractTableModel() {
			@Override
			public int getRowCount() {
				return objectList.size();
			}

			@Override
			public int getColumnCount() {
				return columns.length;
			}

			@Override
			public Object getValueAt(int rowIndex, int columnIndex) {
				Object obj = objectList.get(rowIndex);
				if (obj instanceof Member) {
					Member m = (Member) obj;
					switch (columnIndex) {
					case 0:
						return m.getMemberNumber();
					case 1:
						return m.getMemberName();
					case 2:
						return m.getMemberUsername();
					case 3:
						return m.getMemberPassword();
					case 4:
						return m.getMemberAddress();
					case 5:
						return m.getMemberPhone();
					case 6:
						return m.getMemberOrNot();
					default:
						return null;
					}
				} else if (obj instanceof Product) {
					Product p = (Product) obj;
					switch (columnIndex) {
					case 0:
						return p.getProductNumber();
					case 1:
						return p.getProductName();
					case 2:
						return p.getCategory();
					case 3:
						return p.getProdouctOverview();
					case 4:
						return p.getProductCost();
					case 5:
						return p.getProductPrice();
					default:
						return null;
					}
				} else if (obj instanceof ProductStock) {
					ProductStock p = (ProductStock) obj;
					switch (columnIndex) {
					case 0:
						return p.getProductNumber();
					case 1:
						return p.getProductInStock();
					case 2:
						return p.getProductOutStock();
					case 3:
						return p.getProductStockDate();
					default:
						return null;
					}
				} else if (obj instanceof ProductSystemView) {
					ProductSystemView p = (ProductSystemView) obj;
					switch (columnIndex) {
					case 0:
						return p.getNumber();
					case 1:
						return p.getName();
					case 2:
						return p.getIn();
					case 3:
						return p.getOut();
					case 4:
						return p.getNowHave();
					default:
						return null;
					}
				} else if (obj instanceof Staff) {
					Staff s = (Staff) obj;
					switch (columnIndex) {
					case 0:
						return s.getStaffNumber();
					case 1:
						return s.getStaffName();
					case 2:
						return s.getStaffUsername();
					case 3:
						return s.getStaffPassword();
					case 4:
						return s.getStaffPhone();
					default:
						return null;
					}
				}
				return null;
			}

			@Override
			public String getColumnName(int column) {
				return columns[column];
			}

			@Override
			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return setChangeJTable(object, columnIndex);
			}

			@Override
			public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
				Object obj = objectList.get(rowIndex);
				if (obj instanceof Member) {
					Member m = (Member) obj;
					switch (columnIndex) {
					case 1:
						m.setMemberName((String) aValue);
						break;
					case 2:
						m.setMemberUsername((String) aValue);
						break;
					case 3:
						m.setMemberPassword((String) aValue);
						break;
					case 4:
						m.setMemberAddress((String) aValue);
						break;
					case 5:
						m.setMemberPhone((String) aValue);
						break;
					case 6:
						m.setMemberOrNot((boolean) aValue);
						break;
					case 7:
						m.setMemberNumber((String) aValue);
						break;
					default:
						break;
					}
				} else if (obj instanceof Product) {
					Product p = (Product) obj;
					switch (columnIndex) {
					case 1:
						p.setProductName((String) aValue);
						break;
					case 2:
						p.setCategory((String) aValue);
						break;
					case 3:
						p.setProdouctOverview((String) aValue);
						break;
					case 4:
						p.setProductCost((Integer) aValue);
						break;
					case 5:
						p.setProductPrice((Integer) aValue);
						break;
					case 6:
						p.setProductNumber((String) aValue);
						break;
					default:
						break;
					}
				} else if (obj instanceof ProductStock) {
					ProductStock p = (ProductStock) obj;
					switch (columnIndex) {
					case 1:
						p.setProductInStock((Integer) aValue);
						break;
					case 2:
						p.setProductOutStock((Integer) aValue);
						break;
					case 3:
						p.setProductStockDate((Date) aValue);
						break;
					case 4:
						p.setProductNumber((String) aValue);
						break;
					default:
						break;
					}
				} else if (obj instanceof ProductSystemView) {
					ProductSystemView p = (ProductSystemView) obj;
					switch (columnIndex) {
					case 1:
						p.setName((String) aValue);
						break;
					case 2:
						p.setIn(null);
						break;
					case 3:
						p.setOut(null);
						break;
					case 4:
						p.setNowHave(null);
						break;
					case 5:
						p.setNumber((String) aValue);
						break;
					default:
						break;
					}
				} else if (obj instanceof Staff) {
					Member m = (Member) obj;
					switch (columnIndex) {
					case 1:
						m.setMemberName((String) aValue);
						break;
					case 2:
						m.setMemberUsername((String) aValue);
						break;
					case 3:
						m.setMemberPassword((String) aValue);
						break;
					case 4:
						m.setMemberAddress((String) aValue);
						break;
					case 5:
						m.setMemberPhone((String) aValue);
						break;
					case 6:
						m.setMemberOrNot((boolean) aValue);
						break;
					case 7:
						m.setMemberNumber((String) aValue);
						break;
					default:
						break;
					}
				}
				fireTableRowsUpdated(rowIndex, rowIndex);
			}
		});
	}

	public static List<OrderData> releaseSpaceForOrderData() {
		List<OrderData> orderDataList = new ArrayList<>();
		List<OrderAll> orderAllList = new OrderAllServiceImpl().allOrder();
		List<OrderItem> orderItemList = new ArrayList<>();
		for (OrderAll o : orderAllList) {
			OrderAll orderAll = new OrderAll();
			orderAll.setOrderNumber(o.getOrderNumber());
			orderAll.setMemberNumber(o.getMemberNumber());
			orderItemList = new OrderItemServiceImpl().orderItemView(o.getOrderNumber());
			orderAll.setOrderSum(o.getOrderSum());
			orderAll.setOrderDate(o.getOrderDate());
			OrderData orderData = new OrderData();
			orderData.setItems(orderItemList);
			orderData.setOrderall(orderAll);
			orderDataList.add(orderData);
		}
		return orderDataList;
	}

	public static List<Object[]> convertOrderDataList(List<OrderData> dataList, List<RowMapping> mappings) {
		List<Object[]> rows = new ArrayList<>();
		for (OrderData od : dataList) {
			OrderAll oa = od.getOrderall();
			List<OrderItem> items = od.getItems();
			if (items == null || items.isEmpty()) {
				// 沒有任何 OrderItem，填入 noInfo
				Object[] row = new Object[] { oa.getOrderNumber(), oa.getMemberNumber(), "noInfo", // ProductNumber
						0, // Quantity
						0.0, // 單價
						oa.getOrderSum(), oa.getOrderDate() };
				rows.add(row);
				mappings.add(new RowMapping(od, null)); // 沒有對應的 item
			} else {
				for (OrderItem item : items) {
					Object[] row = new Object[] { oa.getOrderNumber(), oa.getMemberNumber(), item.getProductNumber(),
							item.getProductQuantity(), item.getOrderProductPrice(), oa.getOrderSum(),
							oa.getOrderDate() };
					rows.add(row);
					mappings.add(new RowMapping(od, item));
				}
			}
		}
		return rows;
	}

	public static JTable createOrderDataTable(OrderData orderData) {
		String[] columns = changeForJTable(orderData);
		List<OrderData> orderDataList = releaseSpaceForOrderData();
		List<RowMapping> mappings = new ArrayList<>();
		List<Object[]> data = convertOrderDataList(orderDataList, mappings);

		return new JTable(new AbstractTableModel() {
			@Override
			public int getRowCount() {
				return data.size();
			}

			@Override
			public int getColumnCount() {
				return columns.length;
			}

			@Override
			public Object getValueAt(int rowIndex, int columnIndex) {
				return data.get(rowIndex)[columnIndex];
			}

			@Override
			public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
				Object[] rowData = data.get(rowIndex);
				rowData[columnIndex] = aValue;

				RowMapping mapping = mappings.get(rowIndex);
				OrderData od = mapping.orderData;
				OrderItem item = mapping.orderItem;
				OrderAll oa = od.getOrderall();

				try {
					switch (columnIndex) {
					case 1:
						oa.setMemberNumber((String) aValue);
						break;
					case 2:
						item.setProductNumber((String) aValue);
						break;
					case 3:
						if (aValue instanceof Integer) {
							item.setProductQuantity((Integer) aValue);
						} else {
							item.setProductQuantity(Integer.parseInt(aValue.toString()));
						}
						break;
					case 4:
						item.setOrderProductPrice((Double) aValue);
						break;
					case 5:
						oa.setOrderSum((Double) aValue);
						break;
					case 6:
						oa.setOrderDate((Date) aValue);
						break;
					}
					rowData[5] = oa.getOrderSum();
					// 更新總金額（範例）
					oa.setOrderSum(od.getItems().stream()
							.mapToDouble(i -> i.getProductQuantity() * i.getOrderProductPrice()).sum());
					fireTableRowsUpdated(rowIndex, rowIndex);
				} catch (Exception ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(null, "資料更新失敗：" + ex.getMessage());
				}
			}

			@Override
			public String getColumnName(int column) {
				return columns[column];
			}

			@Override
			public boolean isCellEditable(int rowIndex, int columnIndex) {
				// 禁止編輯 訂單編號、小計、總金額
				return setChangeJTable(orderData, columnIndex);
			}
		});
	}

	public static void forSearchMemberOrder(String memberNumber) {
		StringBuilder message = new StringBuilder();
		message.append("【查詢會員訂單資料】\n\n");
		List<OrderAll> orderAllList = new OrderAllServiceImpl().memberOrderView(memberNumber);
		List<OrderData> dataList=new ArrayList<>();
		for (OrderAll oaa : orderAllList) {
			OrderData open=new OrderData();
			open.setOrderall(oaa);
			dataList.add(open);
		}
		for (OrderData od : dataList) {
			OrderAll oa = od.getOrderall();
			message.append("會員編號：").append(memberNumber).append("\n");
			message.append("會員名稱：").append(new MemberServiceImpl().userSomeView(memberNumber).getMemberName())
					.append("\n");
			message.append("訂單編號：").append(oa.getOrderNumber()).append("\n");
			message.append("訂單日期：").append(oa.getOrderDate()).append("\n");

			for (OrderItem item : od.getItems()) {
				message.append("  └ 產品編號：").append(item.getProductNumber()).append("\n");
				message.append("     數量：").append(item.getProductQuantity()).append("\n");
				message.append("     單價：").append(item.getOrderProductPrice()).append("\n");
				message.append("     小計：").append(item.getProductQuantity() * item.getOrderProductPrice())
						.append("\n\n");
			}

			message.append("訂單總金額：").append(oa.getOrderSum()).append("\n");
			message.append("──────────────────────────\n");
		}
		JOptionPane.showMessageDialog(null, message.toString(), "查詢會員訂單資料", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public static void forSearchOrderNumber(String orderNumber) {
		StringBuilder message = new StringBuilder();
		message.append("【查詢訂單編號資料】\n\n");
		List<OrderAll> orderAllList = new OrderAllServiceImpl().orderNumberView(orderNumber);
		List<OrderData> dataList=new ArrayList<>();
		for (OrderAll oaa : orderAllList) {
			OrderData open=new OrderData();
			open.setOrderall(oaa);
			dataList.add(open);
		}
		for (OrderData od : dataList) {
			OrderAll oa = od.getOrderall();
			message.append("會員編號：").append(oa.getMemberNumber()).append("\n");
			message.append("會員名稱：").append(new MemberServiceImpl().userSomeView(oa.getMemberNumber()).getMemberName())
					.append("\n");
			message.append("訂單編號：").append(oa.getOrderNumber()).append("\n");
			message.append("訂單日期：").append(oa.getOrderDate()).append("\n");

			for (OrderItem item : od.getItems()) {
				message.append("  └ 產品編號：").append(item.getProductNumber()).append("\n");
				message.append("     數量：").append(item.getProductQuantity()).append("\n");
				message.append("     單價：").append(item.getOrderProductPrice()).append("\n");
				message.append("     小計：").append(item.getProductQuantity() * item.getOrderProductPrice())
						.append("\n\n");
			}

			message.append("訂單總金額：").append(oa.getOrderSum()).append("\n");
			message.append("──────────────────────────\n");
		}
		JOptionPane.showMessageDialog(null, message.toString(), "查詢訂單編號資料", JOptionPane.INFORMATION_MESSAGE);
	}

	public static void forSearchMemberNumber(String memberUser) {
	    List<Member> member = new MemberServiceImpl().userView(memberUser);
	    StringBuilder message = new StringBuilder();
	    message.append("【查詢會員資料】\n\n");
	    for(Member o:member) {
	    message.append("會員編號：").append(o.getMemberNumber()).append("\n");
	    message.append("姓名：").append(o.getMemberName()).append("\n");
	    message.append("帳號：").append(o.getMemberUsername()).append("\n");
	    message.append("密碼：").append(o.getMemberPassword()).append("\n");
	    message.append("地址：").append(o.getMemberAddress()).append("\n");
	    message.append("電話：").append(o.getMemberPhone()).append("\n");
	    message.append("是否會員：").append(o.getMemberOrNot() ? "是" : "否").append("\n");
	    }
	    JOptionPane.showMessageDialog(null, message.toString(), "查詢會員資料", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public static void forSearchProduct(String productNumber) {
	    Product product = new ProductServiceImpl().productNumber(productNumber);
	    StringBuilder message = new StringBuilder();
	    message.append("【查詢產品資料】\n\n");
	    message.append("產品編號：").append(product.getProductNumber()).append("\n");
	    message.append("產品名稱：").append(product.getProductName()).append("\n");
	    message.append("分類：").append(product.getCategory()).append("\n");
	    message.append("產品介紹大綱：").append(product.getProdouctOverview()).append("\n");
	    message.append("產品進價：").append(product.getProductCost()).append("\n");
	    message.append("產品定價：").append(product.getProductPrice()).append("\n");

	    JOptionPane.showMessageDialog(null, message.toString(), "查詢產品資料", JOptionPane.INFORMATION_MESSAGE);
	}

	public static void forSearchStaff(String staffNumber) {
	    Staff staff = new StaffServiceImpl().viewSomeone(staffNumber);
	    StringBuilder message = new StringBuilder();
	    message.append("【查詢員工資料】\n\n");
	    message.append("員工編號：").append(staff.getStaffNumber()).append("\n");
	    message.append("員工姓名：").append(staff.getStaffName()).append("\n");
	    message.append("員工帳號：").append(staff.getStaffUsername()).append("\n");
	    message.append("員工密碼：").append(staff.getStaffPassword()).append("\n");
	    message.append("員工電話：").append(staff.getStaffPhone()).append("\n");

	    JOptionPane.showMessageDialog(null, message.toString(), "查詢員工資料", JOptionPane.INFORMATION_MESSAGE);
	}

	public static void forSearchMember(String memberNumber) {
	    Member member = new MemberServiceImpl().userSomeView(memberNumber);
	    StringBuilder message = new StringBuilder();
	    message.append("【查詢會員資料】\n\n");
	    message.append("會員編號：").append(member.getMemberNumber()).append("\n");
	    message.append("姓名：").append(member.getMemberName()).append("\n");
	    message.append("帳號：").append(member.getMemberUsername()).append("\n");
	    message.append("密碼：").append(member.getMemberPassword()).append("\n");
	    message.append("地址：").append(member.getMemberAddress()).append("\n");
	    message.append("電話：").append(member.getMemberPhone()).append("\n");
	    message.append("是否會員：").append(member.getMemberOrNot() ? "是" : "否").append("\n");

	    JOptionPane.showMessageDialog(null, message.toString(), "查詢會員資料", JOptionPane.INFORMATION_MESSAGE);
	}

	public static void saveAdvertiseContent() {
	    String content = JOptionPane.showInputDialog(null, "請輸入廣告內容：", "廣告輸入", JOptionPane.PLAIN_MESSAGE);

	    // 若使用者按取消或沒輸入內容就直接結束
	    if (content == null || content.trim().isEmpty()) {
	        JOptionPane.showMessageDialog(null, "未輸入任何內容，操作已取消。", "提示", JOptionPane.WARNING_MESSAGE);
	        return;
	    }

	    int option = JOptionPane.showConfirmDialog(null, "是否永久儲存？", "儲存選項", JOptionPane.YES_NO_OPTION);

	    String fileName = (option == JOptionPane.YES_OPTION) ? "advertise.txt" : "advertiseMoment.txt";

	    try (FileWriter writer = new FileWriter(fileName, true)) {
	        writer.write(content + System.lineSeparator());
	        JOptionPane.showMessageDialog(null, "內容已成功儲存至 " + fileName, "完成", JOptionPane.INFORMATION_MESSAGE);
	    } catch (IOException e) {
	        JOptionPane.showMessageDialog(null, "寫入失敗：" + e.getMessage(), "錯誤", JOptionPane.ERROR_MESSAGE);
	    }
	}


}

class RowMapping {
	OrderData orderData;
	OrderItem orderItem;

	RowMapping(OrderData orderData, OrderItem orderItem) {
		this.orderData = orderData;
		this.orderItem = orderItem;
	}
}
