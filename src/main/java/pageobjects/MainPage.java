package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import resources.Base;

public class MainPage extends Base {

	public WebDriver driver;
	private By more = By.xpath("//span[contains(text(),'More')]");
	private By careerPage = By.xpath("//a[@href='https://useinsider.com/careers/']");
	
	

	public MainPage(WebDriver driver) {
		this.driver = driver;
	}

	public WebElement careerPage() {
		driver.findElement(more).click();
		return driver.findElement(careerPage);
	}
	public String getTitle()
	{
		return driver.getTitle();
	}

}
