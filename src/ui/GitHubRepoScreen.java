package ui;

import core.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created by ars on 3/18/16.
 */
public class GitHubRepoScreen {

    private final By forkButtonLocator = By.xpath("//button[contains(@title, 'Fork')]");
    private final By folderRowLocator = By.xpath("//tr[@class='js-navigation-item']//a[contains(@class, 'directory')]");
    private final By returnButtonLocator = By.xpath("//a[@title='Go to parent directory']");

    public GitHubRepoScreen(String directRepoLink){
        Driver.get().get(directRepoLink);
    }

    public void clickOnForkButton(){
        WebElement forkButton = Driver.findElement(forkButtonLocator);
        forkButton.click();
    }

    public void exploreRepoStructure(){
        String root = "/";
        exploreFolderStructure(root);
    }

    private void exploreFolderStructure(String root) {
        List<WebElement> folders = getFolders();
        for (int i = 0; i < folders.size(); i++) {
            WebElement folder = getFolders().get(i);
            String folderName = folder.getText();
            folder.click();
            exploreFolderStructure(root + folderName + "/");
        }
        System.out.println(root);
        getClickOnReturnButton(root);
    }

    private void getClickOnReturnButton(String rootFolder) {
        if (!Driver.findAll(returnButtonLocator).isEmpty()) {
            String folderName = rootFolder.substring(
                    rootFolder.substring(0, rootFolder.lastIndexOf("/")).lastIndexOf("/") + 1,
                    rootFolder.lastIndexOf("/"));
            WebElement returnButton = Driver.findElement(returnButtonLocator);
            returnButton.click();
            int timeout = 0;
            do {
                Driver.pause(500);
                timeout = timeout + 500;
            }
            while (!isFolderPresent(folderName) && timeout < 10000);
        }
    }

    private boolean isAnyFolderPresent() {
        return Driver
                .findAll(folderRowLocator)
                .stream()
                .anyMatch(webElement -> !webElement.getText().contains("."));
    }

    private boolean isFolderPresent(String folderName) {
        return Driver
                .findAll(folderRowLocator)
                .stream()
                .anyMatch(webElement -> webElement.getText().contains(folderName));
    }

    private List<WebElement> getFolders(){
        Driver.pause(1000);
        List<WebElement> list = Driver
                .findAll(folderRowLocator);
        list
                .removeIf(webElement -> webElement.getText().contains("."));

        return list;
    }
}
