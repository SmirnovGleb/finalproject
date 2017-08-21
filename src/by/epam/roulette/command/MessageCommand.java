package by.epam.roulette.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.roulette.controller.PathType;
import by.epam.roulette.controller.TypeAction;
import by.epam.roulette.entity.User;
import by.epam.roulette.exception.RouletteException;
import by.epam.roulette.service.ServiceUser;

public class MessageCommand implements ICommand {
	private static Logger logger = LogManager.getLogger(MessageCommand.class);
	private static final String PATH_IF_USER_IS_NOT_FOUND = "jsp/login.jsp";
	private static final String PATH_TO_MESSAGE_PAGE = "jsp/message.jsp";
	private static final String PATH_TO_ERROR_PAGE = "jsp/error.jsp";
	private static final String USER_PARAMETER = "user";
	private static final String MESSAGE_PARAMETER = "textmessage";
	private static final String MESSAGE_LIST_ATTRIBUTE_NAME = "messageslist";

	@Override
	public PathType execute(HttpServletRequest request) {
		String text = request.getParameter(MESSAGE_PARAMETER);
		String currentPath = PATH_IF_USER_IS_NOT_FOUND;
		User user = (User) request.getSession().getAttribute(USER_PARAMETER);
		if (user != null) {
			List messages = null;
			try {
				messages = ServiceUser.findAllMessages();
			} catch (RouletteException e) {
				currentPath = PATH_TO_ERROR_PAGE;
				logger.log(Level.ERROR, e);
			}
			if (text != null) {
				String[] newMessage = { user.getName(), text };
				ServiceUser.addMessage(text, user);
				messages.add(newMessage);
			}
			request.setAttribute(MESSAGE_LIST_ATTRIBUTE_NAME, messages);
			currentPath = PATH_TO_MESSAGE_PAGE;
		}
		return new PathType(currentPath, TypeAction.FORWARD);
	}

}
