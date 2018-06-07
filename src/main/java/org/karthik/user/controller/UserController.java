package org.karthik.user.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.karthik.user.bo.UserBO;
import org.karthik.user.dao.UserDAO;

@Path("/user")
public class UserController {
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/create")
	public Response create(final UserBO user) {
		new UserDAO().create(user);
		return Response.status(Status.OK).entity(user).build();
	}
	
	

}
