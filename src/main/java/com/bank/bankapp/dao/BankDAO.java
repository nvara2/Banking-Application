package com.bank.bankapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.bank.bankapp.account.Account;
import com.bank.bankapp.user.User;

public class BankDAO {

	public boolean saveAccount(Account account, User user) {
		boolean status = false;
		Connection connection = ConnectionUtil.getConnection();
		String idColumn[] = { "id" };
		String sql = "INSERT INTO Users (first_name, last_name, username, password) VALUES('" + user.getFirstName()
				+ "', '" + user.getLastName() + "' , '" + user.getUsername() + "', '" + user.getPassword() + "');";
		// System.out.println("SQL: " + sql);
		PreparedStatement statement;
		try {
			statement = connection.prepareStatement(sql, idColumn);
			statement.execute();
			ResultSet rs = statement.getGeneratedKeys();

			if (rs.next()) {
				long id = rs.getLong(1);
				String accountInsertSql = "INSERT INTO Account (user_info, accountnumber, "
						+ "availablebalance, openingbalance, aid) VALUES(" + id + ", " + account.getAccountNumber()
						+ ", 0, 0, " + account.getAccountType() + ");";
			//	System.out.println("Account SQL: " + sql);
				statement = connection.prepareStatement(accountInsertSql);
				statement.execute();
				status = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return status;
	}

	public int accessaccount(String username, String password) {
		int userId = -1;
		Connection connection = ConnectionUtil.getConnection();
		String sql = "select *  from users where username ='" + username + "' and password ='" + password + "';";
		//System.out.println("User SQL: " + sql);
		PreparedStatement statement;
		try {
			statement = connection.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				userId = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return userId;

	}

	public double getaccountBalance(int userId) {
		Connection connection = ConnectionUtil.getConnection();
		String sql = "select * from account where user_info = ('" + userId + "');";
		PreparedStatement statement;
		double availableBalance = 0;
		try {
			statement = connection.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				availableBalance = rs.getFloat(4);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return availableBalance;
	}

	public long getaccountNumber(int userId) {
		Connection connection = ConnectionUtil.getConnection();
		String sql = "select * from account where user_info = ('" + userId + "');";
		PreparedStatement statement;
		long accountnumber = 0;
		try {
			statement = connection.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				accountnumber = rs.getLong(3);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return accountnumber;
	}

	public boolean updateBalance(int userId, double newBalance) {
		Connection connection = ConnectionUtil.getConnection();
		String sql = "UPDATE account SET availableBalance=" + newBalance + " where user_info = ('" + userId + "');";
		boolean status = false;
		PreparedStatement statement;
		double availableBalance = 0;
		try {
			statement = connection.prepareStatement(sql);
			statement.execute();
			status = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return status;
	}

	public boolean checkAccount(long transfernumber) {
		Connection connection = ConnectionUtil.getConnection();
		String sql = "select * from account where accountnumber = ('" + transfernumber + "');";
		PreparedStatement statement;
		boolean status = false;

		try {
			statement = connection.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				status = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return status;
	}

	public boolean updatedtransferBalance(long transfernumber, double amount) {
		Connection connection = ConnectionUtil.getConnection();
		String sql = "UPDATE account SET availableBalance= availablebalance + " + amount + " where accountnumber = ('"
				+ transfernumber + "');";
		//System.out.println("updatetransferbalance: " + sql);
		boolean status = false;
		PreparedStatement statement;
		try {
			statement = connection.prepareStatement(sql);
			statement.execute();
			status = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return status;
	}
	
	public boolean closeAccount(int userId, int accountType) {
		boolean status = false;
		Connection connection = ConnectionUtil.getConnection();
		String idColumn[] = { "id" };
		String sql = "DELETE FROM account WHERE user_info=" + userId + " AND aid=" + accountType + ";";
	   // System.out.println("SQL: " + sql);
		PreparedStatement statement;
		try {
			statement = connection.prepareStatement(sql, idColumn);
			statement.execute();
            status = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
		//	e.printStackTrace();
			System.out.println("Unable to find the account");
		}

		return status;
	}


}
