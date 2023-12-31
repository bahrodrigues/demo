package testes;

import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
java.util.Random;

import net.bytebuddy.utility.RandomString;
import testes2.WebDriverWait;

public class createcontacts {
    
    WebDriver driver;
    String firstName = "Marcos";
    String lastName = "Rodrigues";
   
    @BeforeSuite
    public void login(){

        System.setProperty("webdriver.chrome,driver","\\Users\\bdasilva\\Documents\\java\\dem\\src\\drivers\\chromedriver");
        driver = new ChromeDriver();
        driver.get("https://dhp000003szikma4-dev-ed.develop.lightning.force.com/lightning/page/home");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

        WebElement username = driver.findElement(By.id("username"));
        username.click();
        username.sendKeys("bdasilva@salesforce1.com");

        WebElement password = driver.findElement(By.id("password"));
        password.click();
        password.sendKeys("Salesforce1");

        WebElement login = driver.findElement(By.id("Login"));
        login.click();
    }
    
  //  @BeforeMethod
 //   public void loginsalesforce(){

        

   //     }


    @Test
    public void create(){
        WebDriver driver = this.driver;

        WebElement button = driver.findElement(By.xpath("//span[text()='Contacts']/parent::a[contains(@href,'lightning/o/Contact/home')]"));   
        ((JavascriptExecutor)driver).executeScript("arguments[0].click();", button);
        
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='New' and @type='button']")));        
        driver.findElement(By.xpath("//button[text()='New' and @type='button']")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='firstName']"))); 
        driver.findElement(By.xpath("//input[@name='firstName']")).sendKeys(firstName);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='lastName']"))); 
        driver.findElement(By.xpath("//input[@name='lastName']")).sendKeys(lastName);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='Phone']"))); 
        driver.findElement(By.xpath("//input[@name='Phone']")).sendKeys("67367289");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='Email']"))); 
        driver.findElement(By.xpath("//input[@name='Email']")).sendKeys("teste2@teste.com");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Save' and @type='button']"))); 
        driver.findElement(By.xpath("//button[text()='Save' and @type='button']")).click();

    }

    @Test
    public void verifyFieldisEmpyt(){

        //  System.setProperty("webdriver.chrome,driver","\\Users\\bdasilva\\Documents\\java\\dem\\src\\drivers\\chromedriver");
        // WebDriver driver = new ChromeDriver();
        // driver.get("https://dhp000003szikma4-dev-ed.develop.lightning.force.com/lightning/page/home");
        // driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        // WebElement username = driver.findElement(By.id("username"));
        // username.click();
        // username.sendKeys("bdasilva@salesforce1.com");

        // WebElement password = driver.findElement(By.id("password"));
        // password.click();
        // password.sendKeys("Salesforce1");

        // WebElement login = driver.findElement(By.id("Login"));
        // login.click();

        // WebElement button = driver.findElement(By.xpath("//span[text()='Contacts']/parent::a[contains(@href,'lightning/o/Contact/home')]"));    
        // ((JavascriptExecutor)driver).executeScript("arguments[0].click();", button);
        
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        // wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@title=\"Marcos Rodrigues\"]")));
        // WebElement search = driver.findElement(By.xpath("//a[@title=\"Marcos Rodrigues\"]"));
        // search.click();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[@title=\"Details\"]")));
        WebElement details = driver.findElement(By.xpath("//li[@title=\"Details\"]"));
        details.click();

        // wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='test-id__inline-edit-trigger slds-shrink-none inline-edit-trigger slds-button slds-button_icon-bare' and @title='Edit Title']")));
        // WebElement buttonTitle = driver.findElement(By.xpath("//button[@class='test-id__inline-edit-trigger slds-shrink-none inline-edit-trigger slds-button slds-button_icon-bare' and @title='Edit Title']"));
        // buttonTitle.click();

        //until here is clicking in Title Button

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='slds-form']//span[text()='Name']/../following-sibling::div//*[@slot='outputField']")));
        WebElement Name = driver.findElement(By.xpath("//div[@class='slds-form']//span[text()='Name']/../following-sibling::div//*[@slot='outputField']"));

        String textInsidetitleBox = Name.getText();

        // Check whether input field is blank
        
        Assert.assertEquals(firstName+" "+lastName, textInsidetitleBox, "The name is not the same");
    }

    @Test
    public void useglobalsearch(){

        //globalsearch
        
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Search...']")));
        WebElement globalSearch = driver.findElement(By.xpath("//button[text()='Search...']"));
        globalSearch.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[contains(@placeholder,'Search') and @class='slds-input' and @type='search' and @maxlength='100']")));
        WebElement globalSearch2 = driver.findElement(By.xpath("//input[contains(@placeholder,'Search') and @class='slds-input' and @type='search' and @maxlength='100']"));
        globalSearch2.sendKeys(firstName+" "+lastName);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Show more results')]")));
        WebElement viewAllResults = driver.findElement(By.xpath("//span[contains(text(),'Show more results')]"));
        viewAllResults.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='slds-col scopesItem_name slds-truncate' and text()='Contacts']")));
        WebElement clickContacts = driver.findElement(By.xpath("//span[@class='slds-col scopesItem_name slds-truncate' and text()='Contacts']"));
        clickContacts.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@title='"+firstName+" "+lastName+"']")));
        WebElement clickContact = driver.findElement(By.xpath("//a[@title='"+firstName+" "+lastName+"']"));
        clickContact.click();



    }

    @Test
    public void deletecontact(){

    //delete the contact

    driver.get("https://dhp000003szikma4-dev-ed.develop.lightning.force.com/lightning/r/Contact/003Hp00002rtOyOIAU/view");

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    
    WebElement clickArrow = driver.findElement(By.xpath("//li[@class='slds-dropdown-trigger slds-dropdown-trigger_click slds-button_last overflow']"));
    wait.until(ExpectedConditions.visibilityOf(clickArrow));
    clickArrow.click();

    WebElement deleteContact = driver.findElement(By.xpath("//span[text()='Delete']"));
    wait.until(ExpectedConditions.visibilityOf(deleteContact));
    deleteContact.click();
    
    WebElement clickDelete = driver.findElement(By.xpath("//span[text()='Delete' and @class=' label bBody']"));
    wait.until(ExpectedConditions.visibilityOf(clickDelete));
    clickDelete.click();
        
    }
    //first and last name should be random value



   

}

