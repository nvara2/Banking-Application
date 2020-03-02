/**
 * 
 */
package com.bank.bankapp.account;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.bank.bankapp.user.User;

/**
 * @author vpns3
 *
 */
public class AccountTest {

	@Test
	public void testGetterSetters(){
		User user = new User();
		Account account = new Account();
		account.setUser(user);
		assertEquals(account.getUser(), user);
		account.setAccountNumber(123456789);
		assertTrue(account.getAccountNumber() == 123456789);
		account.setAccountType(1);
		assertTrue(account.getAccountType() == 1);
		account.setAvailableBalance(10.15);
		assertTrue(account.getAvailableBalance() == 10.15);
	}
	
	@Test
	public void testCreate() {
		Account account = new Account();
		boolean status = account.create("name", 1);
		assertTrue(status);
	}
}
