package by.epam.roulette.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.roulette.commandfactory.CommandFactory;
import by.epam.roulette.pool.ConnectionPool;


public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = LogManager.getLogger(Controller.class);
	private static final String COMMAND_PARAMETER = "command";
	private static final String PATH_TO_ERROR_PAGE = "jsp/error404.jsp";

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doWork(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doWork(request, response);
	}
	
	private void doWork(HttpServletRequest request, HttpServletResponse response){
		String command = request.getParameter(COMMAND_PARAMETER);
		CommandFactory factory = new CommandFactory();
		PathType helper = factory.getCommand(command).execute(request);
		 try {
			 switch (helper.getType()) {
		        case FORWARD:
		            RequestDispatcher dispatcher = request.getRequestDispatcher(helper.getPath());
					dispatcher.forward(request, response);
		            break;
		        case REDIRECT:
		            response.sendRedirect(helper.getPath());
		            break;
			}
		} catch (ServletException | IOException e) {
			try {
				response.sendRedirect(PATH_TO_ERROR_PAGE);
			} catch (IOException e1) {
				logger.log(Level.ERROR, e1);
			}
			logger.log(Level.ERROR, e);
		}
	}

	@Override
	public void destroy() {
		ConnectionPool.getInstance().destroyConnectionPool();
		super.destroy();
	}
}
