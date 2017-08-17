package by.epam.roulette.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Bet {
	private int id;
	private int userId;
	private String betOn;
	private BigDecimal money;
	private String result;
	private BigDecimal winAmount;
	private Timestamp date;
	
	public Bet(int id, int userId, String betOn, BigDecimal money, String result, BigDecimal winAmount,
			Timestamp date) {
		this.id = id;
		this.userId = userId;
		this.betOn = betOn;
		this.money = money;
		this.result = result;
		this.winAmount = winAmount;
		this.date = date;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getBetOn() {
		return betOn;
	}
	public void setBetOn(String betOn) {
		this.betOn = betOn;
	}
	public BigDecimal getMoney() {
		return money;
	}
	public void setMoney(BigDecimal money) {
		this.money = money;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public BigDecimal getWinAmount() {
		return winAmount;
	}
	public void setWinAmount(BigDecimal winAmount) {
		this.winAmount = winAmount;
	}
	public Timestamp getDate() {
		return date;
	}
	public void setDate(Timestamp date) {
		this.date = date;
	}
	
	
	
}
