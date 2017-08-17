package by.epam.roulette.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.roulette.dao.LockListDao;
import by.epam.roulette.dao.CasinoDao;
import by.epam.roulette.dao.UserDao;
import by.epam.roulette.entity.User;
import by.epam.roulette.exception.DaoException;
import by.epam.roulette.exception.RouletteException;

public class ServiceAdmin{
	private static Logger logger = LogManager.getLogger(ServiceAdmin.class);
	
	public static boolean changePercent(int percent){
		boolean flag = false;
		try {
			flag = new CasinoDao().changePercent(percent);
		} catch (DaoException e) {
			logger.log(Level.ERROR, e);
		}
		return flag;
	}
	
	public static ArrayList<User> findPlayers() throws RouletteException{
		ArrayList<User> players = new ArrayList<>();
		try {
			ArrayList<User> currentPlayers = new UserDao().findAllPlayers();
			for(User user : currentPlayers){
				Timestamp isBlockedUser = new LockListDao().isPlayerLocked(user.getId());
				if(isBlockedUser == null){
					players.add(user);
				}
			}
		} catch (DaoException e) {
			throw new RouletteException();
		}
		return players;
	}
	
	public static HashMap<User, Timestamp> findBlockedPlayers() throws RouletteException{
		HashMap<User, Timestamp> players = new HashMap<>();
		try {
			ArrayList<User> currentPlayers = new UserDao().findAllPlayers();
			for(User user : currentPlayers){
				Timestamp isLockedUser = new LockListDao().isPlayerLocked(user.getId());
				if(isLockedUser != null){
					players.put(user, isLockedUser);
				}
			}
		} catch (DaoException e) {
			throw new RouletteException();
		}
		return players;
	}
	
	public static boolean addLock(int id, int days){
		boolean flag = false;
		try {
			flag = new LockListDao().addLock(id, days);
		} catch (DaoException e) {
			logger.log(Level.ERROR, e);
		}
		return flag;
	}
	
	public static boolean unlockPlayer(int id){
		boolean flag = false;
		try {
			flag = new LockListDao().unlockPlayer(id);
		} catch (DaoException e) {
			logger.log(Level.ERROR, e);
		}
		return flag;
	}
	

}