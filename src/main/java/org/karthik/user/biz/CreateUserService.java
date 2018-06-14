package org.karthik.user.biz;

import javax.ws.rs.core.Response;

import org.karthik.user.biz.service.handlers.Service;
import org.karthik.user.bo.UserBO;
import org.karthik.user.dao.UserDAO;
import org.karthik.user.entity.UserDE;
import org.karthik.web.utils.ResponseBuilder;

public class CreateUserService implements Service<UserBO> {

	public Response process(UserBO user) {
		try {
			final UserDE de = new UserDAO().create(user);
			if (de != null) {
				return ResponseBuilder.ok(user);
			} else {
				return ResponseBuilder.okOrUserError("Invalid/Null user object passed");
			}
		} catch (Exception e) {
			return ResponseBuilder.serverError("Error while crreating the user..." + e.getMessage());
		}
	}

}
