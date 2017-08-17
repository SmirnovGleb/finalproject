package by.epam.roulette.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import by.epam.roulette.entity.User;
import by.epam.roulette.exception.DaoException;
import by.epam.roulette.pool.ConnectionPool;
import by.epam.roulette.pool.ConnectionWrapper;

public class MessageDao extends AbstractDao{
	private static final String SQL_SELECT_FIND_ALL_MESSAGES = "SELECT u_name, m_text FROM message LEFT JOIN user ON m_user = u_id";
	private static final String SQL_INSERT_INTO_NEW_MESSAGE = "INSERT INTO message (m_user, m_text) VALUES (?, ?)";
	
	public ArrayList<String[]> findAllMessages() throws DaoException{
		ArrayList<String[]> messages = new ArrayList<>();
		ConnectionPool pool = ConnectionPool.getInstance();
		ConnectionWrapper con = null;
		Statement st = null;
		try {
			con = pool.receiveConnection();
			st = con.createStatement();
			ResultSet resultSet = st.executeQuery(SQL_SELECT_FIND_ALL_MESSAGES);
			while(resultSet.next()){
				String name = resultSet.getString("u_name");
				String text = resultSet.getString("m_text");
				String[] messagesArray = {name, text};
				messages.add(messagesArray);
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			closeStatement(st);
			pool.returnConnection(con);
		}
		return messages;
	}
	
	
	public boolean addMessage(String text, User user) throws DaoException{
		boolean flag = false;
		ConnectionPool pool = ConnectionPool.getInstance();
		ConnectionWrapper con = pool.receiveConnection();
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement(SQL_INSERT_INTO_NEW_MESSAGE);
			ps.setInt(1, user.getId());;	
			ps.setString(2, text);
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
