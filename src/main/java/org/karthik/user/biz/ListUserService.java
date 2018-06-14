package org.karthik.user.biz;

import java.util.List;

import javax.ws.rs.core.Response;

import org.karthik.user.biz.service.handlers.Service;
import org.karthik.user.dao.UserDAO;
import org.karthik.user.entity.UserDE;
import org.karthik.web.utils.ResponseBuilder;

public class ListUserService implements Service<Void>{
	
	public Response process(Void v) {
		try {
			final List<UserDE> de = new UserDAO().listUsers();
			if (de != null && !de.isEmpty()) {
				return ResponseBuilder.ok(de);
			} else {
				return ResponseBuilder.okOrUserError("Invalid/Null user object passed");
			}
		} catch (Exception e) {
			return ResponseBuilder.serverError("Error while crreating the user..."+ e.getMessage());
		}
	}

}
