package com.albertoteloko.utils.selector.exceptions;

import com.albertoteloko.utils.CheckUtils;

/**
 * This exception is throw when try to perform a invalid text extraction.
 * 
 */
public class IllegalTextExtractionException extends RuntimeException {


	/**
	 * The serial version UID
	 */
	private static final long serialVersionUID = 7163052104949688387L;

	/**
	 * Constructs an IllegalTextExtractionException with no detail message.
	 */
	public IllegalTextExtractionException() {
		super();
	}

	/**
	 * Constructs an IllegalTextSelectionException with the specified detail message.
	 * 
	 * @param message the detail message.
	 */
	public IllegalTextExtractionException(String message) {
		super(message);
		CheckUtils.checkString("message", message);
	}

	/**
	 * Constructs a new exception with the specified cause and a detail message of (cause==null ? null :
	 * cause.toString()) (which typically contains the class and detail message of cause). This constructor is
	 * useful for exceptions that are little more than wrappers for other throwables (for example,
	 * PrivilegedActionException).
	 * 
	 * @param cause the cause (which is saved for later retrieval by the Throwable.getCause() method). (A null
	 *            value is permitted, and indicates that the cause is nonexistent or unknown.)
	 */
	public IllegalTextExtractionException(Throwable cause) {
		super(cause);
		CheckUtils.checkNull("cause", cause);
	}

	/**
	 * Constructs a new exception with the specified detail message and cause. Note that the detail message
	 * associated with cause is not automatically incorporated in this exception's detail message.
	 * 
	 * @param message the detail message (which is saved for later retrieval by the Throwable.getMessage()
	 *            method).
	 * @param causethe cause (which is saved for later retrieval by the Throwable.getCause() method). (A null
	 *            value is permitted, and indicates that the cause is nonexistent or unknown.)
	 */
	public IllegalTextExtractionException(String message, Throwable cause) {
		super(message, cause);
		CheckUtils.checkNull("cause", cause);
		CheckUtils.checkString("message", message);
	}

}
