package selenium;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author Sinan
 *
 */
public class PropertyReader {

	private static Properties properties;
	private static final Logger LOGGER = LoggerFactory.getLogger(PropertyReader.class);

	/**
	 * Constructor
	 */
	private PropertyReader() {
	}

	/**
	 * 
	 * @param property
	 * @return
	 */
	public static synchronized String readProperty(String property) {
		String returnValue = "";
		try {
			if (properties == null) {
				loadPropertyFile();
			}
			returnValue = properties.getProperty("app." + property);
		} catch (Exception e) {
			LOGGER.error("Exception:", e);
		}
		return returnValue;
	}

	/**
	 * loadPropertyFile
	 * 
	 * @throws IOException
	 */
	private static void loadPropertyFile() throws IOException {
		properties = new Properties();
		InputStream is = PropertyReader.class.getClassLoader().getResourceAsStream(Base.PROPERTY_FILE);
		if (is != null) {
			properties.load(is);
		} else {
			throw new FileNotFoundException(Base.PROPERTY_FILE + "is not found...");
		}

	}

}
