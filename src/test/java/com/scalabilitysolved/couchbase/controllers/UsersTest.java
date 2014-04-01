package com.scalabilitysolved.couchbase.controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UsersTest {

	private static final String SOURCE = "facebook";
	private Users users;
	@Mock
	private UserDao userDao;

	@Before
	public void setUp() {
		this.users = new Users(userDao);
	}

	@Test(expected = WebApplicationException.class)
	public void testCreateUserWhenSourceIsNull() throws Exception {
		this.users.createUser(null);
	}

	@Test
	public void testUserIsCreated() {
		when(userDao.saveUser(anyString())).thenReturn(new User(SOURCE));
		Response user = this.users.createUser(SOURCE);
		assertNotNull(user.getEntity());
		assertEquals(201, user.getStatus());
	}

}
