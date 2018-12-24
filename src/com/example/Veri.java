package com.example;

import java.util.Iterator;

import org.json.JSONObject;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.login.Login;

import exception.SeleniumException;
import selenium.JsonSource;
import util.CommonElements;

/**
 * 
 * @author Sinan
 *
 */
public class Veri extends Login {

	private static final Logger LOGGER = LoggerFactory.getLogger(Veri.class);

	@Override
	public void run() throws SeleniumException {

		LOGGER.info("Veri is running... ");

		try {

			Iterator<?> it = JsonSource.veri().iterator();

			while (it.hasNext()) {
				prepareElements((JSONObject) it.next());
			}

		} catch (Exception e) {
			throw new SeleniumException(e, driver);
		}

		LOGGER.info("Veri finished.");

	}

	private void prepareElements(JSONObject obj) throws SeleniumException {

		CommonElements.inject(sel, obj);

		sel.sendKeys(By.name("sendCount"), obj.get("gonderimAdedi"));

		sel.clickable(By.xpath("//div[@id='formDialog']/div/div/div[1]/div"));

		sel.clickable(By.id("btnSave"));

		sel.clickable(By.linkText("Evet"));

		sel.clickable(By.xpath("//div[6]/div[1]"));

		sel.clickable(By.linkText("Tamam"));

		sel.clickable(By.xpath("//div[@class='control-group']/div[8]/div[1]"));

	}

}
