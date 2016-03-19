package tests;

import core.BaseTest;
import org.testng.annotations.Test;
import ui.GitHubHomeScreen;
import ui.GitHubLoginScreen;
import ui.GitHubRepoScreen;

/**
 * Created by ars on 3/18/16.
 */
public class GitHubForkTest extends BaseTest {

    private final String repoToExtractLink = "https://github.com/Matt-B/cucumber-js-selenium-webdriver-example";//"https://github.com/githubtraining/hellogitworld";
    private final String username = "aremskrypnyk";
    private final String password = "youtoo4$";

    @Test
    public void forkGitHubTest(){
        GitHubHomeScreen gitHubHomeScreen = new GitHubHomeScreen();
        gitHubHomeScreen
                .clickOnSignInButton();

        GitHubLoginScreen gitHubLoginScreen = new GitHubLoginScreen();
        gitHubLoginScreen
                .login(username, password);

        GitHubRepoScreen gitHubRepoScreen = new GitHubRepoScreen(repoToExtractLink);
        gitHubRepoScreen
                .clickOnForkButton();

        gitHubRepoScreen
                .exploreRepoStructure();
    }
}
