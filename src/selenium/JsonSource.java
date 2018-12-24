package selenium;

import java.io.File;

import org.json.JSONArray;
import org.json.JSONObject;

import util.JSonUtil;

/**
 * @author Sinan
 *
 */
public class JsonSource {

	private static final String JSON_PATH = "ext" + File.separator + "json" + File.separator + "com" + File.separator;
	private static final String PATH = JSON_PATH + File.separator + "example" + File.separator;
	private static final String PATH_LOGIN = JSON_PATH + File.separator + "login" + File.separator;

	private JsonSource() {
	}

	/**
	 * 
	 * @param jsonFileName
	 * @param element
	 * @return
	 */
	private static JSONArray get(String jsonFileName, String element) {
		return (JSONArray) JSonUtil.get(jsonFileName, element);
	}

	/**
	 * 
	 * @return
	 */
	public static JSONObject loginPower() {
		JSONObject obj = (JSONObject) JSonUtil.get(PATH_LOGIN + "login.json", "users");
		return (JSONObject) obj.get("powerUser");
	}

	/**
	 * 
	 * @return
	 */
	public static JSONArray veri() {
		return get(PATH + "veri.json", "veri");
	}

}
