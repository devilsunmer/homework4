package util;

import java.io.File;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

import model.Member;
import model.OrderAll;
import model.OrderData;
import model.OrderItem;
import service.impl.MemberServiceImpl;
import service.impl.OrderAllServiceImpl;
import service.impl.OrderItemServiceImpl;
import service.impl.ProductServiceImpl;

public class OrderTool {

	public static void main(String[] args) {

	}

	public static List<String> custOrder(Member member) {
		List<String> cust = new ArrayList<>();
		if(member!=null)
		{
			cust.add("會員將會有促銷優惠，所有商品小計結算時會自動8折，\n"
				+ "選購完畢消費8000元將有65折優惠！");
		}else {
			cust.add("若消費滿7500元將會有9折優惠喔！");
		}
		cust.add("商品名稱：　　　　　　　　購買數量：　　　　　　商品單價定價價格：\r\n");
		return cust;
	}

	public static void orderUse(String productName, int quantity, int price,JTable JtableName,Member member) {
		try {
			boolean itemExists = false;
			DefaultTableModel model = (DefaultTableModel) JtableName.getModel();
			int rowCount = model.getRowCount();
			for (int i = 0; i < rowCount; i++) {
				String existingProductName = model.getValueAt(i, 0).toString();
				if (existingProductName.equals(productName)) {
					int existingQuantity = Integer.parseInt(model.getValueAt(i, 1).toString());
					existingQuantity += quantity; // 累加數量
					double newSubTotal = existingQuantity * price;
					newSubTotal=subTotal(member, newSubTotal);
					model.setValueAt(existingQuantity, i, 1);
					model.setValueAt(newSubTotal, i, 3);
					itemExists = true;
					break;
				}
			}
			if (!itemExists)
			{
				double subtotal = subTotal(member,quantity * price);
				model.addRow(new Object[] { productName, quantity, price, subtotal });
			}
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(null, "請輸入正確的數字！");
		}
	}
	
	public static Double allPrice(JTable orderView,Member member,double allSubTotal)
	{
		DefaultTableModel model = (DefaultTableModel) orderView.getModel();
	    for (int i = 0; i < model.getRowCount(); i++) {
	        double rowSubtotal = Double.parseDouble(model.getValueAt(i, 3).toString());
	        allSubTotal += rowSubtotal;
	    }
	    
		if(member!=null)
		{
			if(allSubTotal>8000) allSubTotal=allSubTotal*0.65;
		}else {
			if(allSubTotal>7500) allSubTotal=allSubTotal*0.9;
		}
		
		return allSubTotal;
	}
	
	public static Double subTotal(Member member,double subtotal)
	{
		if(member!=null)
		{
			subtotal=subtotal*0.8;
		}
	
		return subtotal;
	}
	
	/**結算訂單**/
	public static List<String> custOrderCheck(JTable orderView,Member member) {
		List<String> cust = new ArrayList<>();
		cust.add("預計購買商品如下，\n");
		cust.add("商品名稱：　　　　　　　　購買數量：　　　　　　商品小計價格：\r\n");

		DefaultTableModel model = (DefaultTableModel) orderView.getModel();
		int rowCount = model.getRowCount();
		double allPrice = 0;
		for (int i = 0; i < rowCount; i++) {
			String name = model.getValueAt(i, 0).toString(); // 商品名稱
			String quantity = model.getValueAt(i, 1).toString(); // 購買數量
			String price = model.getValueAt(i, 2).toString();// 商品單價價格
			Double pricaCount = (Double.parseDouble(price)) * (Integer.parseInt(quantity));
			pricaCount=subTotal(member,pricaCount);
			allPrice = allPrice + pricaCount;
			String formatted = String.format("%-20s\t%-10s\t%-10s", name, quantity, pricaCount.toString());
			cust.add(formatted + "\r\n");
		}
		allPrice=allPrice(orderView,member,allPrice);
		cust.add("預計商品總金額：" + allPrice + "元");
		return cust;
	}
	
