import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.testng.annotations.Test;

public class sampleJenkins {
    @Test
    public void runtest(){
        WebDriver driver;
        String FS = File.separator;
        String downloadFilepath = System.getProperty("user.dir") + FS + "src" + FS + "test" + FS + "resources" + FS + "downloads";

        System.out.println("Hello Jenkins");
        /*WebDriverManager.chromedriver().clearDriverCache().setup();
        HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
        chromePrefs.put("profile.default_content_settings.popups", 0);
        chromePrefs.put("download.prompt_for_download", "false");
        chromePrefs.put("download.default_directory", downloadFilepath);
        ChromeOptions chromeOptions = getChromeOptions(downloadFilepath);
        System.setProperty("webdriver.chrome.silentOutput","true");

        chromeOptions.setExperimentalOption("prefs", chromePrefs);
        chromeOptions.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        chromeOptions.setCapability(CapabilityType.UNHANDLED_PROMPT_BEHAVIOUR,"accept");
        chromeOptions.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
        driver = new ChromeDriver(chromeOptions);
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();*/

       // System.setProperty("webdriver.chrome.driver",downloadFilepath+"//chromedriver.exe");
       /* ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--disable-notifications");
        chromeOptions.setBinary(downloadFilepath);
        driver = new ChromeDriver(chromeOptions);
      //  WebDriverRunner.setWebDriver(driver);
        driver.get("https://www.google.com");
        System.out.println(driver.getTitle());*/

        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless"); // Run in headless mode (optional)
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");
        options.addArguments("window-size=1366,768");

        // Start ChromeDriver
        driver = new ChromeDriver(options);

        // Open a website to verify if everything is working
        driver.get("https://www.google.com");

        // Print the title of the page
        System.out.println("Title: " + driver.getTitle());

        // Close the browser
        driver.quit();
        System.out.println("quit");


    }

    private static ChromeOptions getChromeOptions(String downloadFilepath) {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--disable-notifications");
        chromeOptions.addArguments("--headless");
        chromeOptions.addArguments("window-size=1366,768"); // New Added
        chromeOptions.setBinary(downloadFilepath);


        chromeOptions.addArguments("--disable-extensions"); // disabling extensions
        chromeOptions.addArguments("--disable-gpu"); // applicable to windows os only
        chromeOptions.addArguments("--disable-impl-side-painting");
        chromeOptions.addArguments("--remote-debugging-port=9222");
        chromeOptions.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
        chromeOptions.addArguments("--no-sandbox"); // Bypass OS security model
        chromeOptions.addArguments("enable-automation");
        chromeOptions.addArguments("--dns-prefetch-disable");
        chromeOptions.setPageLoadStrategy(PageLoadStrategy.NONE);
        // Generate a unique temporary user data directory
        try {
            Path tempDir = Files.createTempDirectory("chrome-user-data");
            chromeOptions.addArguments("--user-data-dir=" + tempDir.toString());
        } catch (IOException e) {
            e.printStackTrace(); // Handle the error if directory creation fails
        }
        return chromeOptions;
    }
}
