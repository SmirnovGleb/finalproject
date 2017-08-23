package by.epam.roulette.command;

import javax.servlet.http.HttpServletRequest;

import by.epam.roulette.controller.PathType;
import by.epam.roulette.controller.TypeAction;
import by.epam.roulette.entity.User;

/**
 * The Class LanguageCommand.
 */
public class LanguageCommand implements ICommand {
	private static final String PATH_TO_REGISTRATION_OR_LOGIN_PAGE = "jsp/regorlogin.jsp";
	private static final String PATH_TO_ADMINISTRATOR_PAGE = "jsp/administrator.jsp";
	private static final String PATH_TO_GAME_ROOM = "jsp/game.jsp";
	private static final String USER_ATTRIBUTE_NAME = "user";
	private static final String LANGUAGE_PARAMETER_NAME = "language";

	/**
	 * change language, Checking user status
	 * 
	 * @param request
	 */
	@Override
	public PathType execute(HttpServletRequest request) {
		String currentPath = PATH_TO_REGISTRATION_OR_LOGIN_PAGE;
		String language = request.getParameter(LANGUAGE_PARAMETER_NAME);
		request.getSession().setAttribute(LANGUAGE_PARAMETER_NAME, language);
		User user = (User) request.getSession().getAttribute(USER_ATTRIBUTE_NAME);
		if (user != null) {
			currentPath = PATH_TO_GAME_ROOM;
			if (user.isAdmin()) {
				currentPath = PATH_TO_ADMINISTRATOR_PAGE;
			}
		}
		return new PathType(currentPath, TypeAction.FORWARD);
	}
}
