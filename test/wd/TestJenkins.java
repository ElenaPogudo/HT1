package wd;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


public class TestJenkins {
    private WebDriverWait wait;
    private final WebDriver driver;
    private String NewUserName = new String("someuser");
    private String element_1_0_Message = new String("Manage Users");
    private String element_1_1_Message = new String("Create/delete/modify users that can log in to this Jenkins");
    private String element_5_Message = new String("Are you sure about deleting the user from Jenkins?");

    // Подготовка элементов страницы.

    @FindBy(id = "j_username")
    private WebElement logName;

    @FindBy(name = "j_password")
    private WebElement logPassword;

    @FindBy(id = "yui-gen1-button")
    private WebElement button;

    @FindBy(xpath = "//a[contains(text(),'Manage Jenkins')]")
    private WebElement link_Manage_Jenkins;

    @FindBy(css = "a[title=\"Manage Users\"]")
    private WebElement link_Manage_User;

    @FindBy(xpath = "//a[contains(text(),'Create User')]")
    private WebElement link_Create_User;

    @FindBy(css = "a[href=\"user/someuser/delete\"]")
    private WebElement link_href_property;

    @FindBy(css = "a[href=\"user/admin/delete\"]")
    private WebElement link_href_property1;

    @FindBy(css = "a[title=\"Manage Users\"] > dl > dt")
    private WebElement element_1_0;

    @FindBy(css = "a[title=\"Manage Users\"] > dl > dd")
    private WebElement element_1_1;

    @FindBy(xpath = "//a[contains(text(),'Create User')]")
    private WebElement element_2;

    @FindBy(xpath = "//table[@id='people']/tbody/tr[3]")
    private WebElement element_4_0;

    @FindBy(xpath = "//table[@id='people']/tbody/tr[3]/td[2]")
    private WebElement element_4_1;

    @FindBy(name = "delete")
    private WebElement element_5;


    @FindBy(id = "username")
    private WebElement new_name;

    @FindBy(name = "password1")
    private WebElement password1;

    @FindBy(name = "password2")
    private WebElement password2;

    @FindBy(name = "fullname")
    private WebElement fullname;

    @FindBy(name = "email")
    private WebElement email;


    public TestJenkins(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(this.driver, 30);
    }

    // Заполнение имени в авторизации.
    public TestJenkins setName(String name) {
        logName.clear();
        logName.sendKeys(name);
        return this;
    }

    // Заполнение пароля в авторизации.
    public TestJenkins setPassword(String password) {
        logPassword.clear();
        logPassword.sendKeys(password);
        return this;
    }

    // Заполнение всех полей формы авторизации.
    public TestJenkins setFieldsLoginForm(String name, String password) {
        setName(name);
        setPassword(password);
        return this;
    }


    // Отправка данных из формы.
    public TestJenkins submitForm() {
        button.click();
        return this;
    }

    // Обёртка для упрощения отправки данных авторизации.
    public TestJenkins submitFilledForm(String name, String password) {
        //Заполняем форму
        setFieldsLoginForm(name, password);
        //проверяем что все поля заполнены верно
        Assert.assertEquals(getName(), name, "Unable to fill 'Login name' field");
        Assert.assertEquals(getPassword(), password, "Unable to fill 'Password' field");
        //подтверждаем отправку формы
        return submitForm();
    }

    //Имя в новом пользователе
    public TestJenkins setNewName(String name) {
        new_name.clear();
        new_name.sendKeys(name);
        return this;
    }

    //Пароль в новом пользователе
    public TestJenkins setNewPassword1(String name) {
        password1.clear();
        password1.sendKeys(name);
        return this;
    }

    //Подтверждение пороля в новом пользователе
    public TestJenkins setNewPassword2(String name) {
        password2.clear();
        password2.sendKeys(name);
        return this;
    }

    //Полное имя в новом пользователе
    public TestJenkins setNewFullName(String name) {
        fullname.clear();
        fullname.sendKeys(name);
        return this;
    }

