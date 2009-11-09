package org.nullability.delicious;

import java.net.Authenticator;
import java.net.PasswordAuthentication;

/**
 * 
 * @author Daniel Quirino Oliveira - danielqo@gmail.com
 * 
 */
final class HTTPAuthenticator extends Authenticator {

	private String username;
	private String password;

	public HTTPAuthenticator(String username, String password)
			throws IllegalArgumentException {
		if (username == null || password == null || "".equals(username)
				|| "".equals(password)) {
			throw new IllegalArgumentException();
		} else {
			this.username = username;
			this.password = password;
		}
	}

	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(username, password.toCharArray());
	}

}
