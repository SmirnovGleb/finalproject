package by.epam.roulette.pool;

import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.NClob;
import java.sql.PreparedStatement;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Savepoint;
import java.sql.Statement;
import java.sql.Struct;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.roulette.exception.RouletteException;

/**
 * The Class ConnectionWrapper.
 */
public class ConnectionWrapper implements AutoCloseable {
	private static Logger logger = LogManager.getLogger(ConnectionWrapper.class);
	private Connection connection;

	/**
	 * Instantiates a new connection wrapper.
	 */
	ConnectionWrapper() {
		try {
			connection = ConnectorDB.getConnection();
		} catch (SQLException | RouletteException e) {
			logger.log(Level.ERROR, e);
		}
	}

	/**
	 * Abort.
	 *
	 * @param executor
	 * @throws SQLException
	 *             the SQL exception
	 */
	void abort(Executor executor) throws SQLException {
		connection.abort(executor);
	}

	/**
	 * Clear warnings.
	 *
	 * @throws SQLException
	 *             the SQL exception
	 */
	public void clearWarnings() throws SQLException {
		connection.clearWarnings();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.AutoCloseable#close()
	 */
	public void close() throws SQLException {
		ConnectionPool.getInstance().returnConnection(this);
	}

	/**
	 * Destroy connection.
	 *
	 * @throws SQLException
	 *             the SQL exception
	 */
	void destroyConnection() throws SQLException {
		this.connection.close();
	}

	/**
	 * Commit.
	 *
	 * @throws SQLException
	 *             the SQL exception
	 */
	public void commit() throws SQLException {
		connection.commit();
	}

	/**
	 * Creates the array of.
	 *
	 * @param typeName
	 * @param elements
	 * @return the array
	 * @throws SQLException
	 *             the SQL exception
	 */
	public Array createArrayOf(String typeName, Object[] elements) throws SQLException {
		return connection.createArrayOf(typeName, elements);
	}

	/**
	 * Creates the blob.
	 *
	 * @return the blob
	 * @throws SQLException
	 *             the SQL exception
	 */
	public Blob createBlob() throws SQLException {
		return connection.createBlob();
	}

	/**
	 * Creates the clob.
	 *
	 * @return the clob
	 * @throws SQLException
	 *             the SQL exception
	 */
	public Clob createClob() throws SQLException {
		return connection.createClob();
	}

	/**
	 * Creates the N clob.
	 *
	 * @return the n clob
	 * @throws SQLException
	 *             the SQL exception
	 */
	public NClob createNClob() throws SQLException {
		return connection.createNClob();
	}

	/**
	 * Creates the SQLXML.
	 *
	 * @return the sqlxml
	 * @throws SQLException
	 *             the SQL exception
	 */
	public SQLXML createSQLXML() throws SQLException {
		return connection.createSQLXML();
	}

	/**
	 * Creates the statement.
	 *
	 * @return the statement
	 * @throws SQLException
	 *             the SQL exception
	 */
	public Statement createStatement() throws SQLException {
		return connection.createStatement();
	}

	/**
	 * Creates the statement.
	 *
	 * @param resultSetType
	 *            the result set type
	 * @param resultSetConcurrency
	 *            the result set concurrency
	 * @param resultSetHoldability
	 *            the result set holdability
	 * @return the statement
	 * @throws SQLException
	 *             the SQL exception
	 */
	public Statement createStatement(int resultSetType, int resultSetConcurrency, int resultSetHoldability)
			throws SQLException {
		return connection.createStatement(resultSetType, resultSetConcurrency, resultSetHoldability);
	}

	/**
	 * Creates the statement.
	 *
	 * @param resultSetType
	 * @param resultSetConcurrency
	 * @return the statement
	 * @throws SQLException
	 *             the SQL exception
	 */
	public Statement createStatement(int resultSetType, int resultSetConcurrency) throws SQLException {
		return connection.createStatement(resultSetType, resultSetConcurrency);
	}

	/**
	 * Creates the struct.
	 *
	 * @param typeName
	 * @param attributes
	 * @return the struct
	 * @throws SQLException
	 *             the SQL exception
	 */
	public Struct createStruct(String typeName, Object[] attributes) throws SQLException {
		return connection.createStruct(typeName, attributes);
	}

	/**
	 * Gets the auto commit.
	 *
	 * @return the auto commit
	 * @throws SQLException
	 *             the SQL exception
	 */
	public boolean getAutoCommit() throws SQLException {
		return connection.getAutoCommit();
	}

	/**
	 * Gets the catalog.
	 *
	 * @return the catalog
	 * @throws SQLException
	 *             the SQL exception
	 */
	public String getCatalog() throws SQLException {
		return connection.getCatalog();
	}

	/**
	 * Gets the client info.
	 *
	 * @return the client info
	 * @throws SQLException
	 *             the SQL exception
	 */
	public Properties getClientInfo() throws SQLException {
		return connection.getClientInfo();
	}

	/**
	 * Gets the client info.
	 *
	 * @param name
	 * @return the client info
	 * @throws SQLException
	 *             the SQL exception
	 */
	public String getClientInfo(String name) throws SQLException {
		return connection.getClientInfo(name);
	}

	/**
	 * Gets the holdability.
	 *
	 * @return the holdability
	 * @throws SQLException
	 *             the SQL exception
	 */
	public int getHoldability() throws SQLException {
		return connection.getHoldability();
	}

	/**
	 * Gets the meta data.
	 *
	 * @return the meta data
	 * @throws SQLException
	 *             the SQL exception
	 */
	public DatabaseMetaData getMetaData() throws SQLException {
		return connection.getMetaData();
	}

	/**
	 * Gets the network timeout.
	 *
	 * @return the network timeout
	 * @throws SQLException
	 *             the SQL exception
	 */
	public int getNetworkTimeout() throws SQLException {
		return connection.getNetworkTimeout();
	}

	/**
	 * Gets the schema.
	 *
	 * @return the schema
	 * @throws SQLException
	 *             the SQL exception
	 */
	public String getSchema() throws SQLException {
		return connection.getSchema();
	}

	/**
	 * Gets the transaction isolation.
	 *
	 * @return the transaction isolation
	 * @throws SQLException
	 *             the SQL exception
	 */
	public int getTransactionIsolation() throws SQLException {
		return connection.getTransactionIsolation();
	}

	/**
	 * Gets the type map.
	 *
	 * @return the type map
	 * @throws SQLException
	 *             the SQL exception
	 */
	public Map<String, Class<?>> getTypeMap() throws SQLException {
		return connection.getTypeMap();
	}

	/**
	 * Gets the warnings.
	 *
	 * @return the warnings
	 * @throws SQLException
	 *             the SQL exception
	 */
	public SQLWarning getWarnings() throws SQLException {
		return connection.getWarnings();
	}

	/**
	 * Checks if is closed.
	 *
	 * @return true, if is closed
	 * @throws SQLException
	 *             the SQL exception
	 */
	public boolean isClosed() throws SQLException {
		return connection.isClosed();
	}

	/**
	 * Checks if is read only.
	 *
	 * @return true, if is read only
	 * @throws SQLException
	 *             the SQL exception
	 */
	public boolean isReadOnly() throws SQLException {
		return connection.isReadOnly();
	}

	/**
	 * Checks if is valid.
	 *
	 * @param timeout
	 *            the timeout
	 * @return true, if is valid
	 * @throws SQLException
	 *             the SQL exception
	 */
	public boolean isValid(int timeout) throws SQLException {
		return connection.isValid(timeout);
	}

	/**
	 * Checks if is wrapper for.
	 *
	 * @param iface
	 *            the iface
	 * @return true, if is wrapper for
	 * @throws SQLException
	 *             the SQL exception
	 */
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		return connection.isWrapperFor(iface);
	}

