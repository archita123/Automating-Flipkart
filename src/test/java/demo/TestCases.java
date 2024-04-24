package demo;

import java.util.List;

import org.checkerframework.checker.units.qual.s;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;


public class TestCases {
    ChromeDriver driver;
    public TestCases()
    {
        System.out.println("Constructor: TestCases");
        WebDriverManager.chromedriver().timeout(30).setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }
    
    @Test
    public  void testCase01() throws InterruptedException{
      // Search Washing Machine Sort by popularity and print the count of items with rating less than or equal to 4 stars.
        System.out.println("Start Test case: testCase01");
        driver.get("https://www.flipkart.com/");

       WebElement searchBar = driver.findElement(By.xpath("//input[@class='Pke_EE']"));  
       SeleniumWrapper.EnterText(searchBar, "Washing Machine");

       WebElement clickonEnter =  driver.findElement(By.xpath("//button[@type='submit']"));
       SeleniumWrapper.Click(clickonEnter, driver);

       WebElement popularityFilter = driver.findElement(By.xpath("//div[text()='Popularity']"));
       popularityFilter.click();

        int countOfLowRatings = 0;

        Thread.sleep(2000);
        List<WebElement> ratingsList = driver.findElements(By.xpath("//div[@class='tUxRFH']//div[@class='XQDdHH']"));
      
        for (int i = 0; i < ratingsList.size(); i++) {
            try {
                WebElement ratingElement = ratingsList.get(i);
                // Assuming the text of rating WebElement can be extracted as follows
                
                float rating = Float.parseFloat(ratingElement.getText());
                
                //Checking if rating is <= 4.0
                if (rating <= 4.0) {
                    // if true, increment countOfLowRatings
                    countOfLowRatings++;
                }
            } catch (NumberFormatException e) {
        // Handle if the rating cannot be parsed to float
                e.printStackTrace();
            }  
        }
         System.out.println("ratings less than or equal to 4 = " + countOfLowRatings);

        System.out.println("end Test case: testCase01");
        
    }

    @Test
    public void testCase02() throws InterruptedException{
     //Search iPhone print the Titles and discount % of items with more than 17% discount
        System.out.println("Start Test case: testCase02");

       // driver.get("https://www.flipkart.com/");

        WebElement searchBar = driver.findElement(By.xpath("//input[@type='text']"));
        SeleniumWrapper.Click(searchBar, driver);
        SeleniumWrapper.EnterText(searchBar, "Iphone");
        
        WebElement clickElement = driver.findElement(By.xpath("//button[@type='submit']"));
        SeleniumWrapper.Click(clickElement, driver);

        int count = 0;
        String getTitle = "";

        List<WebElement> discountPercentage = driver.findElements(By.xpath("//div[@class='tUxRFH']//div[@class='UkUFwK']//span"));
            for (int i = 0; i<discountPercentage.size(); i++) {
                try {
                    WebElement discountElement = discountPercentage.get(i);
                   // System.out.println(printTitles.get(i));
                   String discountRate = discountElement.getText();
                   String numericDiscount = discountRate.replaceAll("[^0-9]", "");
                 int discountValue = Integer.parseInt(numericDiscount);
                   // int discount = Integer.parseInt(discountElement.getText());
                    if (discountValue > 17) {
                        
                        WebElement printTitle = driver.findElement(By.xpath("//div[@class='KzDlHZ']"));
                        getTitle = printTitle.getText();
                       System.out.println(getTitle);
                        count++;
                    }
                    
                } catch (NumberFormatException e) {
            // Handle if the rating cannot be parsed to float
                    e.printStackTrace();

                } 
                 
            }
            System.out.println(getTitle);

            System.out.println(count++);

        System.out.println("end Test case: testCase02");
        
    }

    @Test
    public void testCase03() throws InterruptedException{
        //Search Coffee Mug select 4 stars and above, and print the Title and image URL of the 5 items with highest number of reviews
        System.out.println("Start Test case: testCase03");
        //driver.get("https://www.flipkart.com/");
       
        WebElement searchBar = driver.findElement(By.xpath("//input[@type='text']"));
        SeleniumWrapper.Click(searchBar, driver);
        SeleniumWrapper.EnterText(searchBar, "Coffee Mug");

        WebElement clickElement = driver.findElement(By.xpath("//button[@type='submit']"));
        SeleniumWrapper.Click(clickElement, driver);

        WebElement selectRatings = driver.findElement(By.xpath("//div[@class='ewzVkT _3DvUAf'][1]"));
         SeleniumWrapper.Click(selectRatings, driver);

        List<WebElement> Alllinks = driver.findElements(By.xpath("//div[@class='_4WELSP']"));
           
        for(int i=0; i<(Math.min(5, Alllinks.size())); i++){
          
                // Iterating through the array list and getting the URL's
                WebElement element = Alllinks.get(i);
                Thread.sleep(3000);
                 String title = element.findElement(By.xpath("//a[@class='wjcEIp']")).getText();
                String imgUrl = element.findElement(By.tagName("img")).getAttribute("src");
                System.out.println("Image Title - "+ title + "Image URL = " + imgUrl);
               
      }
        System.out.println("end Test case: testCase03");
    }

    public void endTest()
    {
        System.out.println("End Test: TestCases");
        driver.close();
        driver.quit();

    }
}
