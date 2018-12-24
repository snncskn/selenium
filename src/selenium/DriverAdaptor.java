package selenium;

import java.io.File;
import java.util.logging.Level;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author Sinan
 *
 */
public class DriverAdaptor {

	private static final Logger LOGGER = LoggerFactory.getLogger(DriverAdaptor.class);

	private DriverAdaptor() {
	}

	/**
	 * 
	 * @return phantomJsDriver
	 */
	public static WebDriver phantomJsDriver() {

		LOGGER.info("PhantomJsDriver is starting...");

		DesiredCapabilities caps = initDesired();
		caps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,
				"ext" + File.separator + "browser" + File.separator + "phantomjs.exe");
		WebDriver driver = new PhantomJSDriver(caps);
		windowsConf(driver);
		deleteCookies(driver);
		externalLogConf(caps);

		LOGGER.info("PhantomJsDriver test configuration completed.");

		return driver;
	}

	/**
	 * 
	 * @return chromeDriver
	 */
	public static WebDriver chromeDriver() {

		LOGGER.info("ChromeDriver is starting...");

		DesiredCapabilities caps = initDesired();
		caps.setCapability(ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY,
				"ext" + File.separator + "browser" + File.separator + "chrome.exe");

		WebDriver driver = new ChromeDriver(caps);
		windowsConf(driver);
		deleteCookies(driver);
		externalLogConf(caps);

		LOGGER.info("ChromeDriver test configuration completed.");

		return driver;
	}

	private static void externalLogConf(DesiredCapabilities caps) {
		LoggingPreferences logPrefs = new LoggingPreferences();
		logPrefs.enable(LogType.BROWSER, Level.ALL);
		caps.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
	}

	private static void windowsConf(WebDriver driver) {
		LOGGER.info("Window size is setting...");
		driver.manage().window().maximize();
	}

	private static void deleteCookies(WebDriver driver) {
		LOGGER.info("Cookies are deleting...");
		driver.manage().deleteAllCookies();
	}

	private static DesiredCapabilities initDesired() {
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setJavascriptEnabled(true);
		return caps;
	}
}
