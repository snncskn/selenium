package util;

import java.io.FileReader;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author Sinan
 *
 */
public class JSonUtil {

	private static final Logger LOGGER = LoggerFactory.getLogger(JSonUtil.class);

	private JSonUtil() {
	}

	/**
	 * 
	 * @param object
	 * @param key
	 * @return
	 */
	public static Object get(String object, String key) {
		JSONObject obj = null;
		try {
			obj = new JSONObject(new JSONTokener(new FileReader(object)));
			if (key != null) {
				return obj.get(key);
			} else {
				return obj;
			}
		} catch (Exception e) {
			LOGGER.error("JSonUtil >>>", e);
		}
		return obj;
	}

	/**
	 * 
	 * @param object
	 * @return
	 */
	public static JSONObject get(String object) {
		return (JSONObject) get(object, null);
	}

}
