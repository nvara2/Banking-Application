package com.bank.bankapp;

import java.sql.ResultSet;
import java.util.Scanner;

import com.bank.bankapp.account.Account;
import com.bank.bankapp.dao.BankDAO;
import com.bank.bankapp.user.User;

public class BankApp {

	private Scanner scanner = new Scanner(System.in);
	private User user;
	BankDAO dao = new BankDAO();

	public void createAccount() {
		System.out.println("Enter first name");
		boolean valid = false;
		String firstName = "";
		while (!valid) {
			firstName = scanner.next();
			if (isValidString(firstName)) {
				valid = true;
			} else {
				System.out.println("That's not a valid name, please try again! ");
			}
		}
		System.out.println("Your first name is, " + firstName + ", Thank you");

		System.out.println("Enter last name");
		String lastName = "";
		valid = false;
		while (!valid) {
			lastName = scanner.next();
			if (isValidString(lastName)) {
				valid = true;
			} else {
				System.out.println("That's not a valid name, please try again! ");
			}

		}
		System.out.println("Your last name is " + lastName + ", thank you");

		System.out.println("Enter a username");
		String username = "";
		valid = false;
		while (!valid) {
			username = scanner.next();
			if (isValidCredential(username)) {
				valid = true;
			} else {
				System.out.println("Invalid Username!");
			}
		}
		System.out.println("Your username has been saved, thank you!");

		System.out.println("Enter a password");
		String password = "";
		valid = false;
		while (!valid) {
			password = scanner.next();
			if (isValidCredential(password)) {
				valid = true;
			} else {
				System.out.println("Password is invalid");
			}
			System.out.println("Your password has been saved, thank you!");
		}

		user = new User(firstName, lastName, username, password);
		System.out.println("What account type would you like to open ?");
		System.out.println("Enter 1 for Checking and 2 for Savings Account");
		int accountType;
		valid = false;
		Account account = new Account();
		String name = firstName + " " + lastName;
		while (!valid) {
			accountType = scanner.nextInt();
			String type = "";
			switch (accountType) {
			case 1:
			case 2:
				boolean status = account.create(name, accountType);
				status = dao.saveAccount(account, user);
				if (status) {
					System.out.println("Thank you, your account has been created. The Sun shines and so will your banking experience with the one and only Sun Bank!");
				}
				valid = true;
				break;
			default:
				System.out.println("Invalid account type");
				break;
			}
		}
	}

	private boolean isValidString(String str) {
		return (str != null && !str.equals("") && str.matches("^[a-zA-Z]*$"));
	}

	private boolean isValidCredential(String str1) {
		return (str1 != null && !str1.equals(""));
	}

	private void logIn() {
		// Scanner scanner = new Scanner(System.in);
		boolean valid = false;
		System.out.println("Enter your username");
		String username = "";
		// valid = false;
		while (!valid) {
			username = scanner.next();
			if (isValidCredential(username)) {
				valid = true;
			} else {
				System.out.println("Invalid Username!");
			}

		}
		System.out.println("Enter your password");
		String password = "";
		valid = false;
		while (!valid) {
			password = scanner.next();
			if (isValidCredential(password)) {
				valid = true;
			} else {
				System.out.println("Invalid password!");
			}
		}

		int userId = dao.accessaccount(username, password);
		System.out.println("UserId: " + userId);
		if (userId != -1) {
			System.out.println("Welcome to your account!");
		} else {
			System.out.println("Login Failed due to incorrect username or password!");
		}
		boolean validChoice = false;

		while (!validChoice) {
			System.out.println("How can we help you today? Please select an option below to get started!");
			System.out.println(" 1. Check Balance\n 2. Deposit\n 3. Withdraw\n 4. Transfer\n 0. Logout\n");

			System.out.println("Enter option number");

			int option = scanner.nextInt();

			switch (option) {
			case 1:
				System.out.println("Your request is being processed, please wait.");
				double balance = dao.getaccountBalance(userId);
				System.out.println("Fun fact: Did you know the Sun accounts for 99.86% of the mass in the solar system? ");
				System.out.println("Your available balance is : " + balance);
				
				// validChoice = true;
				break;
			case 2:
				System.out.println("How much would you like to deposit?");
				while (true) {
					double amount = scanner.nextDouble();
					if (amount <= 0) {
						System.out.println("That is a invalid amount to deposit, please try again!");
					} else {
						double newBalance = dao.getaccountBalance(userId);
						newBalance += amount;
						if (dao.updateBalance(userId, newBalance)) {
							System.out.println("Your new balance : " + newBalance);
							System.out.println("Fun fact: Over one million Earth’s could fit inside the Sun.");
							break;
						}
					}
				}
				break;
			// validChoice = true;

			case 3:
				System.out.println("How much would you like to withdraw?");
				while (true) {
					double amount = scanner.nextDouble();
					double accountbalance = dao.getaccountBalance(userId);
					if (amount <= 0 || amount > accountbalance) {
						System.out.println("That is a invalid amount to withdraw, please try again!");
					} else {
						double newBalance = dao.getaccountBalance(userId);
						newBalance -= amount;
						if (dao.updateBalance(userId, newBalance)) {
							System.out.println("Your new balance is : " + newBalance);
							System.out.println("Fun fact: It takes eight minutes for light to reach Earth from the Sun.");
							break;
						}
					}
				}

				// validChoice = true;
				break;
			case 4:
				while (true) {
					System.out.println("Enter account number you want to transfer to");
					long transfernumber = scanner.nextLong();
					if (dao.checkAccount(transfernumber)) {
						System.out.println("How much would you like to transfer to this account?");
						double amount = scanner.nextDouble();
						if (amount > 0) {
							double curBalance = dao.getaccountBalance(userId);
							if (curBalance >= amount) {
								curBalance -= amount;
								if (dao.updatedtransferBalance(transfernumber, amount))
									System.out.println("Your request is being processed!");
								if (dao.updateBalance(userId, curBalance)) {
									System.out.println("Your new balance is : " + curBalance
											+ ", the amount you have requested has been transferred!");
									System.out.println("Fun fact: Temperatures inside the Sun can reach 15 million degrees Celsius!");
									break;
								} else {
									System.out.println(
											"Could not complete your transaction ! Please check your information");
								}
							} else {
								System.out.println("Request could not be processed");
							}

						} else {
							System.out.println("Could not complete your transaction ! There are no sufficient funds");
						}

					} else {
						System.out.println("Account could not be found!");
					}
				}

			case 0:
				System.out.println("Thank you for using Sun Bank, and remember, keep shining like the Sun!");
				validChoice = true;
				break;
			default:
				System.out.println("You picked a option that does not exist! Please try again!");
			}
		}
	}

