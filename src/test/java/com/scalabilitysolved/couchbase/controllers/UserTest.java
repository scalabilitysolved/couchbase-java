package com.scalabilitysolved.couchbase.controllers;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class UserTest {

	private static final String USER_SOURCE = "facebook";
	private User user;

	@Test
	public void testGetSource() {
		user = new User(USER_SOURCE);
		assertEquals(USER_SOURCE, user.getSource());
	}

}
