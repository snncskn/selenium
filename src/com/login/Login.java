package com.login;

import org.json.JSONObject;
import org.junit.Before;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import exception.SeleniumException;
import selenium.Base;
import selenium.JsonSource;

/**
 * 
 * @author Sinan
 *
 */
public abstract class Login extends Base {

	private static final Logger LOGGER = LoggerFactory.getLogger(Login.class);

	/**
	 * Login
	 * 
	 * @throws SeleniumException
	 */
	@Before
	public void login() throws SeleniumException {

		driver.get(baseUrl);

		LOGGER.info(baseUrl + " Login is starting... ");

		JSONObject obj = JsonSource.loginPower();

		sel.sendKeys(By.name("username"), obj.get("userName"));
		sel.sendKeys(By.name("password"), obj.get("password"));
		sel.clickable(By.name("loginButton"));

		LOGGER.info("Login finished. ");

	}

}
