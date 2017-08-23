package by.epam.roulette.command;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;

import by.epam.roulette.controller.PathType;
import by.epam.roulette.controller.TypeAction;
import by.epam.roulette.entity.User;
import by.epam.roulette.entity.Wheel;
import by.epam.roulette.service.ServiceBet;
import by.epam.roulette.service.ServiceUser;

/**
 * The Class BetUserCommand.
 */
public class BetUserCommand implements ICommand {
	private static final String PATH_IF_USER_IS_NOT_FOUND = "jsp/login.jsp";
	private static final String PATH_TO_ERROR_PAGE = "jsp/error.jsp";
	private static final String PATH_TO_GAME_ROOM = "jsp/game.jsp";
	private static final String USER_ATTRIBUTE_NAME = "user";
	private static final String BET_PARAMETER_NAME = "bet";
	private static final String POSITIONS_PARAMETER_NAME = "positions";
	private static final String WIN_NUMBER_ATTRIBUTE_NAME = "winnumb";
	private static final String IS_WIN_ATTRIBUTE_NAME = "iswin";
	private static final String WIN_AMOUNT_ATTRIBUTE_NAME = "winamount";

	/**
	 * Game logic
	 * 
	 * @param request
	 */
	@Override
	public PathType execute(HttpServletRequest request) {
		String currentPath = PATH_TO_ERROR_PAGE;
		User user = (User) request.getSession().getAttribute(USER_ATTRIBUTE_NAME);
		String bet = request.getParameter(BET_PARAMETER_NAME);
		String positions = request.getParameter(POSITIONS_PARAMETER_NAME);
		if (user != null) {
			if ((user.getMoney().signum() == 1) && ((user.getMoney().compareTo(new BigDecimal(bet)) != -1))) {
				int winnum = Wheel.rotateWheel();
				request.getSession().setAttribute(WIN_NUMBER_ATTRIBUTE_NAME, winnum);
				String[] result = ServiceUser.play(user, bet, positions, winnum);
				request.getSession().setAttribute(IS_WIN_ATTRIBUTE_NAME, result[0]);
				request.getSession().setAttribute(WIN_AMOUNT_ATTRIBUTE_NAME, result[1] + "$");
				ServiceBet.addBet(user.getId(), positions, new BigDecimal(bet), result[0],
						new BigDecimal(result[1].trim()));
				currentPath = PATH_TO_GAME_ROOM;
			} else {
				request.getSession().setAttribute(IS_WIN_ATTRIBUTE_NAME, "NO MONEY");
				request.getSession().setAttribute(WIN_AMOUNT_ATTRIBUTE_NAME, "");
				currentPath = PATH_TO_GAME_ROOM;
			}
		} else {
			currentPath = PATH_IF_USER_IS_NOT_FOUND;
		}
		return new PathType(currentPath, TypeAction.REDIRECT);
	}
}
