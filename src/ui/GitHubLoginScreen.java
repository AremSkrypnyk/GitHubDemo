package ui;

import core.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Created by ars on 3/18/16.
 */
public class GitHubLoginScreen {

    private final By emailFieldLocator = By.id("login_field");
    private final By passwordFieldLocator = By.id("password");
    private final By signInButtonLocator = By.xpath("//input[@type='submit']");

    public void login(String username, String password){
        WebElement emailField = Driver.findElement(emailFieldLocator);
        emailField.sendKeys(username);

        WebElement passwordField = Driver.findElement(passwordFieldLocator);
        passwordField.sendKeys(password);

        WebElement signInButton = Driver.findElement(signInButtonLocator);
        signInButton.click();
    }
}
