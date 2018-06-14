package org.karthik.user.dao;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.karthik.exception.ExceptionHandler;
import org.karthik.hibernate.HibernateUtil;
import org.karthik.hibernate.UserHQLConstants;
import org.karthik.user.bo.UserBO;
import org.karthik.user.entity.UserDE;

public class UserDAO {
	
	private static final String CLASS_NAME = UserDAO.class.getSimpleName();
	
	HibernateUtil hibarnateUtil = new HibernateUtil();

	public UserDE create(UserBO user) {
		UserDE userDE = populateUserDeFromUserBO(user);
		saveEntity(userDE);
		return userDE;
	}
	
	private UserDE populateUserDeFromUserBO(UserBO user) {
		UserDE userDE = null;
		try{
			userDE = new UserDE();
			userDE.setName(user.getName());
			userDE.setEmailId(user.getEmailAddress());
			userDE.setPassword(user.getPassword());
			userDE.setLastLogin(new Timestamp(new Date().getTime()));
		}catch(Exception e){
			new ExceptionHandler().throwBusinessException(CLASS_NAME, e.getMessage(), e);
		}
		return userDE;
	}
	
	private void saveEntity(UserDE user) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.save(user);
			transaction.commit();
		} catch (HibernateException e) {
			new ExceptionHandler().rollbackAndThrowHibernateException(CLASS_NAME, transaction, e);
		} finally {
			if(null != session)
				session.close();
		}
	}

	public UserDE updateuser(UserBO user) {
		UserDE de = getEntity(user.getEmailAddress());
		if (null != de) {
			de.setName(user.getName());
			de.setPassword(user.getPassword());
			updateEntity(de);
		} 
		return de;
	}
	
	private UserDE getEntity(String emailId) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		UserDE de = null;
		try {
			transaction = session.beginTransaction();
			de = session.get(UserDE.class, emailId);
		} catch (HibernateException e) {
			new ExceptionHandler().rollbackAndThrowHibernateException(CLASS_NAME, transaction, e);
		} finally {
			if(null != session)
				session.close();
		}
		return de;
	}
	
	private void updateEntity(UserDE user) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.update(user);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			new ExceptionHandler().rollbackAndThrowHibernateException(CLASS_NAME, transaction, e);
		} finally {
			if(null != session)
				session.close();
		}
	}

	public List<UserDE> listUsers() {
		List<UserDE> de = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			String hql = UserHQLConstants.GET_USER_DE;
			@SuppressWarnings("unchecked")
			Query<UserDE> query = session.createQuery(hql);
			de = query.list();
		} catch (HibernateException e) {
			new ExceptionHandler().rollbackAndThrowHibernateException(CLASS_NAME, transaction, e);
		} finally {
			if(null != session)
				session.close();
		}
		return de;
	}
	
	public UserDE deleteUser(String emailId) {
		UserDE de = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			de = session.get(UserDE.class, emailId);
			session.delete(de);
			transaction.commit();
		} catch (HibernateException e) {
			new ExceptionHandler().rollbackAndThrowHibernateException(CLASS_NAME, transaction, e);
		} finally {
			if(null != session)
				session.close();
		}
		return de;
	}
	
}
