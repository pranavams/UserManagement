package org.karthik.exception;

import javax.persistence.EntityTransaction;

import org.hibernate.HibernateException;

public class ExceptionHandler {
	
	public void throwServerException(final String CLASS_NAME, final Exception e) {
		throw new ServerException(e.getMessage(), e);
	}
	
	public void throwBusinessException(final String CLASS_NAME, final String exceptionMessage, final Exception e) {
		throw new ServerException(e.getMessage(), e);
	}

	public void rollbackAndThrowHibernateException(final String CLASS_NAME, final EntityTransaction transaction, final HibernateException he) {
		transaction.rollback();
		throw new DatabaseException(he.getMessage(), he);
	}
}