	/**
	 * Native SQL.
	 *
	 * @param sql
	 *            the sql
	 * @return the string
	 * @throws SQLException
	 *             the SQL exception
	 */
	public String nativeSQL(String sql) throws SQLException {
		return connection.nativeSQL(sql);
	}

	/**
	 * Prepare call.
	 *
	 * @param sql
	 *            the sql
	 * @param resultSetType
	 *            the result set type
	 * @param resultSetConcurrency
	 *            the result set concurrency
	 * @param resultSetHoldability
	 *            the result set holdability
	 * @return the callable statement
	 * @throws SQLException
	 *             the SQL exception
	 */
	public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency,
			int resultSetHoldability) throws SQLException {
		return connection.prepareCall(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
	}

	/**
	 * Prepare call.
	 *
	 * @param sql
	 *            the sql
	 * @param resultSetType
	 *            the result set type
	 * @param resultSetConcurrency
	 *            the result set concurrency
	 * @return the callable statement
	 * @throws SQLException
	 *             the SQL exception
	 */
	public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
		return connection.prepareCall(sql, resultSetType, resultSetConcurrency);
	}

	/**
	 * Prepare call.
	 *
	 * @param sql
	 *            the sql
	 * @return the callable statement
	 * @throws SQLException
	 *             the SQL exception
	 */
	public CallableStatement prepareCall(String sql) throws SQLException {
		return connection.prepareCall(sql);
	}

	/**
	 * Prepare statement.
	 *
	 * @param sql
	 *            the sql
	 * @param resultSetType
	 *            the result set type
	 * @param resultSetConcurrency
	 *            the result set concurrency
	 * @param resultSetHoldability
	 *            the result set holdability
	 * @return the prepared statement
	 * @throws SQLException
	 *             the SQL exception
	 */
	public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency,
			int resultSetHoldability) throws SQLException {
		return connection.prepareStatement(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
	}

	/**
	 * Prepare statement.
	 *
	 * @param sql
	 *            the sql
	 * @param resultSetType
	 *            the result set type
	 * @param resultSetConcurrency
	 *            the result set concurrency
	 * @return the prepared statement
	 * @throws SQLException
	 *             the SQL exception
	 */
	public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency)
			throws SQLException {
		return connection.prepareStatement(sql, resultSetType, resultSetConcurrency);
	}

	/**
	 * Prepare statement.
	 *
	 * @param sql
	 *            the sql
	 * @param autoGeneratedKeys
	 *            the auto generated keys
	 * @return the prepared statement
	 * @throws SQLException
	 *             the SQL exception
	 */
	public PreparedStatement prepareStatement(String sql, int autoGeneratedKeys) throws SQLException {
		return connection.prepareStatement(sql, autoGeneratedKeys);
	}

	/**
	 * Prepare statement.
	 *
	 * @param sql
	 *            the sql
	 * @param columnIndexes
	 *            the column indexes
	 * @return the prepared statement
	 * @throws SQLException
	 *             the SQL exception
	 */
	public PreparedStatement prepareStatement(String sql, int[] columnIndexes) throws SQLException {
		return connection.prepareStatement(sql, columnIndexes);
	}

	/**
	 * Prepare statement.
	 *
	 * @param sql
	 *            the sql
	 * @param columnNames
	 *            the column names
	 * @return the prepared statement
	 * @throws SQLException
	 *             the SQL exception
	 */
	public PreparedStatement prepareStatement(String sql, String[] columnNames) throws SQLException {
		return connection.prepareStatement(sql, columnNames);
	}

	/**
	 * Prepare statement.
	 *
	 * @param sql
	 *            the sql
	 * @return the prepared statement
	 * @throws SQLException
	 *             the SQL exception
	 */
	public PreparedStatement prepareStatement(String sql) throws SQLException {
		return connection.prepareStatement(sql);
	}

	/**
	 * Release savepoint.
	 *
	 * @param savepoint
	 *            the savepoint
	 * @throws SQLException
	 *             the SQL exception
	 */
	public void releaseSavepoint(Savepoint savepoint) throws SQLException {
		connection.releaseSavepoint(savepoint);
	}

	/**
	 * Rollback.
	 *
	 * @throws SQLException
	 *             the SQL exception
	 */
	public void rollback() throws SQLException {
		connection.rollback();
	}

	/**
	 * Rollback.
	 *
	 * @param savepoint
	 *            the savepoint
	 * @throws SQLException
	 *             the SQL exception
	 */
	public void rollback(Savepoint savepoint) throws SQLException {
		connection.rollback(savepoint);
	}

	/**
	 * Sets the auto commit.
	 *
	 * @param autoCommit
	 *            the new auto commit
	 * @throws SQLException
	 *             the SQL exception
	 */
	public void setAutoCommit(boolean autoCommit) throws SQLException {
		connection.setAutoCommit(autoCommit);
	}

	/**
	 * Sets the catalog.
	 *
	 * @param catalog
	 *            the new catalog
	 * @throws SQLException
	 *             the SQL exception
	 */
	public void setCatalog(String catalog) throws SQLException {
		connection.setCatalog(catalog);
	}

	/**
	 * Sets the client info.
	 *
	 * @param properties
	 *            the new client info
	 * @throws SQLClientInfoException
	 *             the SQL client info exception
	 */
	public void setClientInfo(Properties properties) throws SQLClientInfoException {
		connection.setClientInfo(properties);
	}

	/**
	 * Sets the client info.
	 *
	 * @param name
	 *            the name
	 * @param value
	 *            the value
	 * @throws SQLClientInfoException
	 *             the SQL client info exception
	 */
	public void setClientInfo(String name, String value) throws SQLClientInfoException {
		connection.setClientInfo(name, value);
	}

	/**
	 * Sets the holdability.
	 *
	 * @param holdability
	 *            the new holdability
	 * @throws SQLException
	 *             the SQL exception
	 */
	public void setHoldability(int holdability) throws SQLException {
		connection.setHoldability(holdability);
	}

	/**
	 * Sets the network timeout.
	 *
	 * @param executor
	 *            the executor
	 * @param milliseconds
	 *            the milliseconds
	 * @throws SQLException
	 *             the SQL exception
	 */
	public void setNetworkTimeout(Executor executor, int milliseconds) throws SQLException {
		connection.setNetworkTimeout(executor, milliseconds);
	}

	/**
	 * Sets the read only.
	 *
	 * @param readOnly
	 *            the new read only
	 * @throws SQLException
	 *             the SQL exception
	 */
	public void setReadOnly(boolean readOnly) throws SQLException {
		connection.setReadOnly(readOnly);
	}

	/**
	 * Sets the savepoint.
	 *
	 * @return the savepoint
	 * @throws SQLException
	 *             the SQL exception
	 */
	public Savepoint setSavepoint() throws SQLException {
		return connection.setSavepoint();
	}

	/**
	 * Sets the savepoint.
	 *
	 * @param name
	 *            the name
	 * @return the savepoint
	 * @throws SQLException
	 *             the SQL exception
	 */
	public Savepoint setSavepoint(String name) throws SQLException {
		return connection.setSavepoint(name);
	}

	/**
	 * Sets the schema.
	 *
	 * @param schema
	 *            the new schema
	 * @throws SQLException
	 *             the SQL exception
	 */
	public void setSchema(String schema) throws SQLException {
		connection.setSchema(schema);
	}

	/**
	 * Sets the transaction isolation.
	 *
	 * @param level
	 *            the new transaction isolation
	 * @throws SQLException
	 *             the SQL exception
	 */
	public void setTransactionIsolation(int level) throws SQLException {
		connection.setTransactionIsolation(level);
	}

	/**
	 * Sets the type map.
	 *
	 * @param map
	 *            the map
	 * @throws SQLException
	 *             the SQL exception
	 */
	public void setTypeMap(Map<String, Class<?>> map) throws SQLException {
		connection.setTypeMap(map);
	}

	/**
	 * Unwrap.
	 *
	 * @param <T>
	 *            the generic type
	 * @param iface
	 *            the iface
	 * @return the t
	 * @throws SQLException
	 *             the SQL exception
	 */
	public <T> T unwrap(Class<T> iface) throws SQLException {
		return connection.unwrap(iface);
	}

}
