package by.epam.roulette.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.roulette.controller.PathType;
import by.epam.roulette.controller.TypeAction;
import by.epam.roulette.entity.Bet;
import by.epam.roulette.entity.User;
import by.epam.roulette.exception.RouletteException;
import by.epam.roulette.service.ServiceBet;

public class ListBetsCommand implements ICommand{
	private static Logger logger = LogManager.getLogger(ListBetsCommand.class);
	private static final String PATH_IF_USER_IS_NOT_FOUND = "jsp/login.jsp"; 
	private static final String PATH_TO_LISTBETS_PAGE = "jsp/listbets.jsp";
	private static final String PATH_TO_ERROR_PAGE = "jsp/error404.jsp";
	private static final String USER_PARAMETER = "user";

	@Override
	public PathType execute(HttpServletRequest request) {
		String currentPath = PATH_IF_USER_IS_NOT_FOUND;
		User user = (User) request.getSession().getAttribute(USER_PARAMETER);		
		if(user!=null){
			List<Bet> bets = null;
			try {
				bets = ServiceBet.findBetsByUserId(user.getId());
			} catch (RouletteException e) {
				currentPath = PATH_TO_ERROR_PAGE;
				logger.log(Level.ERROR, e);
			}
			request.getSession().setAttribute("listbets", bets);
			currentPath = PATH_TO_LISTBETS_PAGE;
		}
		return new PathType(currentPath, TypeAction.FORWARD);
	}

}
