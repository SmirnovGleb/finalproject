package by.epam.roulette.command;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;

import by.epam.roulette.controller.PathType;
import by.epam.roulette.controller.TypeAction;
import by.epam.roulette.entity.User;
import by.epam.roulette.service.ServiceUser;

public class TakeCreditCommand implements ICommand{
	private static final String PATH_TO_PERSONAL_ACCOUNT = "jsp/account.jsp";
	private static final String PATH_TO_LOGIN = "jsp/login.jsp";
	private static final String PATH_TO_ERROR_PAGE = "jsp/error404.jsp"; 
	private static final String PATH_USER_HAS_CREDIT = "jsp/credit.jsp";	
	private static final String INFO_FOR_USER = "infoforguest";
	private static final String AMOUNT_PARAMETER = "amount";
	private static final String DURATION_PARAMETER = "duration";
	private static final String USER_PARAMETER = "user";
	
	@Override
	public PathType execute(HttpServletRequest request) {
		String currentPath = PATH_TO_ERROR_PAGE;
		BigDecimal money = new BigDecimal(request.getParameter(AMOUNT_PARAMETER));
		int duration = Integer.parseInt(request.getParameter(DURATION_PARAMETER));
		User user = (User) request.getSession().getAttribute(USER_PARAMETER);
		boolean creditIsTaken = false;
		if(user!=null){
			if(ServiceUser.hasCredit(user) || ServiceUser.hasOverdueCredit(user)){
				currentPath = PATH_USER_HAS_CREDIT;
				request.getSession().setAttribute(INFO_FOR_USER, "You had got the Credit before");
			}
			else{
				BigDecimal currentMoney = user.getMoney();
				creditIsTaken = ServiceUser.takeCredit(user, money, duration);
				user.setMoney(money.add(currentMoney));
			}
		}
		else{
			currentPath = PATH_TO_LOGIN;
		}
		if(creditIsTaken){
			currentPath = PATH_TO_PERSONAL_ACCOUNT;
		}
		return new PathType(currentPath, TypeAction.REDIRECT);
	}

}
