package bsuir.library.serialization.exception;

public class SerializationException extends Exception{
	private static final long serialVersionUID = 1L;

	public SerializationException(){
	}

	public SerializationException(Exception e){
		super(e);
	}
	
	public SerializationException(String message){
		super(message);
	}
	
	public SerializationException(String message, Exception e){
		super(message, e);
	}
}