    //Емаил в новом пользователе
    public TestJenkins setNewEmail(String name) {
        email.clear();
        email.sendKeys(name);
        return this;
    }

    //Заполнение всей формы в новом пользователе
    public TestJenkins setFieldsNewForm(String name, String password1, String password2, String fullname, String email) {
        setNewName(name);
        setNewPassword1(password1);
        setNewPassword2(password2);
        setNewFullName(fullname);
        setNewEmail(email);
        return this;
    }

    // Обёртка для упрощения отправки данных о новом пользователе.
    public TestJenkins submitFilledForm(String name1, String password1, String password2, String fullname, String email) {
        //Заполняем форму
        setFieldsNewForm(name1, password1, password2, fullname, email);
        //проверяем что все поля заполнены верно
        Assert.assertEquals(getNewName(), name1, "Unable to fill 'New user name' field");
        Assert.assertEquals(getNewPassword1(), password1, "Unable to fill 'Password' field");
        Assert.assertEquals(getNewPassword2(), password2, "Unable to fill 'Confirm Password' field");
        Assert.assertEquals(getNewFullname(), fullname, "Unable to fill 'Full Name' field");
        Assert.assertEquals(getNewEmail(), email, "Unable to fill 'Email' field");
        //подтверждаем отправку формы
        return submitForm();
    }
    // Клик по ссылке Manage Jenkins
    public TestJenkins clickMJ() {
        link_Manage_Jenkins.click();
        return this;
    }

    // Клик по ссылке Manage Users
    public TestJenkins clickMU() {
        link_Manage_User.click();
        return this;
    }

    // // Клик по ссылке Create User
    public TestJenkins clickCU() {
        link_Create_User.click();
        return this;
    }
    // Клик по ссылке с заданным href
    public TestJenkins clickHL() {
        link_href_property.click();
        return this;
    }

    //Все проверки
    public boolean FirstIsElementsPresentWithRightMessage() {
        return (element_1_0.getText().equals(element_1_0_Message) && (element_1_1.getText().equals(element_1_1_Message)));

    }

    public boolean SecondIsElementsPresent() {
        return (element_2 != null);
    }

    public boolean TherdIsElementsPresentWithRightMessage() {
        if ((new_name.getAttribute("type").equals("text")) && (fullname.getAttribute("type").equals("text"))
                && (email.getAttribute("type").equals("text")) && (password1.getAttribute("type").equals("password"))
                && (password2.getAttribute("type").equals("password"))&&(new_name.getText().equals(""))
                &&(password1.getText().equals(""))&&(password2.getText().equals(""))&&(fullname.getText().equals(""))
                &&(email.getText().equals("")))
            return true;
        else
            return false;
    }

    public boolean FourthIsElementsPresentWithRightMessage() {
        return ((element_4_0 != null) && (element_4_1.getText().equals(NewUserName)));
    }

    public boolean FivethIsElementsPresentWithRightMessage() {
        return element_5.getText().equals(element_5_Message);
    }


    public boolean SixthIsElementsPresentWithRightMessage() {
        return ((element_4_0 == null) && (link_href_property == null));
    }

    public boolean SeventhIsElementsPresentWithRightMessage() {
        return (link_href_property1 == null);
    }

    // Получение значения имени в авторизации.
    public String getName() {
        return logName.getAttribute("value");
    }

    // Получение значения пароля в авторизации.
    public String getPassword() {
        return logPassword.getAttribute("value");
    }

    // Получение значения Нового имени.
    public String getNewName() {
        return new_name.getAttribute("value");
    }

    // Получение значения Нового пароля.
    public String getNewPassword1() {
        return password1.getAttribute("value");
    }

    // Получение значения подтверждения Нового пароля.
    public String getNewPassword2() {
        return password2.getAttribute("value");
    }

    // Получение значения Нового полного имени.
    public String getNewFullname() {
        return fullname.getAttribute("value");
    }

    // Получение значения Нового емаила.
    public String getNewEmail() {
        return email.getAttribute("value");
    }
}