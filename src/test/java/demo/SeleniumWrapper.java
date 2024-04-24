package demo;

import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SeleniumWrapper {

      public static boolean EnterText(WebElement input, String sendKeys){

        try{
          
            input.click();
            input.clear();
            input.sendKeys(sendKeys);
            Thread.sleep(1000);

            return true;
        } catch(Exception e){

            e.getMessage();
            return false;

        }    
    }

    public static boolean Click(WebElement clickOperation, WebDriver driver){

        if(clickOperation.isDisplayed()){

            try{

                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].scrollIntoView(true)", clickOperation);

                clickOperation.click();
                Thread.sleep(2000);
                return true;
                
            }catch(Exception e){

                e.getMessage();
                return false;

            }
        }
        return false;

    }
    

}
