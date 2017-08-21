package by.epam.roulette.command;

import javax.servlet.http.HttpServletRequest;

import by.epam.roulette.controller.PathType;
import by.epam.roulette.controller.TypeAction;
import by.epam.roulette.service.ServiceUser;
import by.epam.roulette.validator.UserParametersValidator;

public class RegistrationUserCommand implements ICommand {
	private static final String PATH_IF_USER_IS_NOT_ADDED = "jsp/registration.jsp";
	private static final String PATH_TO_LOGIN = "jsp/login.jsp";
	private static final String INFO_FOR_USER = "infoforguest";
	private static final String BAD_DATA_VALUE = "Invalid Login or Password";
	private static final String NAME_PARAMETER = "name";
	private static final String LOGIN_PARAMETER = "login";
	private static final String PASSWORD1_PARAMETER = "password1";
	private static final String PASSWORD2_PARAMETER = "password2";
	private static final String MAIL_PARAMETER = "mail";

	@Override
	public PathType execute(HttpServletRequest request) {
		String currentPath = PATH_IF_USER_IS_NOT_ADDED;
		String name = request.getParameter(NAME_PARAMETER);
		String login = request.getParameter(LOGIN_PARAMETER);
		String password1 = request.getParameter(PASSWORD1_PARAMETER);
		String password2 = request.getParameter(PASSWORD2_PARAMETER);
		String email = request.getParameter(MAIL_PARAMETER);

		if (password1.equals(password2) && UserParametersValidator.validateLoginPassword(password1)
				&& UserParametersValidator.validateLoginPassword(login)
				&& UserParametersValidator.validateEmail(email)) {
			if (ServiceUser.addUser(name, login, password1, email)) {
				currentPath = PATH_TO_LOGIN;
			}
		} else {
			request.getSession().setAttribute(INFO_FOR_USER, BAD_DATA_VALUE);
		}

		return new PathType(currentPath, TypeAction.REDIRECT);
	}

}
