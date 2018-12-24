package util;

import org.json.JSONObject;
import org.openqa.selenium.By;

import exception.SeleniumException;

/**
 * 
 * @author Sinan
 * 
 */
public class CommonElements {

	/**
	 * {private Constructor}
	 */
	private CommonElements() {
	}

	/**
	 * common elements
	 * 
	 * @param sel
	 * @param obj
	 * @throws SeleniumException
	 */
	public static void inject(SeleniumUtil sel, JSONObject obj) throws SeleniumException {
		sel.clickable(By.id("dropdownMenu"));
	}

}
