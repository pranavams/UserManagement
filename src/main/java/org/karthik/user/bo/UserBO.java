package org.karthik.user.bo;


import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserBO {
	
	private String name;
	private String emailAddress;
	private String password;
	private Timestamp timestamp;
}