	/**結算時自動將所有東西全部裝進去OrderAll、OrderItem**/
	public static OrderData packOrder(Member member, JTable orderTable) 
	{
		DefaultTableModel model = (DefaultTableModel) orderTable.getModel();
	    int rowCount = model.getRowCount();
	    if(rowCount==0) return null;
	    double totalPrice = 0;
	    List<OrderItem> orderItems = new ArrayList<>();
	    for (int i = 0; i < rowCount; i++) {
	        String productName = model.getValueAt(i, 0).toString();
	        int quantity = Integer.parseInt(model.getValueAt(i, 1).toString());
	        double unitPrice = Integer.parseInt(model.getValueAt(i, 2).toString());
	        double subTotal = quantity * unitPrice;
	        subTotal=subTotal(member,subTotal);
	        OrderItem item = new OrderItem();
		    item.setProductNumber(new ProductServiceImpl().takeProductNumber(productName));
		    item.setProductQuantity(quantity);
		    item.setOrderProductPrice(subTotal);
		    orderItems.add(item);
		    totalPrice += subTotal;
	    }
	    totalPrice=allPrice(orderTable,member,totalPrice);
	    OrderAll orderAll = new OrderAll();
	    orderAll.setMemberNumber(member.getMemberNumber());
	    orderAll.setOrderDate(Date.valueOf(LocalDate.now()));
	    orderAll.setOrderSum(totalPrice);
	    return new OrderData(orderAll,orderItems);
	}
	
