package selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.ContextConfiguration;

import exception.SeleniumException;
import util.ScreenshotUtil;
import util.SeleniumUtil;
import util.ZipUtil;
 

/**
 * 
 * @author Sinan
 *
 */
//@ContextConfiguration(locations = { "/beans.xml" })
//@ActiveProfiles("dev")
public abstract class Base {

	protected static final String PROPERTY_FILE = "config.properties";
	protected static final String DRIVER_CHROME = "chrome";
	protected static final String DRIVER_PHANTOMJS = "phantomJS";

	protected WebDriver driver;
	protected SeleniumUtil sel;
	protected String baseUrl;

	/**
	 * Constructor for set thread name
	 */
	public Base() {
		Thread.currentThread().setName(this.getClass().getName());
	}

	/**
	 * run
	 * 
	 * @throws SeleniumException
	 * 
	 */
	@Test
	public abstract void run() throws SeleniumException;

	/**
	 * Set Webdriver {@link DriverAdaptor}, url, util
	 */
	@Before
	public void initDriver() {

		String webDriver = PropertyReader.readProperty("driver");

		if (DRIVER_CHROME.equalsIgnoreCase(webDriver)) {
			this.driver = DriverAdaptor.chromeDriver();
		} else if (DRIVER_PHANTOMJS.equalsIgnoreCase(webDriver)) {
			this.driver = DriverAdaptor.phantomJsDriver();
		} else {
			throw new IllegalArgumentException("WebDriver define is not found...");
		}

		this.sel = new SeleniumUtil(driver, Integer.parseInt(PropertyReader.readProperty("timeout")));

		this.baseUrl = PropertyReader.readProperty("url");
	}

	/**
	 * tear down
	 */
	@After
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
		ZipUtil.archive(ScreenshotUtil.IMAGES_PATH);
	}

}