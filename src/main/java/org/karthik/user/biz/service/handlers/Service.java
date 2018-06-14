package org.karthik.user.biz.service.handlers;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

public interface Service<E> {
	
	@SuppressWarnings("rawtypes")
	public Service<?> NOT_IMPLEMENTED_SERVICE =  new Service() {

		@Override
		public Response process(Object e) {
			return Response.status(Status.NOT_IMPLEMENTED).build();
		}
		
	};
	
	public Response process(E e);

}
