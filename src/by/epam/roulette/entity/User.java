package by.epam.roulette.entity;

import java.math.BigDecimal;

public class User {
	private int id;
	private String name;
	private String login;
	private String password;
	private BigDecimal money;
	private String email;
	private boolean isAdmin;
	
	public User() {
	}

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
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public BigDecimal getMoney() {
		return money;
	}
	public void setMoney(BigDecimal money) {
		this.money = money;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public boolean isAdmin() {
		return isAdmin;
	}
	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", login=" + login + ", password=" + password + ", money=" + money
				+ ", email=" + email + ", isAdmin=" + isAdmin + "]";
	}
}
