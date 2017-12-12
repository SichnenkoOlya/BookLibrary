package bsuir.library.domain;

import java.io.Serializable;

public class User implements Serializable {

	private static final long serialVersionUID = -7624483935361987049L;
	protected String name;
	protected int password;
	protected int accessRights;
	protected String email;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAccessRights() {
		return accessRights;
	}

	public void setAccessRights(int accessRights) {
		this.accessRights = accessRights;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getPassword() {
		return password;
	}

	public void setPassword(int password) {
		this.password = password;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		User that = (User) o;

		if (password != that.password) {
			return false;
		}
		return !(name != null ? !name.equals(that.name) : that.name != null);

	}

	@Override
	public int hashCode() {
		int result = name != null ? name.hashCode() : 0;
		result = 31 * result + 13;
		return result;
	}

	@Override
	public String toString() {
		return "User{" + "name='" + name + '\'' + "password='" + password + '\'' + ", accessRights=" + accessRights
				+ '}';
	}
}
