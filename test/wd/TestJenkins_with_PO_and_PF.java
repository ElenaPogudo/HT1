package wd;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Arrays;

public class TestJenkins_with_PO_and_PF {
	String base_url = "http://localhost:8080/";
	StringBuffer verificationErrors = new StringBuffer();
	WebDriver driver = null;

	@BeforeClass
	public void beforeClass() throws Exception {
		System.setProperty("webdriver.chrome.driver", "C:/Users/elena/Downloads/chromedriver_win32/chromedriver.exe");
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		capabilities.setCapability("chrome.switches", Arrays.asList("--homepage=about:blank"));
		driver = new ChromeDriver(capabilities);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			Assert.fail(verificationErrorString);
		}
	}


	@Test
	public void sampleTest() throws InterruptedException {
		// 0 действие - открыть Jenkins
		driver.get(base_url);

		TestJenkins page = PageFactory.initElements(driver, TestJenkins.class);


		//Авторизация
		page.submitFilledForm("admin","test");

		//1 действие - нажать manage Jenkins
		page.clickMJ();
		//1 проверка - » на странице появляется элемент dt с текстом «Manage Users» и элемент dd с текстом «Create/delete/modify users that can log in to this Jenkins».
		Assert.assertTrue(page.FirstIsElementsPresentWithRightMessage(),"No elements dd,dt comtains 'Manage Jenkins' and 'Create/delete/modify users that can log in to this Jenkins' text");


		//2 действие - нажать Manage User
		page.clickMU();
		//2 проверка - становится доступна ссылка «Create User»
		Assert.assertTrue(page.SecondIsElementsPresent(),"'Create User' link is not available");


		//3 действие - нажать Create User
		page.clickCU();
		//3 проверка - появляется форма с тремя полями типа text и двумя полями типа password, причём все поля должны быть пустыми.
		Assert.assertTrue(page.TherdIsElementsPresentWithRightMessage(),"No three text type and two password type empty field ");


		//4 действие - заполняем поля нового пользователя и кликаем да
		page.submitFilledForm("someuser","somepassword","somepassword","Some Full Name","some@addr.dom");
		//4 проверка - на странице появляется строка таблицы (элемент tr), в которой есть ячейка (элемент td) с текстом «someuser».
		Assert.assertTrue(page.FourthIsElementsPresentWithRightMessage(),"After creating new user info about this user does not appear on Users Manage page");


		//5 действие - кликаем удалить
		page.clickHL();
		//5 проверка - появляется текст «Are you sure about deleting the user from Jenkins?».
		Assert.assertTrue(page.FivethIsElementsPresentWithRightMessage(),"No confirn deleting user message");


		//6 действие - кликаем да
		page.submitForm();
		//6 проверка - на странице отсутствует строка таблицы (элемент tr), с ячейкой (элемент td) с текстом «someuser». На странице отсутствует ссылка с атрибутом href равным «user/someuser/delete».
		Assert.assertFalse(page.SixthIsElementsPresentWithRightMessage(),"element tr or/and element td with text 'someuser' or/and link with href='user/someuser/delete' still on page");
		//7 проверка - на странице отсутствует ссылка сатрибутом href=user/admin/delete
		Assert.assertFalse(page.SeventhIsElementsPresentWithRightMessage(),"Linkwith href='user/admin/delete' is on page");
	}
}
