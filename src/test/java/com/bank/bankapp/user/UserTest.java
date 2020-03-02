/**
 * 
 */
package com.bank.bankapp.user;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.bank.bankapp.account.Account;

/**
 * @author vpns3
 *
 */
public class UserTest {

	@Test
	public void testGetterSetters() {
		User user = new User();
		user.setFirstName("somefirst");
		assertEquals(user.getFirstName(), "somefirst");
		user.setLastName("somelast");
		assertEquals(user.getLastName(), "somelast");
		user.setPassword("pwd");
		assertEquals(user.getPassword(), "pwd");
		user.setUsername("usrnm");
		assertEquals(user.getUsername(), "usrnm");
	}
	
	@Test
	public void testAddAccount() {
		User user = new User();
		Account account = new Account();
		user.addAccount(account);
		assertTrue(user.getAccounts().size() == 1);
		user.removeAccount(account);
		assertTrue(user.getAccounts().size() == 0);
		
	}
}
