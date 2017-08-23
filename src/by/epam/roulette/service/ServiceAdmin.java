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

/**
 * The Class ServiceAdmin.
 */
public class ServiceAdmin {
	private static Logger logger = LogManager.getLogger(ServiceAdmin.class);

	/**
	 * Change credit's percent.
	 *
	 * @param percent
	 * @return true, if successful
	 */
	public static boolean changePercent(int percent) {
		boolean flag = false;
		try {
			flag = new CasinoDao().changePercent(percent);
		} catch (DaoException e) {
			logger.log(Level.ERROR, e);
		}
		return flag;
	}

	/**
	 * Find all players.
	 *
	 * @return the array list
	 * @throws RouletteException
	 *             the roulette exception
	 */
	public static ArrayList<User> findPlayers() throws RouletteException {
		ArrayList<User> players = new ArrayList<>();
		try {
			ArrayList<User> currentPlayers = new UserDao().findAllPlayers();
			for (User user : currentPlayers) {
				Timestamp isBlockedUser = new LockListDao().isPlayerLocked(user.getId());
				if (isBlockedUser == null) {
					players.add(user);
				}
			}
		} catch (DaoException e) {
			throw new RouletteException();
		}
		return players;
	}

	/**
	 * Find all blocked players.
	 *
	 * @return the hash map
	 * @throws RouletteException
	 *             the roulette exception
	 */
	public static HashMap<User, Timestamp> findBlockedPlayers() throws RouletteException {
		HashMap<User, Timestamp> players = new HashMap<>();
		try {
			ArrayList<User> currentPlayers = new UserDao().findAllPlayers();
			for (User user : currentPlayers) {
				Timestamp isLockedUser = new LockListDao().isPlayerLocked(user.getId());
				if (isLockedUser != null) {
					players.put(user, isLockedUser);
				}
			}
		} catch (DaoException e) {
			throw new RouletteException();
		}
		return players;
	}

	/**
	 * Adds the user's lock.
	 *
	 * @param id
	 * @param days
	 * @return true, if successful
	 */
	public static boolean addLock(int id, int days) {
		boolean flag = false;
		try {
			flag = new LockListDao().addLock(id, days);
		} catch (DaoException e) {
			logger.log(Level.ERROR, e);
		}
		return flag;
	}

	/**
	 * Unlock player.
	 *
	 * @param id
	 * @return true, if successful
	 */
	public static boolean unlockPlayer(int id) {
		boolean flag = false;
		try {
			flag = new LockListDao().unlockPlayer(id);
		} catch (DaoException e) {
			logger.log(Level.ERROR, e);
		}
		return flag;
	}

}