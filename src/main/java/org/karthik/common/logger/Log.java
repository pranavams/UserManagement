package org.karthik.common.logger;

public class Log {

	public static void info(String simpleName, String methodName, String message, Exception exception) {
		String messageToLog = new StringBuffer(simpleName)
				.append(":")
				.append(methodName)
				.append(":")
				.append(message)
				.append(":Exception:")
				.append(exception.toString()).toString();
		System.out.println(messageToLog);
	}

}
