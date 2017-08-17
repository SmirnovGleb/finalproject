package by.epam.roulette.service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.roulette.converter.MD5Converter;
import by.epam.roulette.dao.CasinoDao;
import by.epam.roulette.dao.CreditDao;
import by.epam.roulette.dao.LockListDao;
import by.epam.roulette.dao.MessageDao;
import by.epam.roulette.dao.UserDao;
import by.epam.roulette.dao.UserTransaction;
import by.epam.roulette.entity.User;
import by.epam.roulette.exception.DaoException;
import by.epam.roulette.exception.RouletteException;
import by.epam.roulette.logic.CalculateWinnings;
import by.epam.roulette.logic.MakeArrayBets;
import by.epam.roulette.validator.UserParametersValidator;

public class ServiceUser {
	private static Logger logger = LogManager.getLogger(ServiceUser.class);
	
	public static User loginUser(String login) throws RouletteException{
		User user = null;
		UserDao dao = new UserDao();
		try {
			user = dao.findUserByLogin(login);
		} catch (DaoException e) {
			throw new RouletteException();
		}
		return user;
	}
	
	public static String[] play(User user, String bet, String positions, int winnum){
		String[] result = new String[2];
		boolean won = false;
		BigDecimal currentBet = new BigDecimal(bet);
		int[] numbers = MakeArrayBets.makeArray(positions);		
		for(int i = 0; i < numbers.length; i++){
			if(numbers[i] == winnum){
				won = true;
			}
		}
		if(won){
			BigDecimal winning = CalculateWinnings.calculate(currentBet, numbers.length);
			result[0] = "Win";
			result[1] = winning.toString();
			user.setMoney(user.getMoney().add(winning));
			try {
				UserTransaction.fromCasinoToUser(user.getId(), winning);
			} catch (DaoException e) {
				logger.log(Level.ERROR, e);
			}
			
		}
		else{
			result[0] = "Lost";
			result[1] = bet;
			user.setMoney(user.getMoney().subtract(currentBet));
			try {
				UserTransaction.fromUserToCasino(user.getId(), new BigDecimal(bet));
			} catch (DaoException e) {
				logger.log(Level.ERROR, e);
			}
		}
		return result;
	}
	
	public static boolean addUser(String name, String login, String password, String email){
		boolean flag = false;
		UserDao dao = new UserDao();
		try {
			flag = dao.addUser(name, login, new MD5Converter().convert(password), new BigDecimal(0), email);
		} catch (DaoException e) {
			logger.log(Level.ERROR, e);
		}
		return flag;
	}
	
	public static boolean takeCredit(User user, BigDecimal money, int duration){
		boolean flag = false;
		try {
			flag = new CreditDao().addCredit(user.getId(), money, duration);
			flag = UserTransaction.fromCasinoToUser(user.getId(), money);
		} catch (DaoException e) {
			logger.log(Level.ERROR, e);
		}
		return flag;
	}
	
	public static boolean hasCredit(User user){
		boolean flag = false;
		try {
			flag = new CreditDao().isUserDebtor(user.getId());
		} catch (DaoException e) {
			logger.log(Level.ERROR, e);
		}
		return flag;
	}
	
	public static boolean hasOverdueCredit(User user){
		boolean flag = false;
		try {
			flag = new CreditDao().isUserOverdueDebtor(user.getId());
		} catch (DaoException e) {
			logger.log(Level.ERROR, e);
		}
		return flag;
	}
	
	public static boolean returnCredit(User user){
		boolean flag = false;
		int casinoPercent = 0;
		BigDecimal debt = new BigDecimal("0");
		try {
			casinoPercent = new CasinoDao().getPercent();
			debt = new CreditDao().findDebtAmount(user.getId());
			BigDecimal onePercent = debt.divide(new BigDecimal(100));
			BigDecimal allPercent = onePercent.multiply(new BigDecimal(casinoPercent));
			BigDecimal debtWithPercent = debt.add(allPercent);
			flag = UserTransaction.returnCredit(user.getId(), debtWithPercent);
		} catch (DaoException e) {
			logger.log(Level.ERROR, e);
		}
		return flag;
	}
	
	public static boolean cashIn(User user, BigDecimal money){
		boolean flag = false;
		try {
			flag = UserTransaction.fromCardToUser(user.getId(), money);
			user.setMoney(user.getMoney().add(money));
		} catch (DaoException e) {
			logger.log(Level.ERROR, e);
		}
		return flag;
	}
	
	public static boolean changePassword(User user, String oldPassword, String newPassword){
		boolean flag = false;
		UserDao dao = new UserDao();
		try {
			String DaoPassword = dao.findPassword(user.getId());
			if((DaoPassword.equals(new MD5Converter().convert(oldPassword))) && (UserParametersValidator.validateLoginPassword(newPassword))){
				flag = dao.updatePassword(user.getId(), new MD5Converter().convert(newPassword));
			}
		} catch (DaoException e) {
			logger.log(Level.ERROR, e);
		}
		return flag;
	}
	
	public static boolean changeEmail(User user, String newEmail){
		boolean flag = false;
		UserDao dao = new UserDao();
		try {
			
			if(UserParametersValidator.validateEmail(newEmail)){
				flag = dao.updateEmail(user.getId(), newEmail);
			}
		} catch (DaoException e) {
			logger.log(Level.ERROR, e);
		}
		return flag;
	}
	
	public static List findAllMessages() throws RouletteException{
		List list = null;
		try {
			list = new MessageDao().findAllMessages();
		} catch (DaoException e) {
			throw new RouletteException();
		}
		return list;
	}
	
	public static boolean addMessage(String text, User user){
		boolean flag = false;
		try {
			flag = new MessageDao().addMessage(text, user);
		} catch (DaoException e) {
			logger.log(Level.ERROR, e);
		}
		return flag;
	}
	
	public static boolean isPlayerLocked(int id){
		boolean flag = false;
		try {
			Timestamp currentLock = new LockListDao().isPlayerLocked(id);
			if(currentLock!=null){
				flag = true;
			}
		} catch (DaoException e) {
			logger.log(Level.ERROR, e);
		}
		return flag;
	}
	
}
