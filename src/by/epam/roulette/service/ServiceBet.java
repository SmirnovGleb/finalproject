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

public class ServiceBet{
	private static Logger logger = LogManager.getLogger(ServiceBet.class);
	
	public static Map<Bet, String> findAllBets() throws RouletteException{
		HashMap<Bet, String> bets = null;
		try {
			bets = new BetDao().findAllBets();
		} catch (DaoException e) {
			throw new RouletteException();
		}
		return bets;
	}
	
	public static boolean addBet(int userId, String betOn, BigDecimal money, String result, BigDecimal winAmount){
		boolean flag = false;
		try {
			flag = new BetDao().addBet(userId, betOn, money, result, winAmount);
		} catch (DaoException e) {
			logger.log(Level.ERROR, e);
		}
		return flag;
	}
	
	public static List<Bet> findBetsByUserId(int id) throws RouletteException{
		List<Bet> bets = null;
		try {
			bets = new BetDao().findBetsByUserId(id);
		} catch (DaoException e) {
			throw new RouletteException();
		}
		return bets;
	}
}
