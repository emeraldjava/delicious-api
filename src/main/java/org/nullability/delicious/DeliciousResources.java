package org.nullability.delicious;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @author Daniel Quirino Oliveira - danielqo@gmail.com
 *
 */
enum DeliciousResources
{
	ADDPOST("https://api.del.icio.us/v1/posts/add?url={0}&description={1}&extended={2}&tags={3}&dt={4}&replace={5}&shared={6}"),
    DELETEPOST("https://api.del.icio.us/v1/posts/delete?url={0}"),
    GETALLPOSTS("https://api.del.icio.us/v1/posts/all?tag={0}"),
    GETPOSTSBYFILTER("https://api.del.icio.us/v1/posts/get?tag={0}&dt={1}&url={2}"),
    GETRECENTPOSTS("https://api.del.icio.us/v1/posts/recent?tag={0}&count={1}"),
    GETLASTUPDATE("https://api.del.icio.us/v1/posts/update"),
    GETALLTAGS("https://api.del.icio.us/v1/tags/get"),
    RENAMETAG("https://api.del.icio.us/v1/tags/rename?old={0}&new={1}"),
    GETBUNDLES("https://api.del.icio.us/v1/tags/bundles/all"),
    CREATEBUNDLE("https://api.del.icio.us/v1/tags/bundles/set?bundle={0}&tags={1}"),
    DELETEBUNDLE("https://api.del.icio.us/v1/tags/bundles/delete?bundle={0}");

    private final String urlPattern;
    
    private DeliciousResources(String urlPattern)
    {
        this.urlPattern = urlPattern;
    }

    public String getUrlPattern() {
        return urlPattern;
    }
    
    public int parameters() {
		Pattern pattern = Pattern.compile("\\{[0-9]+\\}");
		Matcher matcher = pattern.matcher(this.urlPattern);
		int counter = 0;
		while(matcher.find()) counter++;
		return counter;
	}

    
}
