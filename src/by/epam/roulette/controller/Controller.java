package by.epam.roulette.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.roulette.commandfactory.CommandFactory;
import by.epam.roulette.pool.ConnectionPool;

/**
 * The Class Controller.
 */
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String COMMAND_PARAMETER = "command";

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doWork(request, response);
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doWork(request, response);
	}

	/**
	 * Do work.
	 *
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @throws ServletException
	 *             the servlet exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	private void doWork(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String command = request.getParameter(COMMAND_PARAMETER);
		CommandFactory factory = CommandFactory.getInstance();
		PathType pathType = factory.getCommand(command).execute(request);

		switch (pathType.getType()) {
		case FORWARD:
			RequestDispatcher dispatcher = request.getRequestDispatcher(pathType.getPath());
			dispatcher.forward(request, response);
			break;
		case REDIRECT:
			response.sendRedirect(pathType.getPath());
			break;
		}
	}

	/**
	 * Destroy connection pool and controller.
	 */
	@Override
	public void destroy() {
		ConnectionPool.getInstance().destroyConnectionPool();
		super.destroy();
	}
}
