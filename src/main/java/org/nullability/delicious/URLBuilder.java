package org.nullability.delicious;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * 
 * @author Daniel Quirino Oliveira - danielqo@gmail.com
 * 
 */
class URLBuilder {

	URLBuilder() {
	}

	public URL buildURL(DeliciousResources resource, String... values)
			throws MalformedURLException {
		String urlPattern = resource.getUrlPattern();
		int expectedParams = resource.parameters();
		int actualParams = values.length;
		
		String paramPattern = null;
		for(int i = 0; i < expectedParams; i++) {
			paramPattern = "{" + i + "}";
			String value = encode(i < actualParams?values[i]:"");
			if ("".equals(value)) {
				urlPattern = removeUnusedParameter(urlPattern,
						paramPattern);
			} else {
				urlPattern = urlPattern.replace(paramPattern, value);
			}
		}
		URL url = new URL(urlPattern);
		return url;
	}

	private String encode(String value) throws MalformedURLException {
		try {
			return URLEncoder.encode(value, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new MalformedURLException("Failed to construct the url due to bad encoding: "	+ e.getMessage());
		}
	}

	private String removeUnusedParameter(String urlPattern,
			String parameterPattern) {
		int indexof = urlPattern.indexOf(parameterPattern);
		String toBeRemoved = urlPattern.substring(0, indexof);
		toBeRemoved = toBeRemoved.substring(toBeRemoved.lastIndexOf("&"),
				indexof);
		urlPattern = urlPattern.replace(toBeRemoved + parameterPattern, "");
		return urlPattern;
	}
}
