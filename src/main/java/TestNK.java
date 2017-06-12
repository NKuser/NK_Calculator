import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * Created by User on 22.05.2017.
 */
public class TestNK {
    private WebDriver driver;
    @BeforeTest
    public void setup(){
        final File file=new File(PropertyLoader.loadProperty("path.webDriver"));
        System.setProperty(PropertyLoader.loadProperty("webDriver"),file.getAbsolutePath());
        driver=new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }
    @Test(priority=1)
    public void openPage(){
        driver.navigate().to("http://juliemr.github.io/protractor-demo/");
        driver.manage().window().maximize();
        assertEquals(driver.getCurrentUrl(),"http://juliemr.github.io/protractor-demo/");

    }

    @Test(priority=2)
    public void enterValues(){
        WebElement firstValue=driver.findElement(By.xpath("//input[@ng-model='first']"));
        firstValue.sendKeys("1");
        System.out.println(firstValue.getAttribute("value"));
        //validate the value in the first field
        assertEquals(firstValue.getAttribute("value"),"1");

        //input second value
        WebElement secondValue=driver.findElement(By.xpath("//input[@ng-model='second']"));
        secondValue.sendKeys("1");
        System.out.println(secondValue.getAttribute("value"));
        //validate the value in the second field
        assertEquals(secondValue.getAttribute("value"),"1");

    }

    @Test(priority=3)
    public void selectOperator() throws InterruptedException {
        WebElement operator = driver.findElement(By.xpath("//select[@ng-model='operator']"));
        List<WebElement> allOptions=operator.findElements(By.xpath("//option"));
        operator.click();
        for(WebElement option:allOptions){
            if(option.getText().contains("%")){
                Select operatorSelect=new Select(operator);
                operatorSelect.selectByVisibleText("%");
                break;
            }
            operator.sendKeys(Keys.DOWN);
            Thread.sleep(500);
        }
    }


    @Test(priority=4)
    public void clickButton() throws InterruptedException {
        WebElement buttonGo=driver.findElement(By.xpath("//button[@ng-click='doAddition()']"));
        buttonGo.click();
        // result value
        WebElement result=driver.findElement(By.xpath("//h2[@class]"));
        Thread.sleep(10000);
        assertEquals(result.getText(),"0");
    }
}

