package bsuir.library.service.impl;

import java.util.ArrayList;

import bsuir.library.dao.DAOBook;
import bsuir.library.dao.DAOFactory;
import bsuir.library.dao.exception.DAOException;
import bsuir.library.domain.Book;
import bsuir.library.domain.TypeBook;
import bsuir.library.service.LibraryService;
import bsuir.library.service.exception.ServiceException;

public class LibraryServiceImpl implements LibraryService{


	@Override
	public void deleteBook(String title) throws ServiceException {
		DAOFactory daoFactory = DAOFactory.getInstance();
		DAOBook bookDAO = daoFactory.getBookDAO();
		try {
			ArrayList<Book> listOfBook = bookDAO.searchBook(title);
			for (Book element : listOfBook) {
				bookDAO.deleteBook(element);
			}
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
	@Override
	public void addNewBook(String title, TypeBook typeOfBook) throws ServiceException {
		DAOFactory daoFactory = DAOFactory.getInstance();
		DAOBook bookDAO = daoFactory.getBookDAO();
		
		try {
			bookDAO.addBook(title,typeOfBook);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
	@Override
	public String searchBook(String name) throws ServiceException{
		DAOFactory daoFactory = DAOFactory.getInstance();
		DAOBook bookDAO = daoFactory.getBookDAO();
		ArrayList<Book> listOfbook;
		String response="";
		try {
			listOfbook=bookDAO.searchBook(name);
			if (listOfbook.size()==0) {
				throw new ServiceException("Такой книги нет:(");
			} else {
				for(Book book:listOfbook) {
					response+=book.getName()+" "+book.getType().toString()+"\r\n";
				}
			}
		} catch (DAOException e) {
			throw new ServiceException(e);
		}		
		return response;
	}
	@Override
	public String getListOfBook() throws ServiceException{
		DAOFactory daoFactory = DAOFactory.getInstance();
		DAOBook bookDAO = daoFactory.getBookDAO();
		ArrayList<Book> listOfbook;
		String response="";
		try {
			listOfbook=bookDAO.getListOfBook();
			for(Book element:listOfbook)
			{
				response+=element.getName()+" "+ element.getType()+"\r\n";
			}
		} catch (DAOException e) {
			throw new ServiceException(e);
		}		
		return response;
	}
	@Override
	public void addDescriptionOfBook(String title, String description)throws ServiceException{
		DAOFactory daoFactory = DAOFactory.getInstance();
		DAOBook bookDAO = daoFactory.getBookDAO();
		try {
			ArrayList<Book> book=bookDAO.searchBook(title);
			bookDAO.addDescriptionOfBook(book, description);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}



}
