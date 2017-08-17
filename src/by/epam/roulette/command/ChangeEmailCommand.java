package by.epam.roulette.command;

import javax.servlet.http.HttpServletRequest;

import by.epam.roulette.controller.PathType;
import by.epam.roulette.controller.TypeAction;
import by.epam.roulette.entity.User;
import by.epam.roulette.service.ServiceUser;

public class ChangeEmailCommand implements ICommand{
	private static final String PATH_IF_USER_IS_NOT_FOUND = "jsp/login.jsp"; 
	private static final String PATH_TO_ERROR_PAGE = "jsp/error404.jsp"; 
	private static final String PATH_TO_PERSONAL_ACCOUNT = "jsp/account.jsp";
	private static final String USER_PARAMETER = "user";
	private static final String NEW_EMAIL_PARAMETER = "newemail";

	@Override
	public PathType execute(HttpServletRequest request) {
		String currentPath = PATH_TO_ERROR_PAGE;
		
		String newEmail = request.getParameter(NEW_EMAIL_PARAMETER);
		User user = (User) request.getSession().getAttribute(USER_PARAMETER);
		if(user!=null){
			if(ServiceUser.changeEmail(user, newEmail)){
				currentPath = PATH_TO_PERSONAL_ACCOUNT;
			}
		}
		else{
			currentPath = PATH_IF_USER_IS_NOT_FOUND;
		}
		return new PathType(currentPath, TypeAction.REDIRECT);
	}
}
