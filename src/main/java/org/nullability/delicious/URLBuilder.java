package org.nullability.delicious;

import java.io.UnsupportedEncodingException;
import java.net.*;

/**
 * 
 * @author Daniel Quirino Oliveira - danielqo@gmail.com
 *
 */
class URLBuilder {

	URLBuilder() {
	}

	public URL buildURL(DeliciousResources spi, String... values)
			throws MalformedURLException {
		String urlPattern = spi.getUrlPattern();
		int i = 0;
		String parameterPattern = "{" + i + "}";
		for (int j = 0; j < values.length; j++) {
			String value = values[j];
			try {
				value = URLEncoder.encode(value, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				throw new MalformedURLException((new StringBuilder(
						"Failed to construct the url due to bad encoding: "))
						.append(e.getMessage()).toString());
			}
			if (urlPattern.contains(parameterPattern))
				if ("".equals(value)) {
					int indexof = urlPattern.indexOf(parameterPattern);
					String toBeRemoved = urlPattern.substring(0, indexof);
					toBeRemoved = toBeRemoved.substring(toBeRemoved
							.lastIndexOf("&"), indexof);
					urlPattern = urlPattern.replace((new StringBuilder(String
							.valueOf(toBeRemoved))).append(parameterPattern)
							.toString(), "");
				} else {
					urlPattern = urlPattern.replace(parameterPattern, value);
				}
			i++;
			parameterPattern = "{" + i + "}";
		}

		URL url = new URL(urlPattern);
		return url;
	}
}
