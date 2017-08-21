package by.epam.roulette.pool;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConnectionPool {
	private static Logger logger = LogManager.getLogger(ConnectionPool.class);
	private static final String PATH_TO_DATA = "resource.database";
	private static ConnectionPool instance;
	private static ReentrantLock instanceLock = new ReentrantLock();
	private static AtomicBoolean exist = new AtomicBoolean(false);
	private ArrayBlockingQueue<ConnectionWrapper> pool;
	private int poolSize;

	private ConnectionPool() {
		String driver;
		try {
			ResourceBundle resource = ResourceBundle.getBundle(PATH_TO_DATA);
			poolSize = Integer.parseInt(resource.getString("poolsize"));
			driver = resource.getString("driver");
		} catch (NumberFormatException | MissingResourceException e) {
			logger.log(Level.FATAL, e);
			throw new RuntimeException(e);
		}
		pool = new ArrayBlockingQueue<ConnectionWrapper>(poolSize);
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			logger.log(Level.FATAL, e);
			throw new RuntimeException(e);
		}
		for (int i = 0; i < poolSize; i++) {
			pool.add(createConnection());
		}
	}

	public static ConnectionPool getInstance() {
		if (!exist.get()) {
			instanceLock.lock();
			try {
				if (instance == null) {
					instance = new ConnectionPool();
					exist.set(true);
				}
			} finally {
				instanceLock.unlock();
			}
		}
		return instance;
	}

	private ConnectionWrapper createConnection() {
		return new ConnectionWrapper();
	}

	public ConnectionWrapper receiveConnection() {
		ConnectionWrapper con = null;
		try {
			con = pool.take();
		} catch (InterruptedException e) {
			logger.log(Level.ERROR, e);
		}
		return con;
	}

	public boolean returnConnection(ConnectionWrapper con) {
		if (con != null && pool.size() < poolSize) {
			pool.add(con);
			return true;
		}
		return false;
	}

	public void destroyConnectionPool() {
		for (int i = 0; i < poolSize; i++) {
			try {
				pool.take().destroyConnection();
			} catch (SQLException | InterruptedException e) {
				logger.log(Level.ERROR, e);
			}
		}
		Enumeration<Driver> drivers = DriverManager.getDrivers();
		while (drivers.hasMoreElements()) {
			Driver driver = drivers.nextElement();
			try {
				DriverManager.deregisterDriver(driver);
			} catch (SQLException e) {
				logger.log(Level.FATAL, e);
				throw new RuntimeException(e);//
			}
		}
		instance = null;
		instanceLock = null;
		pool = null;
		exist = null;
	}
}
