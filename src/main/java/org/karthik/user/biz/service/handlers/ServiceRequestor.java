package org.karthik.user.biz.service.handlers;

import org.karthik.common.logger.Log;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ServiceRequestor {

	@SuppressWarnings("unchecked")
	public static final <E> Service<E> requestService(String id) {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		try {
			return (Service<E>) context.getBean(id);
		} catch (BeansException be) {
			Log.info(ServiceRequestor.class.getSimpleName(), "requestService", "Exception while receiveing Object for service " + id, be);
			return (Service<E>) Service.NOT_IMPLEMENTED_SERVICE;
		}
	}
}
