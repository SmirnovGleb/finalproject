package by.epam.roulette.command;

import java.util.Map;

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

public class ListAllBetsCommand implements ICommand{
	private static Logger logger = LogManager.getLogger(ListAllBetsCommand.class);
	private static final String PATH_TO_LIST_ALL_BETS_PAGE = "jsp/listallbets.jsp";
	private static final String PATH_TO_LOGIN = "jsp/login.jsp";
	private static final String PATH_TO_ERROR_PAGE = "jsp/error404.jsp"; 
	private static final String USER_PARAMETER = "user";
	private static final String MAP_BETS_ATTRIBUTE_NAME = "mapbets";

	@Override
	public PathType execute(HttpServletRequest request) {
		String currentPath = PATH_TO_ERROR_PAGE;
		User user = (User) request.getSession().getAttribute(USER_PARAMETER);
		if(user!=null && user.isAdmin()){
			Map<Bet, String> bets = null;
			try {
				bets = ServiceBet.findAllBets();
			} catch (RouletteException e) {
				currentPath = PATH_TO_ERROR_PAGE;
				logger.log(Level.ERROR, e);
			}
			request.getSession().setAttribute(MAP_BETS_ATTRIBUTE_NAME, bets);
			currentPath = PATH_TO_LIST_ALL_BETS_PAGE;
		}
		else{
			currentPath = PATH_TO_LOGIN;
		}
		return new PathType(currentPath, TypeAction.FORWARD);
	}
}
