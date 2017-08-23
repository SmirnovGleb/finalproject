package by.epam.roulette.command;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;

import by.epam.roulette.controller.PathType;
import by.epam.roulette.controller.TypeAction;
import by.epam.roulette.entity.User;
import by.epam.roulette.service.ServiceUser;

/**
 * The Class UserDepositCommand.
 */
public class UserDepositCommand implements ICommand {
	private static final String PATH_IF_USER_IS_NOT_FOUND = "jsp/login.jsp";
	private static final String PATH_TO_ERROR_PAGE = "jsp/error.jsp";
	private static final String PATH_TO_PERSONAL_ACCOUNT = "jsp/account.jsp";
	private static final String MONEY_PARAMETER = "money";
	private static final String USER_PARAMETER = "user";

	/**
	 * User's deposit
	 * 
	 * @param request
	 */
	@Override
	public PathType execute(HttpServletRequest request) {
		String currentPath = PATH_IF_USER_IS_NOT_FOUND;
		String money = request.getParameter(MONEY_PARAMETER);
		User user = (User) request.getSession().getAttribute(USER_PARAMETER);
		if (user != null) {
			currentPath = PATH_TO_PERSONAL_ACCOUNT;
			if (!ServiceUser.cashIn(user, new BigDecimal(money))) {
				currentPath = PATH_TO_ERROR_PAGE;
			}
		}
		return new PathType(currentPath, TypeAction.REDIRECT);
	}

}
