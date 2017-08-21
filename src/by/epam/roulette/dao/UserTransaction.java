package by.epam.roulette.dao;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.roulette.exception.DaoException;
import by.epam.roulette.pool.ConnectionPool;
import by.epam.roulette.pool.ConnectionWrapper;

public class UserTransaction extends AbstractDao {
	private static Logger logger = LogManager.getLogger(UserTransaction.class);

	private static final String SQL_SELECT_USERS_MONEY_BY_ID = "SELECT u_money FROM user WHERE u_id = ?";
	private static final String SQL_SELECT_CASINOS_MONEY = "SELECT casino_money FROM casino WHERE casino_id = 1";
	private static final String SQL_UPDATE_USERS_MONEY = "UPDATE user SET u_money = ? WHERE u_id = ?";
	private static final String SQL_UPDATE_CASINOS_MONEY = "UPDATE casino SET casino_money = ? WHERE casino_id = 1";
	private static final String SQL_UPDATE_USERS_CREDIT_STATUS = "UPDATE credit SET c_is_return = ? WHERE c_user = ?";
	private static final int SQL_UPDATE_USER_IS_NOT_DEBTOR = 1;

	public static boolean fromCasinoToUser(int userId, BigDecimal money) throws DaoException {
		boolean flag = false;
		ConnectionPool pool = ConnectionPool.getInstance();
		ConnectionWrapper con = pool.receiveConnection();
		PreparedStatement ps = null;
		BigDecimal currentCasino = null;
		BigDecimal currentUser = null;
		try {
			con.setAutoCommit(false);
			ps = con.prepareStatement(SQL_SELECT_CASINOS_MONEY);
			ResultSet resultSetFromCasino = ps.executeQuery();
			while (resultSetFromCasino.next()) {
				currentCasino = resultSetFromCasino.getBigDecimal("casino_money");
			}
			ps = con.prepareStatement(SQL_SELECT_USERS_MONEY_BY_ID);
			ps.setInt(1, userId);
			ResultSet resultSetToUser = ps.executeQuery();
			while (resultSetToUser.next()) {
				currentUser = resultSetToUser.getBigDecimal("u_money");
			}
			ps = con.prepareStatement(SQL_UPDATE_CASINOS_MONEY);
			ps.setBigDecimal(1, currentCasino.subtract(money));
			ps.executeUpdate();
			ps = con.prepareStatement(SQL_UPDATE_USERS_MONEY);
			ps.setBigDecimal(1, currentUser.add(money));
			ps.setInt(2, userId);
			ps.executeUpdate();
			con.commit();
			con.setAutoCommit(true);
			flag = true;
		} catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				logger.log(Level.ERROR, e1);
			}
			throw new DaoException(e);
		} finally {
			closeStatement(ps);
			pool.returnConnection(con);
		}
		return flag;
	}

	public static boolean fromUserToCasino(int userId, BigDecimal money) throws DaoException {
		boolean flag = false;
		ConnectionPool pool = ConnectionPool.getInstance();
		ConnectionWrapper con = pool.receiveConnection();
		PreparedStatement ps = null;
		BigDecimal currentCasino = null;
		BigDecimal currentUser = null;
		try {
			con.setAutoCommit(false);
			ps = con.prepareStatement(SQL_SELECT_CASINOS_MONEY);
			ResultSet resultSetFromCasino = ps.executeQuery();
			while (resultSetFromCasino.next()) {
				currentCasino = resultSetFromCasino.getBigDecimal("casino_money");
			}
			ps = con.prepareStatement(SQL_SELECT_USERS_MONEY_BY_ID);
			ps.setInt(1, userId);
			ResultSet resultSetToUser = ps.executeQuery();
			while (resultSetToUser.next()) {
				currentUser = resultSetToUser.getBigDecimal("u_money");
			}
			ps = con.prepareStatement(SQL_UPDATE_CASINOS_MONEY);
			ps.setBigDecimal(1, currentCasino.add(money));
			ps.executeUpdate();
			ps = con.prepareStatement(SQL_UPDATE_USERS_MONEY);
			ps.setBigDecimal(1, currentUser.subtract(money));
			ps.setInt(2, userId);
			ps.executeUpdate();
			con.commit();
			con.setAutoCommit(true);
			flag = true;
		} catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				logger.log(Level.ERROR, e1);
			}
			throw new DaoException(e);
		} finally {
			closeStatement(ps);
			pool.returnConnection(con);
		}
		return flag;
	}

	public static boolean fromCardToUser(int userId, BigDecimal money) throws DaoException {
		boolean flag = false;
		ConnectionPool pool = ConnectionPool.getInstance();
		ConnectionWrapper con = pool.receiveConnection();
		PreparedStatement ps = null;
		BigDecimal currentUser = null;
		try {
			con.setAutoCommit(false);
			ps = con.prepareStatement(SQL_SELECT_USERS_MONEY_BY_ID);
			ps.setInt(1, userId);
			ResultSet resultSetToUser = ps.executeQuery();
			while (resultSetToUser.next()) {
				currentUser = resultSetToUser.getBigDecimal("u_money");
			}
			ps = con.prepareStatement(SQL_UPDATE_USERS_MONEY);
			ps.setBigDecimal(1, currentUser.add(money));
			ps.setInt(2, userId);
			ps.executeUpdate();
			con.commit();
			con.setAutoCommit(true);
			flag = true;
		} catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				logger.log(Level.ERROR, e1);
			}
			throw new DaoException(e);
		} finally {
			closeStatement(ps);
			pool.returnConnection(con);
		}
		return flag;
	}

	public static boolean returnCredit(int userId, BigDecimal money) throws DaoException {
		boolean flag = false;
		ConnectionPool pool = ConnectionPool.getInstance();
		ConnectionWrapper con = pool.receiveConnection();
		PreparedStatement ps = null;
		BigDecimal currentCasino = null;
		BigDecimal currentUser = null;
		try {
			con.setAutoCommit(false);
			ps = con.prepareStatement(SQL_SELECT_CASINOS_MONEY);
			ResultSet resultSetFromCasino = ps.executeQuery();
			while (resultSetFromCasino.next()) {
				currentCasino = resultSetFromCasino.getBigDecimal("casino_money");
			}
			ps = con.prepareStatement(SQL_SELECT_USERS_MONEY_BY_ID);
			ps.setInt(1, userId);
			ResultSet resultSetToUser = ps.executeQuery();
			while (resultSetToUser.next()) {
				currentUser = resultSetToUser.getBigDecimal("u_money");
			}
			ps = con.prepareStatement(SQL_UPDATE_CASINOS_MONEY);
			ps.setBigDecimal(1, currentCasino.add(money));
			ps.executeUpdate();
			ps = con.prepareStatement(SQL_UPDATE_USERS_MONEY);
			ps.setBigDecimal(1, currentUser.subtract(money));
			ps.setInt(2, userId);
			ps.executeUpdate();
			ps = con.prepareStatement(SQL_UPDATE_USERS_CREDIT_STATUS);
			ps.setInt(1, SQL_UPDATE_USER_IS_NOT_DEBTOR);
			ps.setInt(2, userId);
			ps.executeUpdate();
			con.commit();
			con.setAutoCommit(true);
			flag = true;
		} catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				logger.log(Level.ERROR, e1);
			}
			throw new DaoException(e);
		} finally {
			closeStatement(ps);
			pool.returnConnection(con);
		}
		return flag;
	}
}
