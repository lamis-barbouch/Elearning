package tn.esprit.spring.exeptions;

public class WordExistException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public WordExistException(String Message){
		super(Message);
	}

}