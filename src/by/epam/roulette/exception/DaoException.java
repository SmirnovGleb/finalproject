package by.epam.roulette.exception;

/**
 * The Class DaoException.
 */
public class DaoException extends Exception {
	private static final long serialVersionUID = 6927886646210591072L;

	/**
	 * Instantiates a new dao exception.
	 */
	public DaoException() {
		super();
	}

	/**
	 * Instantiates a new dao exception.
	 *
	 * @param arg0
	 *            the arg 0
	 * @param arg1
	 *            the arg 1
	 */
	public DaoException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	/**
	 * Instantiates a new dao exception.
	 *
	 * @param arg0
	 *            the arg 0
	 */
	public DaoException(String arg0) {
		super(arg0);
	}

	/**
	 * Instantiates a new dao exception.
	 *
	 * @param arg0
	 *            the arg 0
	 */
	public DaoException(Throwable arg0) {
		super(arg0);
	}

}
