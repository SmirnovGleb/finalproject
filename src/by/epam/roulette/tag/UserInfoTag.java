package by.epam.roulette.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.roulette.entity.User;

@SuppressWarnings("serial")
public class UserInfoTag extends TagSupport {
	private static Logger logger = LogManager.getLogger(UserInfoTag.class);
	private static final String CONTENT_FIRST_PART = "<div class=\"col-md-2\" id=\"userinfo\"><h3 class=\"textuser\">";
	private static final String CONTENT_SECOND_PART = "</h3><h5><br>money: ";
	private static final String CONTENT_THIRD_PART = "</h5></div>";

	@Override
	public int doStartTag() throws JspException {
		User user = (User) pageContext.getSession().getAttribute("user");
		JspWriter writer = pageContext.getOut();
		try {
			writer.write(
					CONTENT_FIRST_PART + user.getName() + CONTENT_SECOND_PART + user.getMoney() + CONTENT_THIRD_PART);
		} catch (IOException e) {
			logger.log(Level.ERROR, e);
		}
		return EVAL_PAGE;
	}
}
