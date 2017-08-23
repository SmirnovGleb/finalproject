package by.epam.roulette.command;

import javax.servlet.http.HttpServletRequest;

import by.epam.roulette.controller.PathType;
import by.epam.roulette.controller.TypeAction;
import by.epam.roulette.entity.User;
import by.epam.roulette.service.ServiceUser;

/**
 * The Class ChangePasswordCommand.
 */
public class ChangePasswordCommand implements ICommand {
	private static final String PATH_IF_USER_IS_NOT_FOUND = "jsp/login.jsp";
	private static final String PATH_TO_ERROR_PAGE = "jsp/error.jsp";
	private static final String PATH_TO_PERSONAL_ACCOUNT = "jsp/account.jsp";
	private static final String USER_PARAMETER = "user";
	private static final String OLD_PASSWORD_PARAMETER = "oldpassword";
	private static final String NEW_PASSWORD_PARAMETER = "newpassword";

	/**
	 * Change Password, Checking user status
	 * 
	 * @param request
	 */
	@Override
	public PathType execute(HttpServletRequest request) {
		String currentPath = PATH_TO_ERROR_PAGE;
		String oldPassword = request.getParameter(OLD_PASSWORD_PARAMETER);
		String newPassword = request.getParameter(NEW_PASSWORD_PARAMETER);
		User user = (User) request.getSession().getAttribute(USER_PARAMETER);
		if (user != null) {
			if (ServiceUser.changePassword(user, oldPassword, newPassword)) {
				currentPath = PATH_TO_PERSONAL_ACCOUNT;
			}
		} else {
			currentPath = PATH_IF_USER_IS_NOT_FOUND;
		}
		return new PathType(currentPath, TypeAction.REDIRECT);
	}
}
