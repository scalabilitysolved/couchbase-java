package com.scalabilitysolved.couchbase.dao;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;
import net.spy.memcached.internal.OperationFuture;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.couchbase.client.CouchbaseClient;
import com.google.gson.Gson;
import com.scalabilitysolved.couchbase.CouchbaseConnector;
import com.scalabilitysolved.couchbase.domain.User;

@RunWith(MockitoJUnitRunner.class)
public class UserDaoTest {

	private static final String SOURCE = "FB";
	private static final String ID = "user::0";
	@Mock
	private CouchbaseConnector couchbaseConnector;
	@Mock
	private CouchbaseClient couchbase;
	@Mock
	private OperationFuture<Boolean> operationFuture;

	private UserDao userDao;
	private Gson gson;

	@Before
	public void setUp() {
		when(couchbaseConnector.getClient()).thenReturn(couchbase);
		userDao = new UserDao(couchbaseConnector);
		gson = new Gson();
	}

	@Test
	public void testSaveUser() throws Exception {
		when(couchbase.incr(anyString(), anyInt())).thenReturn(0l);
		when(couchbase.add(anyString(), anyObject())).thenReturn(operationFuture);
		String userJson = userBuilder();
		when(couchbase.get(ID)).thenReturn(userJson);

		String user = this.userDao.saveUser(SOURCE);
		assertNotNull(user);
	}

	private String userBuilder() {
		User user = new User(ID, SOURCE);
		return gson.toJson(user);
	}

}
