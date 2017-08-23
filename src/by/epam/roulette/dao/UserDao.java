package by.epam.roulette.dao;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import by.epam.roulette.entity.User;
import by.epam.roulette.exception.DaoException;
import by.epam.roulette.pool.ConnectionPool;
import by.epam.roulette.pool.ConnectionWrapper;

/**
 * The Class UserDao.
 */
public class UserDao extends AbstractDao {
	private static final String SQL_SELECT_ALL_USERS = "SELECT * FROM user";
	private static final String SQL_SELECT_USER_BY_ID = "SELECT * FROM user WHERE u_id = ?";
	private static final String SQL_SELECT_USER_BY_LOGIN = "SELECT * FROM user WHERE u_login = ?";
	private static final String SQL_INSERT_INTO_NEW_USER = "INSERT INTO user (u_name, u_login, u_password, u_is_admin, u_money, u_email) VALUES (?, ?, ?, ?, ?, ?)";
	private static final String SQL_UPDATE_PASSWORD = "UPDATE user SET u_password = ? WHERE u_id = ?";
	private static final String SQL_UPDATE_EMAIL = "UPDATE user SET u_email = ? WHERE u_id = ?";
	private static final int USER_IS_NOT_ADMIN = 0;

	/**
	 * Find all players.
	 *
	 * @return the array list
	 * @throws DaoException
	 *             the dao exception
	 */
	public ArrayList<User> findAllPlayers() throws DaoException {
		ArrayList<User> users = new ArrayList<>();
		ConnectionPool pool = ConnectionPool.getInstance();
		ConnectionWrapper con = null;
		Statement st = null;
		try {
			con = pool.receiveConnection();
			st = con.createStatement();
			ResultSet resultSet = st.executeQuery(SQL_SELECT_ALL_USERS);
			while (resultSet.next()) {
				int id = resultSet.getInt("u_id");
				String name = resultSet.getString("u_name");
				String login = resultSet.getString("u_login");
				String password = resultSet.getString("u_password");
				BigDecimal money = resultSet.getBigDecimal("u_money");
				String email = resultSet.getString("u_email");
				boolean isAdmin = resultSet.getBoolean("u_is_admin");
				if (!isAdmin) {
					users.add(new User(id, name, login, password, money, email, isAdmin));
				}
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			closeStatement(st);
			pool.returnConnection(con);
		}

		return users;
	}

	/**
	 * Adds the user.
	 *
	 * @param name
	 * @param login
	 * @param password
	 * @param money
	 * @param email
	 * @return true, if successful
	 * @throws DaoException
	 *             the dao exception
	 */
	public boolean addUser(String name, String login, String password, BigDecimal money, String email)
			throws DaoException {
		boolean flag = false;
		ConnectionPool pool = ConnectionPool.getInstance();
		ConnectionWrapper con = pool.receiveConnection();
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement(SQL_INSERT_INTO_NEW_USER);
			ps.setString(1, name);
			ps.setString(2, login);
			ps.setString(3, password);
			ps.setInt(4, USER_IS_NOT_ADMIN);
			ps.setBigDecimal(5, money);
			ps.setString(6, email);
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
	 * Find user by id.
	 *
	 * @param id
	 * @return the user
	 * @throws DaoException
	 *             the dao exception
	 */
	public User findUserById(int id) throws DaoException {
		ConnectionPool pool = ConnectionPool.getInstance();
		ConnectionWrapper con = pool.receiveConnection();
		PreparedStatement ps = null;
		User user = null;
		try {
			ps = con.prepareStatement(SQL_SELECT_USER_BY_ID);
			ps.setInt(1, id);
			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()) {
				int userId = resultSet.getInt("u_id");
				String name = resultSet.getString("u_name");
				String login = resultSet.getString("u_login");
				String password = resultSet.getString("u_password");
				BigDecimal money = resultSet.getBigDecimal("u_money");
				String email = resultSet.getString("u_email");
				boolean isAdmin = resultSet.getBoolean("u_is_admin");
				user = new User(userId, name, login, password, money, email, isAdmin);
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			closeStatement(ps);
			pool.returnConnection(con);
		}
		return user;
	}

	/**
	 * Find user by login.
	 *
	 * @param log
	 * @return the user
	 * @throws DaoException
	 *             the dao exception
	 */
	public User findUserByLogin(String log) throws DaoException {
		ConnectionPool pool = ConnectionPool.getInstance();
		ConnectionWrapper con = pool.receiveConnection();
		PreparedStatement ps = null;
		User user = null;
		try {
			ps = con.prepareStatement(SQL_SELECT_USER_BY_LOGIN);
			ps.setString(1, log);
			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()) {
				int userId = resultSet.getInt("u_id");
				String name = resultSet.getString("u_name");
				String login = resultSet.getString("u_login");
				String password = resultSet.getString("u_password");
				BigDecimal money = resultSet.getBigDecimal("u_money");
				String email = resultSet.getString("u_email");
				boolean isAdmin = resultSet.getBoolean("u_is_admin");
				user = new User(userId, name, login, password, money, email, isAdmin);
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			closeStatement(ps);
			pool.returnConnection(con);
		}
		return user;
	}

	/**
	 * Update password.
	 *
	 * @param id
	 * @param newPassword
	 * @return true, if successful
	 * @throws DaoException
	 *             the dao exception
	 */
	public boolean updatePassword(int id, String newPassword) throws DaoException {
		boolean flag = false;
		ConnectionPool pool = ConnectionPool.getInstance();
		ConnectionWrapper con = pool.receiveConnection();
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement(SQL_UPDATE_PASSWORD);
			ps.setString(1, newPassword);
			ps.setInt(2, id);
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
	 * Find password.
	 *
	 * @param id
	 * @return the string
	 * @throws DaoException
	 *             the dao exception
	 */
	public String findPassword(int id) throws DaoException {
		ConnectionPool pool = ConnectionPool.getInstance();
		ConnectionWrapper con = pool.receiveConnection();
		PreparedStatement ps = null;
		String password = null;
		try {
			ps = con.prepareStatement(SQL_SELECT_USER_BY_ID);
			ps.setInt(1, id);
			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()) {
				password = resultSet.getString("u_password");
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			closeStatement(ps);
			pool.returnConnection(con);
		}
		return password;
	}

	/**
	 * Update email.
	 *
	 * @param id
	 * @param newEmail
	 * @return true, if successful
	 * @throws DaoException
	 *             the dao exception
	 */
	public boolean updateEmail(int id, String newEmail) throws DaoException {
		boolean flag = false;
		ConnectionPool pool = ConnectionPool.getInstance();
		ConnectionWrapper con = pool.receiveConnection();
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement(SQL_UPDATE_EMAIL);
			ps.setString(1, newEmail);
			ps.setInt(2, id);
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
