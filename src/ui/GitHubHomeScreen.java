package ui;

import core.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Created by ars on 3/18/16.
 */
public class GitHubHomeScreen {

    private final By signInButtonLocator = By.xpath("//a[text()='Sign in']");

    public GitHubLoginScreen clickOnSignInButton(){
        WebElement signInButton = Driver.findElement(signInButtonLocator);
        signInButton.click();
        return new GitHubLoginScreen();
    }

}
