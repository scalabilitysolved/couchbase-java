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

import com.google.gson.Gson;
import com.scalabilitysolved.couchbase.dao.UserDao;
import com.scalabilitysolved.couchbase.domain.User;

@RunWith(MockitoJUnitRunner.class)
public class UsersTest {

	private static final String SOURCE = "facebook";
	private static final String ID = "user::1";
	private Users users;
	private Gson gson;

	@Mock
	private UserDao userDao;

	@Before
	public void setUp() {
		this.users = new Users(userDao);
		gson = new Gson();
	}

	@Test(expected = WebApplicationException.class)
	public void testCreateUserWhenSourceIsNull() throws Exception {
		this.users.createUser(null);
	}

	@Test
	public void testUserIsCreated() {
		when(userDao.saveUser(anyString())).thenReturn(generateUser());
		Response user = this.users.createUser(SOURCE);
		assertNotNull(user.getEntity());
		assertEquals(201, user.getStatus());
	}

	private String generateUser() {
		return gson.toJson(new User(ID, SOURCE));
	}

}
