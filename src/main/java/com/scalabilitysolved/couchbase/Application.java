package com.scalabilitysolved.couchbase;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;

import com.scalabilitysolved.couchbase.controllers.Users;

public class Application extends ResourceConfig {

	public Application() {
		this.register(RequestContextFilter.class);
		this.register(Users.class);
	}

}
