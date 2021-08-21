package insider.InsiderDemo;

import java.io.IOException;

import org.junit.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pageobjects.CareerPage;
import pageobjects.MainPage;
import pageobjects.PositionsPage;
import resources.Base;
//Test -23.07.2021
public class TestInsider extends Base {
	public MainPage mp;
	public CareerPage cp;
	public PositionsPage pp;

	@BeforeTest
	public void initialize() throws IOException {
		driver = initializeDriver();
	}

	@Test(priority = 0)
	public void goUseInsider() {

		mp = new MainPage(driver);
		driver.get("https://useinsider.com/");
		Assert.assertEquals(getProp().getProperty("mainPageTitle"), mp.getTitle());
	}

	@Test(priority = 1)
	public void goCareerPage() {
		cp = new CareerPage(driver);
		mp.careerPage().click();
		Assert.assertEquals("Our Locations", cp.location());
		Assert.assertEquals("See all teams", cp.teams());
		Assert.assertEquals("Find your dream job", cp.jobs());
		Assert.assertEquals("Life at Insider", cp.life());

	}

	@Test(priority = 2)
	public void goCareerOpportunities() {
		pp = new PositionsPage(driver);
		cp.goCareerOpportunities();
		pp.selectLocation();
		pp.selectDepartment();
		Assert.assertTrue(pp.checkJobsOnPage());
	}

	@Test(priority = 3)
	public void checkAllJobs() {
		Assert.assertTrue(pp.checkDepartment("Quality Assurance"));
		Assert.assertTrue(pp.checkPosition("Quality Assurance"));
		Assert.assertTrue(pp.checkLocation("Istanbul, Turkey"));
	}

	@Test(priority = 4)
	public void selectAPosition() {
		Assert.assertTrue(pp.goPosition());
	}

	@Test(priority = 5)
	public void applyJob() {
		pp.applyForJob().click();
		Assert.assertTrue(pp.checkFormPage());
	}
	@AfterTest
	public void closeBrowser() {
		driver.close();
	}

}
