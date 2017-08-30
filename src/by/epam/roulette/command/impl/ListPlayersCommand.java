package by.epam.roulette.command.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.roulette.command.ICommand;
import by.epam.roulette.controller.PathType;
import by.epam.roulette.controller.TypeAction;
import by.epam.roulette.entity.User;
import by.epam.roulette.exception.RouletteException;
import by.epam.roulette.service.ServiceAdmin;

/**
 * The Class ListPlayersCommand.
 */
public class ListPlayersCommand implements ICommand {
	private static Logger logger = LogManager.getLogger(ListPlayersCommand.class);
	private static final String PATH_TO_LISTPLAYERS_PAGE = "jsp/listplayers.jsp";
	private static final String PATH_TO_LOGIN = "jsp/login.jsp";
	private static final String PATH_TO_ERROR_PAGE = "jsp/error.jsp";
	private static final String USER_PARAMETER = "user";
	private static final String LIST_USERS_ATTRIBUTE_NAME = "listplayers";
	private static final String MAP_USERS_ATTRIBUTE_NAME = "maplockedplayers";

	/**
	 * Send a list of players
	 * 
	 * @param request
	 */
	@Override
	public PathType execute(HttpServletRequest request) {
		String currentPath = PATH_TO_ERROR_PAGE;
		User user = (User) request.getSession().getAttribute(USER_PARAMETER);
		if (user != null && user.isAdmin()) {
			ArrayList<User> players = null;
			HashMap<User, Timestamp> lockedPlayers = null;
			try {
				players = ServiceAdmin.findPlayers();
				lockedPlayers = ServiceAdmin.findBlockedPlayers();
			} catch (RouletteException e) {
				currentPath = PATH_TO_ERROR_PAGE;
				logger.log(Level.ERROR, e);
			}

			request.setAttribute(LIST_USERS_ATTRIBUTE_NAME, players);
			request.setAttribute(MAP_USERS_ATTRIBUTE_NAME, lockedPlayers);
			currentPath = PATH_TO_LISTPLAYERS_PAGE;
		} else {
			currentPath = PATH_TO_LOGIN;
		}
		return new PathType(currentPath, TypeAction.FORWARD);
	}
}
