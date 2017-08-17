package by.epam.roulette.command;

import javax.servlet.http.HttpServletRequest;

import by.epam.roulette.controller.PathType;

public interface ICommand {
	PathType execute(HttpServletRequest request);
}
