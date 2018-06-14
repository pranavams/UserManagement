package org.karthik.user.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.karthik.user.biz.service.handlers.ServiceRequestor;
import org.karthik.user.bo.UserBO;

@Path("/user")
public class UserController {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/login")
	public Response loginUser(){
		return Response.status(Status.OK).entity("User successfully logged in..").build();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/create")//
	public Response create(final UserBO user) {
		return ServiceRequestor.requestService("createUser").process(user);
	}
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/update")
	public Response update(@HeaderParam("authorization") String authString, final UserBO user) {
		return ServiceRequestor.requestService("updateUser").process(user);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/list")
	public Response listUsers() {
		return ServiceRequestor.requestService("listUser").process(null);
	}
	
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/deleteUser")//
	public Response deleteUser(final UserBO user) {
		return ServiceRequestor.requestService("deleteUser").process(user);
	}
}
