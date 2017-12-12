package bsuir.library.service;

import bsuir.library.service.impl.LibraryServiceImpl;
import bsuir.library.service.impl.UserServiceImpl;

public class ServiceFactory {
	private static final ServiceFactory instance = new ServiceFactory();
	
	private final LibraryService liraryService = new LibraryServiceImpl();
	private final UserService userService = new UserServiceImpl();
	
	
	public static ServiceFactory getInstance(){
		return instance;
	}
	
	public LibraryService getLiraryService(){
		return liraryService;
	}
	
	public UserService getUserService(){
		return userService;
	}
	

}
