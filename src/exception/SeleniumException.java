package exception;

import static org.junit.Assert.fail;

import org.openqa.selenium.WebDriver;

import util.ScreenshotUtil;

/**
 * 
 * @author Sinan
 *
 */
public class SeleniumException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructs a new exception with {@code null} as its detail message. The
	 * cause is not initialized, and may subsequently be initialized by a call
	 * to {@link #initCause}.
	 */
	public SeleniumException() {
		super();
	}

	/**
	 * Constructs a new exception with the specified detail message. The cause
	 * is not initialized, and may subsequently be initialized by a call to
	 * {@link #initCause}.
	 * 
	 * @param message
	 */
	public SeleniumException(String message) {
		super(message);
	}

	/**
	 * Constructs a new exception with the specified detail message and cause.
	 * <p>
	 * 
	 * @param message
	 * @param cause
	 */
	public SeleniumException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Constructs handle exception.
	 * 
	 * @param message
	 * @param cause
	 * @param driver
	 */
	public SeleniumException(Throwable cause, WebDriver driver) {
		cause.printStackTrace();
		ScreenshotUtil.shot(driver);
		fail(cause.getMessage());
	}
}
