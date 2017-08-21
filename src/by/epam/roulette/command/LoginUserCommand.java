package by.epam.roulette.command;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.roulette.controller.PathType;
import by.epam.roulette.controller.TypeAction;
import by.epam.roulette.converter.MD5Converter;
import by.epam.roulette.entity.User;
import by.epam.roulette.exception.RouletteException;
import by.epam.roulette.service.ServiceUser;

public class LoginUserCommand implements ICommand {
	private static Logger logger = LogManager.getLogger(LoginUserCommand.class);
	private static final String PATH_IF_USER_IS_NOT_FOUND = "jsp/login.jsp";
	private static final String PATH_TO_ERROR_PAGE = "jsp/error.jsp";
	private static final String PATH_TO_GAME_ROOM = "jsp/game.jsp";
	private static final String PATH_TO_ADMINISTRATOR_PAGE = "jsp/administrator.jsp";
	private static final String LOGIN_PARAMETER = "login";
	private static final String PASSWORD_PARAMETER = "pass";
	private static final String USERS_ATTRIBUTE_NAME = "user";
	private static final String INFO_FOR_USER = "infoforguest";
	private static final String BAD_DATA_VALUE = "Invalid Login or Password";
	private static final String INFO_USER_IS_LOCKED = "Player is Locked";

	@Override
	public PathType execute(HttpServletRequest request) {
		String currentPath = PATH_TO_ERROR_PAGE;
		String login = request.getParameter(LOGIN_PARAMETER);
		String password = request.getParameter(PASSWORD_PARAMETER);
		User user = null;
		try {
			user = ServiceUser.loginUser(login);
			if (user != null && user.getPassword().equals(new MD5Converter().convert(password))
					&& !ServiceUser.isPlayerLocked(user.getId())) {
				currentPath = PATH_TO_GAME_ROOM;
				request.getSession().setAttribute(USERS_ATTRIBUTE_NAME, user);
				if (user.isAdmin()) {
					currentPath = PATH_TO_ADMINISTRATOR_PAGE;
				}
				if (ServiceUser.hasOverdueCredit(user)) {
					ServiceUser.returnCredit(user);
				}
			} else if (user != null && user.getPassword().equals(new MD5Converter().convert(password))
					&& ServiceUser.isPlayerLocked(user.getId())) {
				request.getSession().setAttribute(INFO_FOR_USER, INFO_USER_IS_LOCKED);
				currentPath = PATH_IF_USER_IS_NOT_FOUND;
			} else {
				request.getSession().setAttribute(INFO_FOR_USER, BAD_DATA_VALUE);
				currentPath = PATH_IF_USER_IS_NOT_FOUND;
			}
		} catch (RouletteException e) {
			currentPath = PATH_TO_ERROR_PAGE;
			logger.log(Level.ERROR, e);
		}
		return new PathType(currentPath, TypeAction.FORWARD);
	}
}
