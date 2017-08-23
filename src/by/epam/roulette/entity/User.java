package by.epam.roulette.entity;

import java.math.BigDecimal;

/**
 * The Class User.
 */
public class User {
	private int id;
	private String name;
	private String login;
	private String password;
	private BigDecimal money;
	private String email;
	private boolean isAdmin;

	/**
	 * Instantiates a new user.
	 */
	public User() {
	}

	/**
	 * Instantiates a new user.
	 *
	 * @param id
	 * @param name
	 * @param login
	 * @param password
	 * @param money
	 * @param email
	 * @param isAdmin
	 */
	public User(int id, String name, String login, String password, BigDecimal money, String email, boolean isAdmin) {
		super();
		this.id = id;
		this.name = name;
		this.login = login;
		this.password = password;
		this.money = money;
		this.email = email;
		this.isAdmin = isAdmin;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the login.
	 *
	 * @return the login
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * Sets the login.
	 *
	 * @param login
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the password.
	 *
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Gets the money.
	 *
	 * @return the money
	 */
	public BigDecimal getMoney() {
		return money;
	}

	/**
	 * Sets the money.
	 *
	 * @param money
	 */
	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email.
	 *
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Checks if is admin.
	 *
	 * @return true, if is admin
	 */
	public boolean isAdmin() {
		return isAdmin;
	}

	/**
	 * Sets the admin.
	 *
	 * @param isAdmin
	 */
	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", login=" + login + ", password=" + password + ", money=" + money
				+ ", email=" + email + ", isAdmin=" + isAdmin + "]";
	}
}
