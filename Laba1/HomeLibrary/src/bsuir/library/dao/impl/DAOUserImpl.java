package bsuir.library.dao.impl;

import java.util.ArrayList;

import javax.mail.Address;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import bsuir.library.dao.DAOUser;
import bsuir.library.dao.exception.DAOException;
import bsuir.library.domain.Book;
import bsuir.library.domain.User;
import bsuir.library.email.EmailSender;
import bsuir.library.email.exception.EmailException;
import bsuir.library.serialization.Serialization;
import bsuir.library.serialization.exception.SerializationException;

public class DAOUserImpl implements DAOUser {

	protected static ArrayList<User> listOfUser = new ArrayList<User>();
	protected static String fileDBUser = "DB_FILE_USER.txt";


	@Override
	public User registerUser(String login, String password,String email) throws DAOException {

		try {
			listOfUser = Serialization.deserialize(fileDBUser);
		} catch (SerializationException e) {
			throw new DAOException(e.getLocalizedMessage());
		}

		boolean isInList = false;
		for (User element : listOfUser) {
			if (element.getName().equals(login)) {
				isInList = true;
			}
		}
		if (isInList) {
			throw new DAOException("Такой логин занят!");
		}
		User user = new User();
		user.setName(login);
		user.setEmail(email);
		int hashOfPassword = (login + password).hashCode();
		user.setPassword(hashOfPassword);
		listOfUser.add(user);
		try {
			Serialization.serialize(listOfUser, fileDBUser);
		} catch (SerializationException e) {
			throw new DAOException(e.getLocalizedMessage());
		}
		return user;
	}

	
	public ArrayList<User> ShowListOfUser() throws DAOException {

		try {
			listOfUser = Serialization.deserialize(fileDBUser);
		} catch (SerializationException e) {
			throw new DAOException(e.getLocalizedMessage());
		}
		if (listOfUser.isEmpty()) {
			throw new DAOException("There is no book in the library!");
		}
		return listOfUser;

	}

	@Override
	public User authenticateUser(String login, String password) throws DAOException {
		try {
			listOfUser = Serialization.deserialize(fileDBUser);
		} catch (SerializationException e) {
			throw new DAOException(e.getLocalizedMessage());
		}
		boolean isInList = false;
		User user = new User();
		for (User element : listOfUser) {
			if (element.getName().equals(login) && element.getPassword() == (login + password).hashCode()) {
				isInList = true;
				user = element;
			}
		}
		if (!isInList) {
			throw new DAOException("Некорректные данные!");
		}

		return user;
	}
	@Override
	public void sendBookToAdmin(String location, String login) throws DAOException{
		String adminEmail=getAdminEmail();
		String userEmail=getUserEmail(login);
		
		try {
			EmailSender.sendEmailWithBook(location,userEmail,adminEmail);
		} catch (EmailException e) {
			throw new DAOException(e);
		}
	}
	
	public void makeANewsletter(Book book) throws DAOException {

		ArrayList<User> listOfUser;
		try {
			listOfUser = Serialization.deserialize(fileDBUser);
		} catch (SerializationException e1) {
			throw new DAOException(e1.getLocalizedMessage());
		}
		if (listOfUser.isEmpty()) {
			throw new DAOException("There is no book in the library!");
		}
		Address[] adresses = new Address[listOfUser.size()];
		int i = 0;
		for (User user : listOfUser) {
			if (user.getEmail() != null) {
				try {
					adresses[i] = new InternetAddress(user.getEmail());
				} catch (AddressException e) {
					throw new DAOException(e);
				}
				i++;
			}
		}

		try {
			EmailSender.sendEmail(adresses, book);
		} catch (EmailException e) {
			throw new DAOException(e);
		}
	}
	
	@Override
	public void addAdmin(String login, String password, String email) throws DAOException {
		try {
			listOfUser = Serialization.deserialize(fileDBUser);
		} catch (SerializationException e) {
			throw new DAOException(e.getLocalizedMessage());
		}

		boolean isInList = false;
		for (User element : listOfUser) {
			if (element.getName().equals(login)) {
				isInList = true;
			}
		}
		if (isInList) {
			throw new DAOException("Такой логин занят!");
		}
		User user = new User();
		user.setName(login);
		user.setEmail(email);
		user.setAccessRights(1);
		int hashOfPassword = (login + password).hashCode();
		user.setPassword(hashOfPassword);
		listOfUser.add(user);
		try {
			Serialization.serialize(listOfUser, fileDBUser);
		} catch (SerializationException e) {
			throw new DAOException(e.getLocalizedMessage());
		}

	}
	
	public String getAdminEmail() throws DAOException
	{
		String email=null;
		try {
			listOfUser = Serialization.deserialize(fileDBUser);
		} catch (SerializationException e) {
			throw new DAOException(e.getLocalizedMessage());
		}
		for(User user:listOfUser)
		{
			if (user.getAccessRights()==1)
			{
				email=user.getEmail();
			}
		}
		return email;
		
	}
	
	public String getUserEmail(String login) throws DAOException
	{
		String email=null;
		try {
			listOfUser = Serialization.deserialize(fileDBUser);
		} catch (SerializationException e) {
			throw new DAOException(e.getLocalizedMessage());
		}
		for(User user:listOfUser)
		{
			if (user.getName().equals(login))
			{
				email=user.getEmail();
			}
		}
		return email;
		
	}
}
