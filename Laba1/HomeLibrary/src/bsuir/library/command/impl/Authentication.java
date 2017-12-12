package bsuir.library.command.impl;

import bsuir.library.command.Command;
import bsuir.library.command.exception.CommandException;
import bsuir.library.service.ServiceFactory;
import bsuir.library.service.UserService;
import bsuir.library.service.exception.ServiceException;

public class Authentication implements Command {

	@Override
	public String execute(String request) throws CommandException {
		ServiceFactory servaceFactory = new ServiceFactory();
		UserService userServise = servaceFactory.getUserService();
		String login = request.split("\\|")[1];
		String password = request.split("\\|")[2];
		try {
			userServise.authenticate(login, password);
		} catch (ServiceException e) {		
			throw new CommandException(e);
		}
		return "Аутентификация прошла успешно";
	}
}