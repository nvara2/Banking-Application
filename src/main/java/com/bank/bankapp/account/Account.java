package com.bank.bankapp.account;

import java.util.List;
import java.util.Random;

import com.bank.bankapp.user.User;

public class Account {

	private User user;
	private long accountNumber;
	private double availableBalance;
	private double openingBalance;
	private List<Transaction> transactions;
	private int accountType;

	/**
	 * @return the accountNumber
	 */
	public long getAccountNumber() {
		return accountNumber;
	}

	/**
	 * @param accountNumber the accountNumber to set
	 */
	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}

	/**
	 * @return the availableBalance
	 */
	public double getAvailableBalance() {
		return availableBalance;
	}

	/**
	 * @param availableBalance the availableBalance to set
	 */
	public void setAvailableBalance(double availableBalance) {
		this.availableBalance = availableBalance;
	}

	/**
	 * @return the openingBalance
	 */
	public double getOpeningBalance() {
		return openingBalance;
	}

	/**
	 * @param openingBalance the openingBalance to set
	 */
	public void setOpeningBalance(double openingBalance) {
		this.openingBalance = openingBalance;
	}

	/**
	 * @return the transactions
	 */
	public List<Transaction> getTransactions() {
		return transactions;
	}

	/**
	 * @param transactions the transactions to set
	 */
	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

	
	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @return the accountType
	 */
	public int getAccountType() {
		return accountType;
	}

	/**
	 * @param accountType the accountType to set
	 */
	public void setAccountType(int accountType) {
		this.accountType = accountType;
	}

	public boolean create(String name, int accountType) {
		boolean status = true;
		this.accountNumber = createAccount();
		this.accountType = accountType;
		// System.out.println("Account Created: " + this.accountNumber + " for " +
		// name);
		return status;
	}

	private long createAccount() {
		Random randomAccountNumber = new Random();
		char[] digits = new char[12];
		digits[0] = (char) (randomAccountNumber.nextInt(9) + '1');
		for (int i = 1; i < 12; i++) {
			digits[i] = (char) (randomAccountNumber.nextInt(10) + '0');
		}
		return Long.parseLong(new String(digits));
	}
}
