package com.bank.bankapp.account;

import java.util.Date;

public class Transaction {

	private double purchaseAmount;
	private Date purchasedate;
	private String description;
	private TransactionType type;
	
	/**
	 * @return the purchaseAmount
	 */
	public double getPurchaseAmount() {
		return purchaseAmount;
	}
	/**
	 * @param purchaseAmount the purchaseAmount to set
	 */
	public void setPurchaseAmount(double purchaseAmount) {
		this.purchaseAmount = purchaseAmount;
	}
	/**
	 * @return the purchasedate
	 */
	public Date getPurchasedate() {
		return purchasedate;
	}
	/**
	 * @param purchasedate the purchasedate to set
	 */
	public void setPurchasedate(Date purchasedate) {
		this.purchasedate = purchasedate;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the type
	 */
	public TransactionType getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(TransactionType type) {
		this.type = type;
	}
	
	
}
