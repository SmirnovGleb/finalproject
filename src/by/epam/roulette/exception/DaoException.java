package by.epam.roulette.exception;

public class DaoException extends Exception {
	private static final long serialVersionUID = 6927886646210591072L;

	public DaoException() {
		super();
	}

	public DaoException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public DaoException(String arg0) {
		super(arg0);
	}

	public DaoException(Throwable arg0) {
		super(arg0);
	}

}
