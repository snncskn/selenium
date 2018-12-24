package util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Sinan
 */

public class ScreenshotUtil {

	private static final Logger LOGGER = LoggerFactory.getLogger(ScreenshotUtil.class);
	public static final String IMAGES_PATH = "ext" + File.separator + "zipfiles" + File.separator;
	private static final String IMAGES_NAME = "screenshot_";

	/**
	 * Constructor
	 */
	private ScreenshotUtil() {
	}

	/**
	 * 
	 * @param driver
	 * @return
	 */
	public static File shot(WebDriver driver) {
		return shot(driver, true);
	}

	/**
	 * 
	 * @param driver
	 * @param saveFile
	 * @return
	 */
	public static File shot(WebDriver driver, boolean saveFile) {
		File tmpFile = ((TakesScreenshot) new Augmenter().augment(driver)).getScreenshotAs(OutputType.FILE);
		if (saveFile) {
			try {
				LOGGER.info("Screenshot is taking for exception...");
				FileUtils.copyFile(tmpFile, new File(IMAGES_PATH + File.separator + IMAGES_NAME
						+ new SimpleDateFormat("yyyyMMdd_hhmmss").format(new Date()) + ".png"));
			} catch (IOException e) {
				LOGGER.error(e.getLocalizedMessage(), e);
			}
		}
		return tmpFile;
	}

}
