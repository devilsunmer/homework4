package model;

import java.io.Serializable;
import java.util.List;

import service.impl.MemberServiceImpl;
import service.impl.ProductServiceImpl;

public class OrderData implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	OrderAll orderall;
	List<OrderItem> items;
	Member member;
	public OrderData() {
		super();
	}
	public OrderData(OrderAll orderall, List<OrderItem> items) {
		super();
		this.orderall = orderall;
		this.items = items;
		if (this.orderall.memberNumber != null) {
            this.member = new MemberServiceImpl().userSomeView(this.orderall.memberNumber);
        } else {
            this.member = new Member(); // 或 null，但使用時需檢查
        }
	}
	public OrderAll getOrderall() {
		return orderall;
	}
	public void setOrderall(OrderAll orderall) {
		this.orderall = orderall;
	}
	public List<OrderItem> getItems() {
		return items;
	}
	public void setItems(List<OrderItem> items) {
		this.items = items;
	}
	
	public String getOrderNumber() { return orderall.getOrderNumber(); }
    public String getMemberName() { return member.memberName; }
    public String getMemberAddress() { return member.memberAddress; }
    public String getMemberPhone() { return member.memberPhone; }
    public String getProductNumber() {
        return items != null && !items.isEmpty() ? items.get(0).getProductNumber() : "";
    }

    public String getProductName() {
        if (items != null && !items.isEmpty()) {
            return new ProductServiceImpl().takeProductName(items.get(0).getProductNumber());
        }
        return "";
    }

    public Integer getProductQuantity() {
        return items != null && !items.isEmpty() ? items.get(0).getProductQuantity() : 0;
    }

    public Double getOrderProductPrice() {
        return items != null && !items.isEmpty() ? items.get(0).getOrderProductPrice() : 0.0;
    }

    public Double getOrderSum() { return orderall.getOrderSum(); }
	public void setMember(Member member) {
		this.member = member;		
	}
	public Member getMember() {
		return member;
	}
	

}
