package by.epam.roulette.dao;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;

import by.epam.roulette.entity.Bet;
import by.epam.roulette.exception.DaoException;
import by.epam.roulette.pool.ConnectionPool;
import by.epam.roulette.pool.ConnectionWrapper;

/**
 * The Class BetDao.
 */
public class BetDao extends AbstractDao {
	private static final String SQL_SELECT_USER_BET_BY_ID = "SELECT * FROM bet WHERE b_player = ?";
	private static final String SQL_SELECT_USER_BETS = "SELECT b_id, b_player, b_bet_on, b_money, b_result, b_win_amount, b_date, u_name FROM bet LEFT JOIN user ON b_player = u_id";
	private static final String SQL_INSERT_NEW_BET = "INSERT INTO bet(b_player, b_bet_on, b_money, b_result, b_win_amount, b_date) VALUES (?, ?, ?, ?, ?, now())";

	/**
	 * Find all bets.
	 *
	 * @return the hash map
	 * @throws DaoException
	 *             the dao exception
	 */
	public HashMap<Bet, String> findAllBets() throws DaoException {
		HashMap<Bet, String> bets = new HashMap<>();
		ConnectionPool pool = ConnectionPool.getInstance();
		ConnectionWrapper con = null;
		Statement st = null;
		try {
			con = pool.receiveConnection();
			st = con.createStatement();
			ResultSet resultSet = st.executeQuery(SQL_SELECT_USER_BETS);
			while (resultSet.next()) {
				int id = resultSet.getInt("b_id");
				int userId = resultSet.getInt("b_player");
				String betOn = resultSet.getString("b_bet_on");
				BigDecimal money = resultSet.getBigDecimal("b_money");
				String result = resultSet.getString("b_result");
				BigDecimal winAmount = resultSet.getBigDecimal("b_win_amount");
				Timestamp date = resultSet.getTimestamp("b_date");
				String playerName = resultSet.getString("u_name");
				bets.put(new Bet(id, userId, betOn, money, result, winAmount, date), playerName);
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			closeStatement(st);
			pool.returnConnection(con);
		}

		return bets;
	}

	/**
	 * Adds the bet.
	 *
	 * @param userId
	 *            the user id
	 * @param betOn
	 *            the bet on
	 * @param money
	 *            the money
	 * @param result
	 *            the result
	 * @param winAmount
	 *            the win amount
	 * @return true, if successful
	 * @throws DaoException
	 *             the dao exception
	 */
	public boolean addBet(int userId, String betOn, BigDecimal money, String result, BigDecimal winAmount)
			throws DaoException {
		boolean flag = false;
		ConnectionPool pool = ConnectionPool.getInstance();
		ConnectionWrapper con = pool.receiveConnection();
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement(SQL_INSERT_NEW_BET);
			ps.setInt(1, userId);
			ps.setString(2, betOn);
			ps.setBigDecimal(3, money);
			ps.setString(4, result);
			ps.setBigDecimal(5, winAmount);
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
	 * Find bets by user id.
	 *
	 * @param id
	 *            the id
	 * @return the array list
	 * @throws DaoException
	 *             the dao exception
	 */
	public ArrayList<Bet> findBetsByUserId(int id) throws DaoException {
		ConnectionPool pool = ConnectionPool.getInstance();
		ConnectionWrapper con = pool.receiveConnection();
		PreparedStatement ps = null;
		ArrayList<Bet> bets = new ArrayList<>();
		try {
			ps = con.prepareStatement(SQL_SELECT_USER_BET_BY_ID);
			ps.setInt(1, id);
			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()) {
				int betId = resultSet.getInt("b_id");
				int userId = resultSet.getInt("b_player");
				String betOn = resultSet.getString("b_bet_on");
				BigDecimal money = resultSet.getBigDecimal("b_money");
				String result = resultSet.getString("b_result");
				BigDecimal winAmount = resultSet.getBigDecimal("b_win_amount");
				Timestamp date = resultSet.getTimestamp("b_date");
				Bet bet = new Bet(betId, userId, betOn, money, result, winAmount, date);
				bets.add(bet);
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			closeStatement(ps);
			pool.returnConnection(con);
		}
		return bets;
	}
}
