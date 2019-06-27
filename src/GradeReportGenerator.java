import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

public class GradeReportGenerator {
    public static void main(String[] args) {

        // setting up the chrome driver
        System.setProperty("webdriver.chrome.driver", "/home/hizkias/Documents/program setups/chromedriver");
        ChromeOptions chromeOptions = new ChromeOptions();

        WebDriver driver = new ChromeDriver(chromeOptions);

        //Open the portal site
        driver.get("https://portal.aait.edu.et/");
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);

        // Entering  credentials and login
        driver.findElement(By.id("UserName")).sendKeys("ATR/9733/07");
        driver.findElement(By.id("Password")).sendKeys("myPassword");
        driver.findElement(By.className("btn-success")).click();

        driver.navigate().to("https://portal.aait.edu.et/Grade/GradeReport");

        driver.get(driver.getCurrentUrl());

        FileWriter fileWriter;

        try {
            fileWriter = new FileWriter("GradeReport.html");

            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.write("<html>" +
                    "<body>" +
                            driver.findElement(By.tagName("table")).getText()+
                    "</body>" +
                    "</html>");
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
