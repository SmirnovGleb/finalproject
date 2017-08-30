package by.epam.roulette.command.impl;

import javax.servlet.http.HttpServletRequest;

import by.epam.roulette.command.ICommand;
import by.epam.roulette.controller.PathType;
import by.epam.roulette.controller.TypeAction;
import by.epam.roulette.entity.User;

/**
 * The Class LogoutCommand.
 */
public class LogoutCommand implements ICommand {
	private static final String PATH_USER_LOGOUT = "jsp/regorlogin.jsp";
	private static final String USER_PARAMETER = "user";
	private static final String INFO_FOR_USER = "infoforguest";

	/**
	 * User's logout 
	 * 
	 * @param request
	 */
	@Override
	public PathType execute(HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute(USER_PARAMETER);
		if (user != null) {
			request.getSession().setAttribute(USER_PARAMETER, null);
			request.getSession().setAttribute(INFO_FOR_USER, null);
		}
		return new PathType(PATH_USER_LOGOUT, TypeAction.FORWARD);
	}

}
