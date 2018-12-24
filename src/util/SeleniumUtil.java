package util;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import exception.SeleniumException;

/**
 * 
 * @author Sinan
 *
 */
public class SeleniumUtil {

	private static final Log LOGGER = LogFactory.getLog(SeleniumUtil.class);

	private WebDriverWait wait;
	private WebDriver driver;

	/**
	 * 
	 * @param webDriver
	 * @param timeOutInSeconds
	 */
	public SeleniumUtil(WebDriver webDriver, long timeOutInSeconds) {
		this.driver = webDriver;
		this.wait = new WebDriverWait(driver, timeOutInSeconds);
	}

	/**
	 * Select element to be clickable
	 * 
	 * @param element
	 * @param value
	 * @throws SeleniumException
	 */
	public void selectable(By element, Object value) throws SeleniumException {
		loggerProcessing(element);
		Select tmpSelect = new Select(wait.until(ExpectedConditions.elementToBeClickable(element)));
		tmpSelect.getOptions().clear();
		if (value != null) {
			tmpSelect.selectByVisibleText(value.toString());
		} else {
			LOGGER.warn(Thread.currentThread().getName() + ">>> value is null and selected by index 0");
			tmpSelect.selectByIndex(0);
		}
		loggerSucces(element);
	}

	/**
	 * Element to be clickable
	 * 
	 * @param element
	 * @throws SeleniumException
	 */
	public void clickable(By element) throws SeleniumException {
		loggerProcessing(element);
		WebElement tmpElement = wait.until(ExpectedConditions.elementToBeClickable(element));
		if (tmpElement.isDisplayed()) {
			tmpElement.click();
		} else {
			loggerNotDisplayed(element);
		}
		loggerSucces(element);

	}

	/**
	 * 
	 * @param element
	 * @param value
	 * @throws SeleniumException
	 */
	public void sendKeys(By element, Object value) throws SeleniumException {
		loggerProcessing(element);
		WebElement tmpElement = wait.until(ExpectedConditions.elementToBeClickable(element));
		if (tmpElement.isDisplayed() && value != null) {
			tmpElement.clear();
			tmpElement.sendKeys(value.toString());
		} else {
			loggerNotDisplayed(element);
		}
		loggerSucces(element);
	}

	/**
	 * Browser logs write
	 */
	public void writeLogs() {
		LogEntries logEntries = driver.manage().logs().get(LogType.BROWSER);
		for (LogEntry entry : logEntries) {
			LOGGER.info(new Date(entry.getTimestamp()) + " " + entry.getLevel() + " " + entry.getMessage());
		}
	}

	private static void loggerSucces(By element) {
		LOGGER.info(Thread.currentThread().getName() + " > " + element + " OK.");
	}

	private static void loggerNotDisplayed(By element, Exception e) {
		LOGGER.error(Thread.currentThread().getName() + " >> " + element + " not displayed.");
	}

	private static void loggerNotDisplayed(By element) {
		loggerNotDisplayed(element, null);
	}

	private static void loggerProcessing(By element) {
		LOGGER.info(Thread.currentThread().getName() + " >>> " + element + " processing. ");
	}
}
