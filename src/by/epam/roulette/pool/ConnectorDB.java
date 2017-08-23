package by.epam.roulette.pool;

import java.sql.SQLException;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.roulette.exception.RouletteException;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * The Class ConnectorDB.
 */
class ConnectorDB {
	private static final String PATH_TO_DATA = "resource.database";
	private static Logger logger = LogManager.getLogger(ConnectorDB.class);

	/**
	 * Gets the connection.
	 *
	 * @return the connection
	 * @throws SQLException
	 *             the SQL exception
	 * @throws RouletteException
	 *             the roulette exception
	 */
	static Connection getConnection() throws SQLException, RouletteException {
		String url;
		String user;
		String pass;
		try {
			ResourceBundle resource = ResourceBundle.getBundle(PATH_TO_DATA);
			url = resource.getString("url");
			user = resource.getString("user");
			pass = resource.getString("password");
		} catch (MissingResourceException e) {
			logger.log(Level.FATAL, e);
			throw new RouletteException(e);
		}
		return DriverManager.getConnection(url, user, pass);
	}
}