	private void manageAccount() {

		boolean valid = false;
		System.out.println("Enter your username");
		String username = "";
		valid = false;
		while (!valid) {
			username = scanner.next();
			if (isValidCredential(username)) {
				valid = true;
			} else {
				System.out.println("Invalid Username!");
			}

		}
		System.out.println("Enter your password");
		String password = "";
		valid = false;
		while (!valid) {
			password = scanner.next();
			if (isValidCredential(password)) {
				valid = true;
			} else {
				System.out.println("Invalid password!");
			}
		}

		int userId = dao.accessaccount(username, password);
		System.out.println("UserId: " + userId);
		if (userId != -1) {
			System.out.println("Welcome to your account!");
		} else {
			System.out.println("Login Failed due to incorrect username or password!");
			
		}

		System.out.println("How can we help you today? Please select an option below to get started!");
		boolean validChoice = false;

		while (!validChoice) {
			System.out.println(" 1. Close Account\n 0. Logout\n");

			System.out.println("Enter option number");

			int option = scanner.nextInt();

			switch (option) {
			case 1:
				System.out.println("What account type you want to close.");
				System.out.println(" 1. Checking Account\n 2. Savings Account\n");
				int type = scanner.nextInt();
				if (type == 1 || type == 2) {
					boolean status = dao.closeAccount(userId, type);
					if (status) {
						System.out.println("Successfully closed your account, remember the Sun sets but it is always there and it is just a matter of time till it rises again. The same can be said about Sun bank to help you with your banking needs!");
					} else {
						System.out.println("Error occurred while closing your account");
					}
				} else {
					System.out.println("Invalid option selected");
				}
				break;
			case 0:
				System.out.println("Thank you for using Sun Bank, keep shining like the Sun");
				validChoice = true;
				break;
			default:
				System.out.println("You picked a option that does not exist! Please try again!");
			}

		}
	}

	public void runApp() {

		boolean validChoice = false;

		while (!validChoice) {
			System.out.println("Please select an option below to get started!");
			System.out.println(" 1. Create Account\n 2. Log In\n 3. Manage Account\n 0. Exit\n");

			System.out.println("Enter option number");

			int option = scanner.nextInt();

			switch (option) {
			case 1:
				System.out.println("Let's get started!");
				createAccount();
				// validChoice = true;
				break;
			case 2:
				System.out.println("Let's get logged in!");
				logIn();
				// validChoice = true;
				break;
			case 3:
				System.out.println("Let's get started!");
				manageAccount();
				// validChoice = true;
				break;
			case 0:
				System.out.println("Thank you for using Sun Bank, keep shining like the Sun");
				validChoice = true;
				break;
			default:
				System.out.println("You picked a option that does not exist! Please try again!");
			}
		}

	}

	public static void main(String[] args) {

		System.out.println("Welcome To Sun Bank, where your banking future is as bright as the Sun!");
		BankApp app = new BankApp();
		app.runApp();
	}

}
