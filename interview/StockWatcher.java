import java.io.Console;
import java.util.Timer;
import java.util.TimerTask;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
/*import org.openqa.selenium.WebElement; */
/*import org.openqa.selenium.htmlunit.HtmlUnitDriver; */
import org.openqa.selenium.firefox.FirefoxDriver;
public class StockWatcher extends TimerTask {
   private final WebDriver driver;
   public StockWatcher(WebDriver driver) {
      this.driver = driver;
   }

   public void run() {
      try {
         driver.get("https://urldefense.proofpoint.com/v1/url?u=http://finance.yahoo.com/&k=oIvRg1%2BdGAgOoM1BIlLLqw%3D%3D%0A&r=nGLl6%2FTJLhSkLyFDrls6G00JOJcPzdVF%2FjPkoXIuWi4%3D%0A&m=3CrMWQdkJ8v1lKVhLZ0VVT2Nzrm%2Fz%2BtMCpr0V3S1wek%3D%0A&s=291a925e5a2660de61e8c57d053899f49d30f75be14d972e1cff2e52cd71eb02");
         Thread.sleep(5000);
         driver.findElement(By.id("mnp-search_box")).sendKeys("VMW");
         Thread.sleep(500);
         driver.findElement(By.id("yucs-sprop_button")).click();
         Thread.sleep(5000);
         // driver.findElement(By.id("yucs-sprop_button")).wait();
         // get stock value from yahoo
         String yahooVal = driver.findElement(By.id("yfs_l84_vmw")).getText();
         // convert string value to a number
         double yahooPrice = Double.parseDouble(yahooVal);
         // google site
         driver.get("https://urldefense.proofpoint.com/v1/url?u=http://google.com/finance&k=oIvRg1%2BdGAgOoM1BIlLLqw%3D%3D%0A&r=nGLl6%2FTJLhSkLyFDrls6G00JOJcPzdVF%2FjPkoXIuWi4%3D%0A&m=3CrMWQdkJ8v1lKVhLZ0VVT2Nzrm%2Fz%2BtMCpr0V3S1wek%3D%0A&s=cdc12a431f559e1ed47d080b80aa9bd43224c487628b1223b70a915c04d4ee6d");
         Thread.sleep(5000);
         driver.findElement(By.id("gbqfq")).sendKeys("VMW");
         Thread.sleep(500);
         driver.findElement(By.id("gbqfb")).click();
         Thread.sleep(5000);
         // driver.findElement(By.id(" yucs-sprop_button")).wait();
         // get stock value from google
         String googleVal = driver.findElement(By.id("ref_718288_l")).getText();
         // convert string value to a number
         double googlePrice = Double.parseDouble(googleVal);
         if (googlePrice == yahooPrice) {
            System.out.println("They have the same Value of " + googleVal);
         }
         else {
            System.out.println("The values are different: Google=" + googleVal + ", Yahoo="
                  + yahooVal);
         }
      }
      catch (Exception e) {
      }
      // driver.findElement(By.id("password_text")).sendKeys("#bestclientever");
      /*
       * driver.findElement(By.cssSelector("#login-button > div")).click();
       * 
       * driver.findElement(By.id("schemaid-1")).click();
       * 
       * driver.findElement(By.linkText("Create New Campaign")).click();
       * 
       * driver.findElement(By.linkText("New Foldameer")).click();
       * 
       * driver.findElement(By.xpath("(//a[contains(text(),'Create New Campaign')])[2]")).click();
       * 
       * driver.findElement(By.id("save-file")).click();
       * 
       * driver.findElement(By.id("schemaid-1")).click();
       * 
       * driver.findElement(By.cssSelector("div.close-footer > button")).click();
       */
   }

   public static void main(String[] args) {
      StockWatcher watcher = new StockWatcher(new FirefoxDriver());
      try {
         watcher.run();
         Timer timer = new Timer();
         timer.schedule(watcher, 60 * 5 * 1000);
      }
      catch (Exception e) {
      }

   }
}