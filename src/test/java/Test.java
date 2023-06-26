
public class Test {
	//square bracket in json is called as array,array is collection of element,
	//first element is treated as 0th index
 public static void main(String[] args) throws AWTException, InterruptedException, IOException {
        
       System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"//chromedriver.exe");
       WebDriver driver = new ChromeDriver();
       driver.manage().window().maximize();
       driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
       driver.get("https://www.google.com");

	}

}
