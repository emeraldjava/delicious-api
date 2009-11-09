package org.nullability.delicious;

import java.text.SimpleDateFormat;

/**
 * 
 * @author Daniel Quirino Oliveira - danielqo@gmail.com
 *
 */
final class DeliciousDateFormatter extends SimpleDateFormat {

	public DeliciousDateFormatter() {
		super("yyyy-MM-dd'T'hh:mm:ss'Z'");
	}

	private static final long serialVersionUID = 0x8fc622dbL;
}
