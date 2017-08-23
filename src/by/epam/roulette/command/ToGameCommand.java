package by.epam.roulette.command;

import javax.servlet.http.HttpServletRequest;

import by.epam.roulette.controller.PathType;
import by.epam.roulette.controller.TypeAction;
import by.epam.roulette.entity.User;

/**
 * The Class ToGameCommand.
 */
public class ToGameCommand implements ICommand {
	private static final String PATH_TO_GAME_ROOM = "jsp/game.jsp";
	private static final String PATH_TO_LOGIN = "jsp/login.jsp";

	/**
	 * Path to a game page with checking data
	 * 
	 * @param request
	 */
	@Override
	public PathType execute(HttpServletRequest request) {
		String currentPath = PATH_TO_LOGIN;
		User user = (User) request.getSession().getAttribute("user");
		if (user != null) {
			currentPath = PATH_TO_GAME_ROOM;
		}
		return new PathType(currentPath, TypeAction.FORWARD);
	}

}
