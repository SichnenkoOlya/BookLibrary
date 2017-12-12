package bsuir.library.dao;

import bsuir.library.dao.impl.DAOBookImpl;
import bsuir.library.dao.impl.DAOUserImpl;

public class DAOFactory {

    private static final DAOFactory instance = new DAOFactory();
	
	private DAOUser userDAO = new DAOUserImpl();
	private DAOBook bookDAO = new DAOBookImpl();
	
	private DAOFactory(){}
	
	
	public static DAOFactory getInstance(){
		return instance;
	}
	
	
	public DAOUser getUserDAO(){
		return userDAO;
	}
	
	public DAOBook getBookDAO(){
		return bookDAO;
	}
	

	
}
