import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.Scanner;

public class SeleniumTest {

    By crossBtn = By.xpath("//div[@class='reg_layer_close']");
    By letsbeginBtn = By.xpath("//div[@type='button']");
    By motherTongueDrpDwn = By.id("s2id_frm-mothertongue");

    By emailIdTxtBox = By.id("layer_email");
    By passwordTxtBox = By.id("layer_password1");
    By creatorDrpDwn = By.xpath("//div[@id='s2id_layer_postedby']//span[@class='select2-chosen'][normalize-space()='Select']");
    By sonDD = By.xpath("//div[@class='select2-result-label'][normalize-space()='Son']");

    By nextBtn =By.id("btnSubmit");
    By communityDrpDwn =By.xpath("(//span[@class='select2-chosen'][normalize-space()='Gujarati'])[2]");

    static String path = System.getProperty("user.dir");

    static String drivePath = path + "\\ShaadiAssessmentTask2\\src\\main\\resources\\drivers\\chromedriver.exe";
    static String dataFilePath = path+ "\\ShaadiAssessmentTask2\\src\\main\\resources\\TestData.csv";

    @Test
    public void validateMotherTongue(WebDriver driver) {
        driver.findElement(crossBtn).click();
        String actualLanguage = driver.findElement(motherTongueDrpDwn).getText();
        Assert.assertEquals("Gujarati",actualLanguage);
    }

    @Test
    public void validateCommunityName(WebDriver driver) throws InterruptedException {

        driver.findElement(letsbeginBtn).click();
        Thread.sleep(10000);
        driver.findElement(emailIdTxtBox).sendKeys("Chetan362381@gmail.com");
        driver.findElement(passwordTxtBox).sendKeys("Password12345");

        driver.findElement(creatorDrpDwn).click();
        driver.findElement(sonDD).click();

        driver.findElement(nextBtn).click();
        String actualCommunity = driver.findElement(communityDrpDwn).getText();
        Assert.assertEquals("Gujarati",actualCommunity);
    }

    public static void main(String[] args) throws InterruptedException, FileNotFoundException {

        System.out.println(path);
        System.setProperty("webdriver.chrome.driver", drivePath);
        WebDriver driver=new ChromeDriver();
        driver.manage().window().maximize();

        Scanner scan = new Scanner(new File(dataFilePath));
        scan.useDelimiter(",");
        while (scan.hasNext()) {

            driver.get(scan.next());
            Thread.sleep(2000);

            SeleniumTest obj = new SeleniumTest();
            obj.validateMotherTongue(driver);
            obj.validateCommunityName(driver);
        }
        scan.close();
        driver.close();

    }

}


