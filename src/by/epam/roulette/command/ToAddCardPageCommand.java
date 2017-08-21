package by.epam.roulette.command;

import javax.servlet.http.HttpServletRequest;

import by.epam.roulette.controller.PathType;
import by.epam.roulette.controller.TypeAction;
import by.epam.roulette.entity.User;

public class ToAddCardPageCommand implements ICommand {
	private static final String PATH_IF_USER_IS_NOT_FOUND = "jsp/login.jsp";
	private static final String PATH_TO_DEPOSIT_PAGE = "jsp/deposit.jsp";
	private static final String USER_PARAMETER = "user";

	@Override
	public PathType execute(HttpServletRequest request) {
		String currentPath = PATH_IF_USER_IS_NOT_FOUND;
		User user = (User) request.getSession().getAttribute(USER_PARAMETER);
		if (user != null) {
			currentPath = PATH_TO_DEPOSIT_PAGE;
		}
		return new PathType(currentPath, TypeAction.FORWARD);
	}

}
