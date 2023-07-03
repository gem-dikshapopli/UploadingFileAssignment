package org.example;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.time.Duration;

/*
1. Open Facebook.
2. Click on Username -> Type Username in capitals (Use Keyboard Actions)--> Copy Username using Keyboard Actions.
3. Open Context Menu and Paste username in the last name .
4.Type Mobile Number.
5. Click on password --> Right Click and select suggested passwords --> Choose a suggested password.

For Downloading Uploading a File Go to https://smallseotools.com/plagiarism-checker/
Click on Upload File and Upload a document
*/


public class Main {
    public static void main(String[] args)  {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        WebDriver driver=new ChromeDriver(options);
        driver.get("https://www.facebook.com/signup/");

        //---To maximize the window
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        Actions actions=new Actions(driver);
        //---To Type First name
       WebElement firstName= driver.findElement(Locators.firstName);
                actions.
                moveToElement(firstName).
                click().
                keyDown(Keys.SHIFT).
                sendKeys("diksha").
                keyUp(Keys.SHIFT).
                build().
                perform();

       //---Actions class to copy the First name
       actions.keyDown(Keys.CONTROL).sendKeys("A").keyUp(Keys.CONTROL).keyDown(Keys.CONTROL).sendKeys("C").keyUp(Keys.CONTROL).build().perform();

        //---To right click to find the context menu we use contextClick
       actions.contextClick(driver.findElement(Locators.surName)).build().perform();
        try {

            //----Robot class to select Paste option from contextmenu
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_DOWN);
            robot.keyRelease(KeyEvent.VK_DOWN);
            Thread.sleep(1000);
            robot.keyPress(KeyEvent.VK_DOWN);
            robot.keyRelease(KeyEvent.VK_DOWN);
            Thread.sleep(1000);
            robot.keyPress(KeyEvent.VK_DOWN);
            robot.keyRelease(KeyEvent.VK_DOWN);
            Thread.sleep(1000);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);

            //----To enter the mobile number
            driver.findElement(Locators.mobile).sendKeys("1234567890");
            /*
//      This will only work if we're logged in the chrome!
                    actions.moveToElement(driver.findElement(By.name("reg_passwd__"))).contextClick().perform();
                    robot.keyPress(KeyEvent.VK_DOWN);
                    robot.keyRelease(KeyEvent.VK_DOWN);
                    robot.keyPress(KeyEvent.VK_ENTER);
                    robot.keyRelease(KeyEvent.VK_ENTER);

                    robot.keyPress(KeyEvent.VK_DOWN);
                    robot.keyRelease(KeyEvent.VK_DOWN);
                    robot.keyPress(KeyEvent.VK_ENTER);
                    robot.keyRelease(KeyEvent.VK_ENTER);
*/

            //----Swich to Plagiarism-checker
            driver.navigate().to("https://smallseotools.com/plagiarism-checker/");
            driver.switchTo().window(driver.getWindowHandle());
            //----Scroll to upload button
            JavascriptExecutor javascriptExecutor= (JavascriptExecutor) driver;
            javascriptExecutor.executeScript("window.scrollBy(0,700)","");


            try{
                Thread.sleep(3000);
                WebElement uploadButton = driver.findElement(Locators.upload);
                //---To upload the file
                actions.moveToElement(uploadButton);
                uploadButton.sendKeys("C:\\Users\\Diksha.Popli\\Downloads\\16197-notice.pdf");
                Thread.sleep(3000);
            }catch (Exception e){
                e.printStackTrace();

            }
        }catch (Exception e){
            e.printStackTrace();
        }

        driver.close();

    }
}