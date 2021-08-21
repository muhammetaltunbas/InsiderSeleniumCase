package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import resources.Base;

public class CareerPage extends Base {

	public  WebDriver driver;
	private By location = By.xpath("//h3[contains(text(),'Our Locations')]");
	private By teams= By.linkText("See all teams");
	private By findJobBtn = By.cssSelector("[class*='py-sm-3 py-2']");
	private By life = By.xpath("//h2[contains(text(),'Life at Insider')]");
	private By careerOpportunities = By.cssSelector("[for='newsletter-input']");
	private By findJobArea = By.id("find-job-widget");

	public CareerPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	public String location()
	{
		return driver.findElement(location).getText();
	}
	public String teams()
	{
		return driver.findElement(teams).getText();
	}
	public String jobs()
	{
		return driver.findElement(findJobBtn).getText();
	}
	public String life()
	{
		return driver.findElement(life).getText();
	}
	public WebElement findJobButton()
	{
		return driver.findElement(findJobBtn);
	}

	public void goCareerOpportunities()
	{
		getJS().executeScript("arguments[0].scrollIntoView();",driver.findElement(findJobArea));//scroll to careerOpportunities
		waitFunctionVisibility(findJobBtn);
		findJobButton().click();
	}
	

}
