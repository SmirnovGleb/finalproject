package by.epam.roulette.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.roulette.dao.BetDao;
import by.epam.roulette.entity.Bet;
import by.epam.roulette.exception.DaoException;
import by.epam.roulette.exception.RouletteException;

/**
 * The Class ServiceBet.
 */
public class ServiceBet {
	private static Logger logger = LogManager.getLogger(ServiceBet.class);

	/**
	 * Find all bets.
	 *
	 * @return the map
	 * @throws RouletteException
	 *             the roulette exception
	 */
	public static Map<Bet, String> findAllBets() throws RouletteException {
		HashMap<Bet, String> bets = null;
		try {
			bets = new BetDao().findAllBets();
		} catch (DaoException e) {
			throw new RouletteException();
		}
		return bets;
	}

	/**
	 * Adds the bet.
	 *
	 * @param userId
	 * @param betOn
	 * @param money
	 * @param result
	 * @param winAmount
	 * @return true, if successful
	 */
	public static boolean addBet(int userId, String betOn, BigDecimal money, String result, BigDecimal winAmount) {
		boolean flag = false;
		try {
			flag = new BetDao().addBet(userId, betOn, money, result, winAmount);
		} catch (DaoException e) {
			logger.log(Level.ERROR, e);
		}
		return flag;
	}

	/**
	 * Find bets by user id.
	 *
	 * @param id
	 * @return the list
	 * @throws RouletteException
	 *             the roulette exception
	 */
	public static List<Bet> findBetsByUserId(int id) throws RouletteException {
		List<Bet> bets = null;
		try {
			bets = new BetDao().findBetsByUserId(id);
		} catch (DaoException e) {
			throw new RouletteException();
		}
		return bets;
	}
}