	/** 檢查購物車清單有沒有商品 **/
	public static boolean checkOrderNotEmpty(JTable table) {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		if (model.getRowCount() == 0) {
			JOptionPane.showMessageDialog(null, "請先選購商品。", "提醒", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		return true;
	}

	/** 給顧客查看訂單內容 **/
	public static int showOrderConfirmDialog(List<String> summary) {
		StringBuilder msg = new StringBuilder();
		for (String line : summary) {
			msg.append(line).append("\n");
		}

		return JOptionPane.showConfirmDialog(null, msg.toString(), "確認購買商品", JOptionPane.YES_NO_CANCEL_OPTION,
				JOptionPane.INFORMATION_MESSAGE);
	}

	/**紀錄資料庫的同時轉出txt檔案**/
	public static void saveOrderIfConfirmed(int result, JTable table,String memberNumber, List<String> details,OrderAll orderAll,List<OrderItem> orderItem) {
		if (result == JOptionPane.YES_OPTION) {
			boolean saved = false;
			if(details!=null)
			{
				new OrderAllServiceImpl().addOrder(orderAll,orderItem);
				saved=true;
			}
			if (saved) {
				int saveFile = JOptionPane.showConfirmDialog(null, "是否要儲存訂購明細為文字檔？", "儲存訂單明細",
						JOptionPane.YES_NO_OPTION);

				if (saveFile == JOptionPane.YES_OPTION) {
					FileTool.saveStringListToFile(details, memberNumber);
				    JOptionPane.showMessageDialog(null, "訂單明細已儲存。");
				}
			} else {
				JOptionPane.showMessageDialog(null, "資料庫儲存失敗，請稍後再試。", "錯誤", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	/**取消訂單時會先記錄起來**/
	public static void backupOrderIfCancelled(int result, List<String> details,String memberNumber) {
		if (result == JOptionPane.CANCEL_OPTION) {
			FileTool.saveStringListToFile(details, memberNumber);
			JOptionPane.showMessageDialog(null, "已儲存訂單備份，下次可再次查閱。", "提醒", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	/**最後訂單的總步驟濃縮**/
	public static void processOrder(JTable table, Member member,OrderData orderData) {
	    if (!checkOrderNotEmpty(table)) {
	        JOptionPane.showMessageDialog(null, "請先選購產品", "提醒", JOptionPane.WARNING_MESSAGE);
	        return;
	    }

	    List<String> summary = custOrderCheck(table,member);		
		int result = showOrderConfirmDialog(summary);
		OrderAll orderAll=orderData.getOrderall();
		List<OrderItem> items=orderData.getItems();
		
    	saveOrderIfConfirmed(result, table,member.getMemberNumber(), summary, orderAll,items);
	  }
	
	public static void showOrder(JTextArea textareaName,Member member)
	{
		String number=new MemberServiceImpl().getMemberNumber(member.getMemberName());
		StringBuilder orderView = new StringBuilder();
		List<OrderAll> orderList = new OrderAllServiceImpl().memberOrderView(number);
    	String productName=null;
    	orderView.append("目前為"+member.getMemberName()+"會員所屬的訂單有：\n");
	    for (OrderAll order : orderList) {
	        Member memberOne=new MemberServiceImpl().userSomeView(order.getMemberNumber());
		       orderView
			    .append("收件聯絡人為： ").append(memberOne.getMemberAddress()).append("\n");
		        orderView.append("訂單編號: ").append(order.getOrderNumber()).append("\n")
		                 .append("訂購時間: ").append(order.getOrderDate()).append("\n")
		                 .append("======================================\n");
			    List<OrderItem> orderItemsList = new OrderItemServiceImpl().orderItemView(order.getOrderNumber());
		        for (OrderItem item : orderItemsList) {
		            if (item.getOrderNumber().equals(order.getOrderNumber())) {
		            	productName=new ProductServiceImpl().takeProductNumber(item.getProductNumber());
		                orderView.append("購買商品: ").append(productName).append("\n")
		                         .append("數量: ").append(item.getProductQuantity()).append("\n")
		                         .append("小計: ").append(item.getOrderProductPrice()).append("元\n")
		                         .append("--------------------------------------\n");
		            }
		        }
		        orderView.append("總計: ").append(order.getOrderSum()).append("\n");
			    orderView.append("======================================\n")
			    .append("收件地址為： ").append(memberOne.getMemberAddress()).append("\n")
			    .append("收件電話為： ").append(memberOne.getMemberPhone()).append("\n")
			    .append("======================================\n\n");
		    }
	    textareaName.setText(orderView.toString());
	}
	
	public static void showOrder(JTextArea textareaName,String name)
	{
		String number=new MemberServiceImpl().getMemberNumber(name);
		Member memberSome=new MemberServiceImpl().userSomeView(number);
		StringBuilder orderView = new StringBuilder();
		List<OrderAll> orderList = new OrderAllServiceImpl().userOrderView(number);
    	String productName=null;
    	orderView.append(name+"的訂單有：\n");
	    for (OrderAll order : orderList) {
	        orderView.append("訂單編號: ").append(order.getOrderNumber()).append("\n")
	                 .append("訂購時間: ").append(order.getOrderDate()).append("\n")
	                 .append("======================================\n");
		    List<OrderItem> orderItemsList = new OrderItemServiceImpl().orderItemView(order.getOrderNumber());
	        for (OrderItem item : orderItemsList) {
	            if (item.getOrderNumber().equals(order.getOrderNumber())) {
	            	productName=new ProductServiceImpl().takeProductNumber(item.getProductNumber());
	                orderView.append("購買商品: ").append(productName).append("\n")
	                         .append("數量: ").append(item.getProductQuantity()).append("\n")
	                         .append("小計: ").append(item.getOrderProductPrice()).append("元\n")
	                         .append("--------------------------------------\n");
	            }
	        }
	        orderView.append("總計: ").append(order.getOrderSum()).append("\n");
	    }
	    orderView.append("======================================\n")
	    .append("收件地址為： ").append(memberSome.getMemberAddress()).append("\n")
	    .append("收件電話為： ").append(memberSome.getMemberPhone()).append("\n");
	    textareaName.setText(orderView.toString());
	}
	public static void showOrder(JTextArea textareaName,String name,String phone)
	{
		Member cust=new MemberServiceImpl().freeUserUse(name, phone);
		StringBuilder orderView = new StringBuilder();
		List<OrderAll> orderList = new OrderAllServiceImpl().memberOrderView(cust.getMemberNumber());
    	String productName=null;
    	orderView.append(name+"的訂單有：\n");
	    for (OrderAll order : orderList) {
	        orderView.append("訂單編號: ").append(order.getOrderNumber()).append("\n")
	                 .append("訂購時間: ").append(order.getOrderDate()).append("\n")
	                 .append("======================================\n");
		    List<OrderItem> orderItemsList = new OrderItemServiceImpl().orderItemView(order.getOrderNumber());
	        for (OrderItem item : orderItemsList) {
	            if (item.getOrderNumber().equals(order.getOrderNumber())) {
	            	productName=new ProductServiceImpl().takeProductNumber(item.getProductNumber());
	                orderView.append("購買商品: ").append(productName).append("\n")
	                         .append("數量: ").append(item.getProductQuantity()).append("\n")
	                         .append("小計: ").append(item.getOrderProductPrice()).append("元\n")
	                         .append("--------------------------------------\n");
	            }
	        }
	        orderView.append("總計: ").append(order.getOrderSum()).append("\n");
	    }
	    orderView.append("======================================\n")
	    .append("收件地址為： ").append(cust.getMemberAddress()).append("\n")
	    .append("收件電話為： ").append(cust.getMemberPhone()).append("\n");
	    textareaName.setText(orderView.toString());
	}
}
