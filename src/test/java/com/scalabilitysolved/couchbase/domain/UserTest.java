package com.scalabilitysolved.couchbase.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

public class UserTest {

	private static final String USER_SOURCE = "facebook";
	private static final String ID = "user::1";
	private static final String TYPE = "user";
	private User user;

	@Before
	public void setUp() {
		user = new User(ID, USER_SOURCE);
	}

	@Test
	public void testGetSource() {
		assertEquals(USER_SOURCE, user.getSource());
	}

	@Test
	public void testGetId() {
		assertEquals(ID, user.getId());
	}

	@Test
	public void testGetCreationDate() {
		assertNotNull(user.getCreationDate());
	}

	@Test
	public void testGetType() {
		assertEquals(TYPE, user.getType());
	}

}
