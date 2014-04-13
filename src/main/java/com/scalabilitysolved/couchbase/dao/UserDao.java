package com.scalabilitysolved.couchbase.dao;

import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.couchbase.client.CouchbaseClient;
import com.google.gson.Gson;
import com.scalabilitysolved.couchbase.CouchbaseConnector;
import com.scalabilitysolved.couchbase.domain.User;

public class UserDao {

	static final Logger LOG = LoggerFactory.getLogger(UserDao.class);

	private CouchbaseClient couchbase;
	private Gson gson;

	public UserDao(CouchbaseConnector couchbaseConnector) {
		this.couchbase = couchbaseConnector.getClient();
		gson = new Gson();
	}

	public String saveUser(String source) {

		String key = generateKey();
		User user = new User(key, source);

		try {
			this.couchbase.add(key, gson.toJson(user)).get();
		} catch (InterruptedException | ExecutionException e) {
			throw new RuntimeException(e);
		}

		String userJson = (String) this.couchbase.get(key);
		LOG.debug("Generated new user with id: {}", key);

		return userJson;
	}

	private String generateKey() {
		long id = this.couchbase.incr("user::count", 1, 0);
		return "user::" + id;
	}
}
