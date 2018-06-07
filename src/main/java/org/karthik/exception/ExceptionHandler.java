package org.karthik.exception;

import javax.persistence.EntityTransaction;

public class ExceptionHandler {
	
	public void rollbackAndThrowHibernateException(final String className, final EntityTransaction transaction, final Exception e) {
		transaction.rollback();
		throw new DatabaseException(e.getMessage(), e);
	}

}
