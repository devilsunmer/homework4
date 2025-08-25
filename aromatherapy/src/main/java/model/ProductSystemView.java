package model;

import java.io.Serializable;

public class ProductSystemView implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String number;
	String name;
	Integer in;
	Integer out;
	Integer nowHave;
	
	public ProductSystemView() {
		super();
	}
	
	public ProductSystemView(String number, String name, Integer in, Integer out, Integer nowHave) {
		super();
		this.number = number;
		this.name = name;
		this.in = in;
		this.out = out;
		this.nowHave = nowHave;
	}


	public String getNumber() {
		return number;
	}
	public String getName() {
		return name;
	}
	public Integer getIn() {
		return in;
	}
	public Integer getOut() {
		return out;
	}
	public Integer getNowHave() {
		return nowHave;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setIn(Integer in) {
		this.in = in;
	}

	public void setOut(Integer out) {
		this.out = out;
	}

	public void setNowHave(Integer nowHave) {
		this.nowHave = nowHave;
	}
	
	
	
	

}
