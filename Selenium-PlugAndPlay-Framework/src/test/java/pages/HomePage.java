/**
 * 
 */
package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.BaseClass;

/**
 * @author ACER
 *
 */
public class HomePage extends BaseClass{

	@FindBy(xpath="//input[@type='text' and @class='_2zrpKA']") public WebElement username;
	@FindBy(xpath="//input[@type='password']") public WebElement password;
		
	public HomePage() {
		PageFactory.initElements(driver, this);
		log.debug("HomePage Initialized.");
	}
}
