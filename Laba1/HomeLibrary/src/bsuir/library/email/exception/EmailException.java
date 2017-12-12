package bsuir.library.email.exception;

public class EmailException extends Exception{
	private static final long serialVersionUID = 1L;

	public EmailException(){
	}

	public EmailException(Exception e){
		super(e);
	}
	
	public EmailException(String message){
		super(message);
	}
	
	public EmailException(String message, Exception e){
		super(message, e);
	}
}