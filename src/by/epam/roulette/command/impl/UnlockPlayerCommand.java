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
 * The Class UnlockPlayerCommand.
 */
public class UnlockPlayerCommand implements ICommand {
	private static Logger logger = LogManager.getLogger(UnlockPlayerCommand.class);
	private static final String PATH_IF_USER_IS_NOT_FOUND = "jsp/login.jsp";
	private static final String PATH_TO_LISTPLAYERS_PAGE = "jsp/listplayers.jsp";
	private static final String PATH_TO_ERROR_PAGE = "jsp/error.jsp";
	private static final String USER_PARAMETER = "user";
	private static final String USERS_ID_PARAMETER = "lockbyid";
	private static final String LIST_PLAYERS_ATTRIBUTE = "listplayers";
	private static final String MAP_LOCKED_PLAYERS_ATTRIBUTE = "maplockedplayers";

	/**
	 * Unlock player
	 * 
	 * @param request
	 */
	@Override
	public PathType execute(HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter(USERS_ID_PARAMETER));
		String currentPath = PATH_IF_USER_IS_NOT_FOUND;
		User user = (User) request.getSession().getAttribute(USER_PARAMETER);
		if (user != null && user.isAdmin()) {
			ServiceAdmin.unlockPlayer(id);
			ArrayList<User> players = null;
			HashMap<User, Timestamp> lockedPlayers = null;
			try {
				players = ServiceAdmin.findPlayers();
				lockedPlayers = ServiceAdmin.findBlockedPlayers();
			} catch (RouletteException e) {
				currentPath = PATH_TO_ERROR_PAGE;
				logger.log(Level.ERROR, e);
			}

			request.setAttribute(LIST_PLAYERS_ATTRIBUTE, players);
			request.setAttribute(MAP_LOCKED_PLAYERS_ATTRIBUTE, lockedPlayers);
			currentPath = PATH_TO_LISTPLAYERS_PAGE;
		}
		return new PathType(currentPath, TypeAction.FORWARD);
	}
}
