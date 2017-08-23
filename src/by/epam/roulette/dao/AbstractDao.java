package by.epam.roulette.dao;

import java.sql.SQLException;
import java.sql.Statement;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The Class AbstractDao.
 */
public abstract class AbstractDao {
	private static Logger logger = LogManager.getLogger(AbstractDao.class);

	/**
	 * Close statement.
	 *
	 * @param st      
	 */
	public static void closeStatement(Statement st) {
		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				logger.log(Level.ERROR, "DAOException");
			}
		}
	}
}
