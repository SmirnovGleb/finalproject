package by.epam.roulette.exception;

/**
 * The Class RouletteException.
 */
public class RouletteException extends Exception {

	private static final long serialVersionUID = -6927886646210591032L;

	/**
	 * Instantiates a new roulette exception.
	 */
	public RouletteException() {
		super();
	}

	/**
	 * Instantiates a new roulette exception.
	 *
	 * @param arg0
	 *            the arg 0
	 * @param arg1
	 *            the arg 1
	 */
	public RouletteException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	/**
	 * Instantiates a new roulette exception.
	 *
	 * @param arg0
	 *            the arg 0
	 */
	public RouletteException(String arg0) {
		super(arg0);
	}

	/**
	 * Instantiates a new roulette exception.
	 *
	 * @param arg0
	 *            the arg 0
	 */
	public RouletteException(Throwable arg0) {
		super(arg0);
	}

}
