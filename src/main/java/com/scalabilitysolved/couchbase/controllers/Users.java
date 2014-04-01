package com.scalabilitysolved.couchbase.controllers;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.scalabilitysolved.couchbase.dao.UserDao;
import com.scalabilitysolved.couchbase.domain.User;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class Users {

	private UserDao userDao;

	public Users(UserDao userDao) {
		this.userDao = userDao;
	}

	@POST
	public Response createUser(String source) {
		if (source == null) {
			throw new WebApplicationException(Response.status(Status.BAD_REQUEST).entity("Source cannot be null").build());
		}

		User user = this.userDao.saveUser(source);

		return Response.status(Status.CREATED).entity(user).build();
	}
}
