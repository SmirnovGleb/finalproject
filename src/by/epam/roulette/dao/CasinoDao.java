package by.epam.roulette.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import by.epam.roulette.exception.DaoException;
import by.epam.roulette.pool.ConnectionPool;
import by.epam.roulette.pool.ConnectionWrapper;

public class CasinoDao extends AbstractDao{
	private static final String SQL_UPDATE_PERCENT = "UPDATE casino SET casino_percent = ? WHERE casino_id = 1";
	private static final String SQL_GET_PERCENT = "SELECT casino_percent from casino WHERE casino_id = 1";
	
	public int getPercent() throws DaoException{
		ConnectionPool pool = ConnectionPool.getInstance();
		ConnectionWrapper con = null;
		Statement st = null;
		int percent = 0; 
		try {
			con = pool.receiveConnection();
			st = con.createStatement();
			ResultSet resultSet = st.executeQuery(SQL_GET_PERCENT);
			while(resultSet.next()){
				percent = resultSet.getInt("casino_percent");
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			closeStatement(st);
			pool.returnConnection(con);
		}
		return percent;
	} 
	
	public boolean changePercent(int percent) throws DaoException{
		boolean flag = false;
		ConnectionPool pool = ConnectionPool.getInstance();
		ConnectionWrapper con = pool.receiveConnection();
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement(SQL_UPDATE_PERCENT);
			ps.setInt(1, percent);
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
