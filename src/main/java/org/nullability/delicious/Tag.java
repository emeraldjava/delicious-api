package org.nullability.delicious;

import java.io.Serializable;

/**
 * 
 * @author Daniel Quirino Oliveira - danielqo@gmail.com
 *
 */
public class Tag implements Serializable {
	
    private static final long serialVersionUID = 0x4e8bed43L;
    private String name;
    private Integer count;

	public Tag() {
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int hashCode() {
		return 31 * (name != null ? name.hashCode() : 0);
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tag other = (Tag) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}
