package models.entities;

import java.util.Date;

public class Order {

	private int registerID;
	private Date date;
	private long idMember;
	private int codeProduct;
	private int amount;
	private int state;
	
	public Order(int registerID, Date date, long idMember, int codeProduct,
			int amount, int state) {
		this.registerID = registerID;
		this.date = date;
		this.idMember = idMember;
		this.codeProduct = codeProduct;
		this.amount = amount;
		this.state = state;
	}
	
	public Object[] getData() {
		return new Object[]{registerID, date, idMember, codeProduct, amount, state};
	}

	@Override
	public String toString() {
		return "Order [registerID=" + registerID + ", date=" + date
				+ ", idMember=" + idMember + ", codeProduct=" + codeProduct
				+ ", amount=" + amount + ", state=" + state + "]";
	}

	public int getRegisterID() {
		return registerID;
	}

	public void setRegisterID(int registerID) {
		this.registerID = registerID;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public long getIdMember() {
		return idMember;
	}
	public void setIdMember(long idMember) {
		this.idMember = idMember;
	}

	public int getCodeProduct() {
		return codeProduct;
	}

	public void setCodeProduct(int codeProduct) {
		this.codeProduct = codeProduct;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}
}