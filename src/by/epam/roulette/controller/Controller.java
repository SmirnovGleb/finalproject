package by.epam.roulette.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.roulette.commandfactory.CommandFactory;
import by.epam.roulette.pool.ConnectionPool;

public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String COMMAND_PARAMETER = "command";

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doWork(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doWork(request, response);
	}

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

	@Override
	public void destroy() {
		ConnectionPool.getInstance().destroyConnectionPool();
		super.destroy();
	}
}
