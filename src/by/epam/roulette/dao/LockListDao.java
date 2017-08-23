package by.epam.roulette.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import by.epam.roulette.exception.DaoException;
import by.epam.roulette.pool.ConnectionPool;
import by.epam.roulette.pool.ConnectionWrapper;

/**
 * The Class LockListDao.
 */
public class LockListDao extends AbstractDao {
	private static final String SQL_SELECT_LOCKED_USER_BY_ID = "SELECT * FROM blocklist WHERE b_unblock > now() AND b_blocked_person = ?";
	private static final String SQL_INSERT_NEW_LOCK = "INSERT INTO blocklist(b_blocked_person, b_block, b_unblock) VALUES (?, now(), date_add(now(),interval ? day))";
	private static final String SQL_UPDATE_UNLOCK_PLAYER = "UPDATE blocklist SET b_unblock = NOW() WHERE b_blocked_person = ?";

	/**
	 * Checks if is player locked.
	 *
	 * @param id
	 * @return the timestamp
	 * @throws DaoException
	 *             the dao exception
	 */
	public Timestamp isPlayerLocked(int id) throws DaoException {
		Timestamp current = null;
		ConnectionPool pool = ConnectionPool.getInstance();
		ConnectionWrapper con = pool.receiveConnection();
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement(SQL_SELECT_LOCKED_USER_BY_ID);
			ps.setInt(1, id);
			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()) {
				current = resultSet.getTimestamp("b_unblock");
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			closeStatement(ps);
			pool.returnConnection(con);
		}
		return current;
	}

	/**
	 * Adds the lock.
	 *
	 * @param id
	 * @param days
	 * @return true, if successful
	 * @throws DaoException
	 *             the dao exception
	 */
	public boolean addLock(int id, int days) throws DaoException {
		boolean flag = false;
		ConnectionPool pool = ConnectionPool.getInstance();
		ConnectionWrapper con = pool.receiveConnection();
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement(SQL_INSERT_NEW_LOCK);
			ps.setInt(1, id);
			ps.setInt(2, days);
			ps.executeUpdate();
			flag = true;
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			closeStatement(ps);
			pool.returnConnection(con);
		}
		return flag;
	}

	/**
	 * Unlock player.
	 *
	 * @param id
	 * @return true, if successful
	 * @throws DaoException
	 *             the dao exception
	 */
	public boolean unlockPlayer(int id) throws DaoException {
		boolean flag = false;
		ConnectionPool pool = ConnectionPool.getInstance();
		ConnectionWrapper con = pool.receiveConnection();
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement(SQL_UPDATE_UNLOCK_PLAYER);
			ps.setInt(1, id);
			ps.executeUpdate();
			flag = true;
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			closeStatement(ps);
			pool.returnConnection(con);
		}
		return flag;
	}

}
