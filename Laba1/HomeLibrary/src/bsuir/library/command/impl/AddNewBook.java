package bsuir.library.command.impl;

import bsuir.library.command.Command;
import bsuir.library.command.exception.CommandException;
import bsuir.library.domain.TypeBook;
import bsuir.library.service.LibraryService;
import bsuir.library.service.ServiceFactory;

public class AddNewBook implements Command {

	@Override
	public String execute(String request) throws CommandException {
		ServiceFactory servaceFactory = new ServiceFactory();
		LibraryService libraryService = servaceFactory.getLiraryService();
		String title = request.split("\\|")[1];
		TypeBook typeOfBook = TypeBook.valueOf(request.split("\\|")[2]);
		try {
			libraryService.addNewBook(title, typeOfBook);
		} catch (Exception e) {
			throw new CommandException(e);
		}

		return "Новая книга успешно добавлена";
	}

}
