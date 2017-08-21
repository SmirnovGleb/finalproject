package by.epam.roulette.dao;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import by.epam.roulette.exception.DaoException;
import by.epam.roulette.pool.ConnectionPool;
import by.epam.roulette.pool.ConnectionWrapper;

public class CreditDao extends AbstractDao {
	private static final String SQL_SELECT_FIND_OVERDUE_DEBTOR = "SELECT * FROM credit WHERE c_return < now() AND c_is_return = 0 AND c_user = ?";
	private static final String SQL_SELECT_FIND_DEBTOR = "SELECT * FROM credit WHERE c_return > now() AND c_is_return = 0 AND c_user = ?";
	private static final String SQL_SELECT_DEBT_AMOUNT = "SELECT c_money FROM credit WHERE c_user = ?";
	private static final String SQL_INSERT_NEW_CREDIT = "INSERT INTO credit(c_user, c_money, c_got, c_return, c_is_return) values (?, ?, now(), date_add(now(),interval ? day),0)";

	public boolean isUserOverdueDebtor(int id) throws DaoException {
		boolean isDebtor = false;
		ConnectionPool pool = ConnectionPool.getInstance();
		ConnectionWrapper con = pool.receiveConnection();
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement(SQL_SELECT_FIND_OVERDUE_DEBTOR);
			ps.setInt(1, id);
			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()) {
				isDebtor = true;
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			closeStatement(ps);
			pool.returnConnection(con);
		}
		return isDebtor;
	}

	public boolean isUserDebtor(int id) throws DaoException {
		boolean isDebtor = false;
		ConnectionPool pool = ConnectionPool.getInstance();
		ConnectionWrapper con = pool.receiveConnection();
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement(SQL_SELECT_FIND_DEBTOR);
			ps.setInt(1, id);
			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()) {
				isDebtor = true;
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			closeStatement(ps);
			pool.returnConnection(con);
		}
		return isDebtor;
	}

	public BigDecimal findDebtAmount(int id) throws DaoException {
		BigDecimal debt = new BigDecimal("0");
		ConnectionPool pool = ConnectionPool.getInstance();
		ConnectionWrapper con = pool.receiveConnection();
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement(SQL_SELECT_DEBT_AMOUNT);
			ps.setInt(1, id);
			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()) {
				debt = resultSet.getBigDecimal("c_money");
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			closeStatement(ps);
			pool.returnConnection(con);
		}
		return debt;
	}

	public boolean addCredit(int userId, BigDecimal money, int duration) throws DaoException {
		boolean flag = false;
		ConnectionPool pool = ConnectionPool.getInstance();
		ConnectionWrapper con = pool.receiveConnection();
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement(SQL_INSERT_NEW_CREDIT);
			ps.setInt(1, userId);
			ps.setBigDecimal(2, money);
			ps.setInt(3, duration);
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
