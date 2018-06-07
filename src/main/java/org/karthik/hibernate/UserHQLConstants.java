package org.karthik.hibernate;

public class UserHQLConstants {
	
	public static final String USER_VALIDATION = "from UserDE where emailId = :emailId and password = :password ";
	public static final String GET_USER_DE = "from UserDE";

}
