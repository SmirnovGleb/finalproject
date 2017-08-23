package by.epam.roulette.command;

import javax.servlet.http.HttpServletRequest;

import by.epam.roulette.controller.PathType;
import by.epam.roulette.controller.TypeAction;
import by.epam.roulette.entity.User;
import by.epam.roulette.service.ServiceAdmin;

/**
 * The Class ChangePercentCommand.
 */
public class ChangePercentCommand implements ICommand {
	private static final String PATH_TO_PERSONAL_ACCOUNT = "jsp/administrator.jsp";
	private static final String PATH_TO_LOGIN = "jsp/login.jsp";
	private static final String PATH_TO_ERROR_PAGE = "jsp/error.jsp";

	/**
	 * Change credit's percent, Checking user status
	 * 
	 * @param request
	 */
	@Override
	public PathType execute(HttpServletRequest request) {
		String currentPath = PATH_TO_ERROR_PAGE;
		int percent = Integer.parseInt(request.getParameter("percent"));
		User user = (User) request.getSession().getAttribute("user");
		if (user != null && user.isAdmin()) {
			if (ServiceAdmin.changePercent(percent)) {
				currentPath = PATH_TO_PERSONAL_ACCOUNT;
			}
		} else {
			currentPath = PATH_TO_LOGIN;
		}
		return new PathType(currentPath, TypeAction.REDIRECT);
	}
}
