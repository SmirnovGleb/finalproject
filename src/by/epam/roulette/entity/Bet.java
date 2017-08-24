package by.epam.roulette.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * The Class Bet.
 */
public class Bet {
	private int id;
	private int userId;
	private String betOn;
	private BigDecimal money;
	private String result;
	private BigDecimal winAmount;
	private Timestamp date;

	/**
	 * Instantiates a new bet.
	 *
	 * @param id
	 * @param userId
	 * @param betOn
	 * @param money
	 * @param result
	 * @param winAmount
	 * @param date
	 */
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
	 * Gets the user id.
	 *
	 * @return the user id
	 */
	public int getUserId() {
		return userId;
	}

	/**
	 * Sets the user id.
	 *
	 * @param userId
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}

	/**
	 * Gets the bet on.
	 *
	 * @return the bet on
	 */
	public String getBetOn() {
		return betOn;
	}

	/**
	 * Sets the bet on.
	 *
	 * @param betOn
	 */
	public void setBetOn(String betOn) {
		this.betOn = betOn;
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
	 * Gets the result.
	 *
	 * @return the result
	 */
	public String getResult() {
		return result;
	}

	/**
	 * Sets the result.
	 *
	 * @param result
	 */
	public void setResult(String result) {
		this.result = result;
	}

	/**
	 * Gets the win amount.
	 *
	 * @return the win amount
	 */
	public BigDecimal getWinAmount() {
		return winAmount;
	}

	/**
	 * Sets the win amount.
	 *
	 * @param winAmount
	 */
	public void setWinAmount(BigDecimal winAmount) {
		this.winAmount = winAmount;
	}

	/**
	 * Gets the date.
	 *
	 * @return the date
	 */
	public Timestamp getDate() {
		return date;
	}

	/**
	 * Sets the date.
	 *
	 * @param date
	 */
	public void setDate(Timestamp date) {
		this.date = date;
	}

}
