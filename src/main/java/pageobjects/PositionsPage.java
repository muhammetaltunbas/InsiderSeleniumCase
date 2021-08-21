package pageobjects;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import resources.Base;

public class PositionsPage extends Base {

	public WebDriver driver;
	private By filterLoc = By.id("select2-filter-by-location-container");
	private By filterDept = By.id("select2-filter-by-department-container");
	private By selectLoc = By.id("filter-by-location");
	private By selectDept = By.id("filter-by-department");
	private By jobList = By.cssSelector("[class*='istanbul-turkey full-time']");
	private By jobTitle = By.cssSelector("[class*='position-title']");
	private By jobDept = By.cssSelector("[class*='position-department']");
	private By jobLoc = By.cssSelector("[class*='position-location']");
	private By applyBtn = By.linkText("Apply Now");
	private By applyJob= By.cssSelector("[class*='template-btn-submit']");
	private By jobListTitle = By.cssSelector("[class*='justify-content-lg-between']");
	private By formBtn = By.cssSelector("[class*='template-btn-submit']");

	//Position 4
	String actualTitle = "Insider. - Software Quality Assurance Tester";
	String actualJobDesc = "A Software Quality Assurance Tester in Insider day in and day out:";
	String actualReq = "We want you to join us while we are taking a step into the future if you:";

	public PositionsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public int jobCount() {
		List<WebElement> jobCount = driver.findElements(jobList);
		return jobCount.size();

	}

	public WebElement applyForJob() {
		return driver.findElement(applyJob);
	}
	public WebElement formBtn()
	{
		return driver.findElement(formBtn);
	}

	public void selectLocation() {
		driver.findElement(filterLoc).click();
		Select s = new Select(driver.findElement(selectLoc));
		s.selectByVisibleText("Istanbul, Turkey");
	}

	public void selectDepartment() {
		driver.findElement(filterDept).click();
		Select s = new Select(driver.findElement(selectDept));
		s.selectByVisibleText("Quality Assurance");
	}

	public boolean checkJobsOnPage() {
		//Thread.sleep(1000);
		//waitFunctionClickable(jobTitle);
		waitFunctionVisibility(jobTitle);
		List<WebElement> title = driver.findElements(jobTitle);
		for (int i = 0; i < jobCount(); i++) {
			if (!driver.getPageSource().contains(title.get(i).getText()))
				return false;
		}
		return true;
	}

	public boolean checkPosition(String position) {
		waitFunctionVisibility(jobTitle);
		List<WebElement> jTitle = driver.findElements(jobTitle);
		for (int i = 0; i < jobCount(); i++) {
			if (!jTitle.get(i).getText().contains(position))
				return false;
		}
		return true;
	}

	public boolean checkDepartment(String department) {
		waitFunctionVisibility(jobDept);
		List<WebElement> dept = driver.findElements(jobDept);
		for (int i = 0; i < jobCount(); i++) {
			if (!dept.get(i).getText().contains(department))
				return false;
		}
		return true;
	}

	public boolean checkLocation(String location) {
		waitFunctionVisibility(jobLoc);
		List<WebElement> loc = driver.findElements(jobLoc);
		for (int i = 0; i < jobCount(); i++) {
			if (!loc.get(i).getText().contains(location))
				return false;
		}
		return true;
	}

	public boolean goPosition() {
		if (jobCount() < 1)
			return false;
		List<WebElement> loc = driver.findElements(jobLoc);

		driver.findElement(jobListTitle).click();
		
		waitFunctionVisibility(jobLoc);

		Actions actions = new Actions(driver);
		actions.moveToElement(loc.get(3)).build().perform();


		driver.findElement(applyBtn).click();
		
		Set<String> ids = driver.getWindowHandles();
		Iterator<String> it = ids.iterator();
		String parentid = it.next();
		String childid = it.next();				
		driver.switchTo().window(childid);

		if (!actualTitle.equalsIgnoreCase(driver.getTitle()))
			return false;
		if (!driver.getPageSource().contains(actualJobDesc))
			return false;
		if (!driver.getPageSource().contains(actualReq))
			return false;
		if (!applyForJob().isDisplayed())
			return false;
		return true;

	}
	public boolean checkFormPage()
	{
		Set<String> ids = driver.getWindowHandles();
		Iterator<String> it = ids.iterator();
		String parentid = it.next();
		String childid = it.next();				
		driver.switchTo().window(childid);
		
		if(!formBtn().isDisplayed())
			return false;
		if(!driver.getPageSource().contains("Submit your application"))
			return false;
		driver.close();
		driver.switchTo().window(parentid);
		
		return true;
	}

}
