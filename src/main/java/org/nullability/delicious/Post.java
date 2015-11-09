package org.nullability.delicious;

import java.io.File;
import java.io.Serializable;
import java.net.URL;
import java.util.Date;
import java.util.Set;
import java.util.TreeSet;

/**
 * 
 * @author Daniel Quirino Oliveira - danielqo@gmail.com
 *
 */
public class Post implements Serializable {
	
	private static final long serialVersionUID = 0xb747b3b8L;
	private String description;
	private String hash;
	private Set<Tag> tags;
	private Date lastUpdate;
	private URL url;


	public Post() {
		tags = new TreeSet<Tag>();
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public Set<Tag> getTags() {
		return tags;
	}

	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}

	public URL getUrl() {
		return url;
	}

	public void setUrl(URL url) {
		this.url = url;
	}

	public int hashCode() {
		return 31 * (url != null ? url.hashCode() : 0);
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Post other = (Post) obj;
		if (url == null) {
			if (other.getUrl() != null)
				return false;
		} else if (!url.equals(other.getUrl()))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Post{" +
				"description='" + description + '\'' +
				", hash='" + hash + '\'' +
				", tags=" + tags +
				", lastUpdate=" + lastUpdate +
				", url=" + url +
				'}';
	}

	public String markup() {
		return "- [" + description + "]("+ url +")";
	}
}
