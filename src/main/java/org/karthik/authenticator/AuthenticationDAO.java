package org.karthik.authenticator;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.karthik.exception.ExceptionHandler;
import org.karthik.hibernate.HibernateUtil;
import org.karthik.hibernate.UserHQLConstants;
import org.karthik.user.entity.UserDE;

public class AuthenticationDAO{
	
	public static final String CLASS_NAME = AuthenticationDAO.class.getSimpleName();
	
	public boolean authenticateUser(String emailId, String password){
		boolean isAuthenticatedUser = false;
		UserDE de = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try{
			tx = (Transaction) session.beginTransaction();
			String hql = UserHQLConstants.USER_VALIDATION;
			@SuppressWarnings("rawtypes")
			Query query = session.createQuery(hql).setParameter("emailId", emailId).setParameter("password", password);
			Object queryResult = query.uniqueResult();
			de = (UserDE) queryResult;
			if(null != de && de.getEmailId().equals(emailId))
				isAuthenticatedUser = true;
		}catch(HibernateException he){
			isAuthenticatedUser = false;
			new ExceptionHandler().rollbackAndThrowHibernateException(CLASS_NAME, tx, he);
		}finally {
			session.close();
		}
		return isAuthenticatedUser;
	}
	
}
