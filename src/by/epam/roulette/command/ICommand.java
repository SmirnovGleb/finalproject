package by.epam.roulette.command;

import javax.servlet.http.HttpServletRequest;

import by.epam.roulette.controller.PathType;

/**
 * The Interface ICommand.
 */
public interface ICommand {
	
	/**
	 * Execute.
	 *
	 * @param request the request
	 * @return the path type
	 */
	PathType execute(HttpServletRequest request);
}
