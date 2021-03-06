import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.junit.Test;
import org.apache.bcel.generic.RETURN;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;



public class Retransmission_Adding_User {
	     
	public static WebDriver driver;
	            	
                public static void Retransmission_Adding_User() throws IOException, InterruptedException {
                                  
                	 String filename = new java.io.File(".").getCanonicalPath();

                     filename = filename + "\\input\\SMData_Creation.xls";

                     

                     FileInputStream fis = new FileInputStream(filename);  

                     HSSFWorkbook workbook = new HSSFWorkbook(fis); 



                    HSSFRow row;

                    HSSFCell KeypageName, KeypageSetName, VLA,UserName, cell4,statuscolumn;

                    

                    int rows;
                    HSSFSheet sheet = workbook.getSheetAt(0);

                    rows = sheet.getPhysicalNumberOfRows();
                  //WebDriver driver = new FirefoxDriver();
                    System.setProperty("webdriver.chrome.driver", "C:\\Users\\nandagp\\SeleniumAutomation\\AdminGUI\\Drivers\\chromedriver.exe");			
                      driver = new ChromeDriver();
                     Thread.sleep(4000);
                    driver.get("http://10.221.4.215:8080/admin-gui");

                                  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

                                  driver.manage().window().maximize();

                                  driver.findElement(By.id("loginForm:userName")).sendKeys("admin");

                                  driver.findElement(By.id("loginForm:password")).sendKeys("admin");

                          driver.findElement(By.id("loginForm:loginCommand")).click();

                                  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                                  driver.findElement(By.xpath("//*[@id='confirmButtons']/a")).click();
                                  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

                    
                    for(int r=1;r<rows;r++)         

                    {           
                              row = sheet.getRow(r);
                             // KeypageName = row.getCell(0);
                            //  KeypageName.setCellType(KeypageName.CELL_TYPE_STRING);
                              KeypageSetName = row.getCell(0);
                              KeypageSetName.setCellType(KeypageSetName.CELL_TYPE_STRING);
                              //VLA = row.getCell(2);
                              //VLA.setCellType(VLA.CELL_TYPE_STRING);
                              UserName = row.getCell(1);
                              UserName.setCellType(UserName.CELL_TYPE_STRING);

                           
							      //Assign Retransmission session to user
								          Add_retransmissin_to_user("VR_HS1to2_SK21to24", UserName.getStringCellValue().trim());
                                   
                                  //*[@id="userConfigForm:userLeftPanel:leftDataTable_data"]/tr/td
                                    //((JavascriptExecutor)driver).executeScript("document.body.style.zoom='50%';");
                                    
                                    // 
                    }}

                
                
                

                //public static void Add_retransmissin_to_user_to_user(String keyPageset_name, String user_name, String vla_num) throws InterruptedException{
                public static void Add_retransmissin_to_user(String retranmission_name, String user_name) throws InterruptedException{
                	

                	Thread.sleep(1000);
                	header_tab("Users");
					Thread.sleep(1000);
                    
                    button_click("//*[@id='userForm:userSubMenu2']/ul/li[2]/a/span");
                    driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
                   Thread.sleep(1000);
                    
                    enter_data_in_textbox("//*[@id='userRetransmissionForm:userLeftPanel:searchText']",user_name);
                    Thread.sleep(1000);
                   
                    button_click("//*[@id='userRetransmissionForm:userLeftPanel:searchGoButton']");
                    
                 
                 
                 
                    
                    
                    String search_text="";
                    
                    
                    int k,i=0;
                    Thread.sleep(1000);
                    for(int j=0;j<20;j++){
                    search_text=get_text_of_element("//*[@id='userRetransmissionForm:userLeftPanel:leftDataTable:"+j+":name']");
                    System.out.println(search_text);
                    if (search_text.equals(user_name)){
                    	
                    	select_unselect_checkbox_radio("//*[@id='userRetransmissionForm:userLeftPanel:leftDataTable:"+j+":name']");
                    	//for(i=0;i<3;i++){
                    	Thread.sleep(000);
                    	//*[@id="userConfigForm:userConfigTabView:1:settingsTabView"]/div[1]/ul/li[3]/a
                     	
                    	select_unselect_checkbox_radio("//*[@id='userRetransmissionForm:centerPanel:asgndTbl:selectColumn']/div/div[2]");
						button_click("//*[@id='userRetransmissionForm:centerPanel:removeIcon']");
						Thread.sleep(1000);
                    	
                    	
                    	//Thread.sleep(2000);
                    	enter_data_in_textbox("//*[@id='userRetransmissionForm:rightPanel:search_text']",retranmission_name);
                    	Thread.sleep(1000);
                    	button_click("//*[@id='userRetransmissionForm:rightPanel:search_go']");
                    	Thread.sleep(1000);
						
						select_unselect_checkbox_radio("//*[@id='userRetransmissionForm:rightPanel:rightDataTable_data']/tr/td[1]/div/div[2]");
						button_click("//*[@id='userRetransmissionForm:rightPanel:assignIcon']");
						Thread.sleep(1000);
						button_click("//*[@id='userRetransmissionForm:saveBtn']");
						break;
                    
                    }}                  
                    	
     
                    
    }
                
                public static String get_text_of_element(String object) throws InterruptedException{
                	 Thread.sleep(2000);
                	 String search_text = driver.findElement(By.xpath(object)).getText();
                   
                   return search_text;
                }
          public static void header_tab(String tab_name){
                   
                    driver.findElement(By.partialLinkText(tab_name)).click();                
                              
          }
          
          public static void  button_click(String object){
        	  
      			driver.findElement(By.xpath(object)).click();
      			driver.manage().timeouts().implicitlyWait(300, TimeUnit.SECONDS);
      			
              }	  
          
          public static void  select_unselect_checkbox_radio(String object) throws InterruptedException{
        	  Thread.sleep(1000);
    			driver.findElement(By.xpath(object)).click();
    			
    			driver.manage().timeouts().implicitlyWait(300, TimeUnit.SECONDS);
    			
            }	 
          
          
          
          public static void enter_data_in_textbox(String object, String input_text) throws InterruptedException{
              //#self.driver.execute_script("document.body.style.zoom='60%'")	
        	  driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        	  Thread.sleep(3000);
        	  driver.findElement(By.xpath(object)).clear();
                  driver.findElement(By.xpath(object)).sendKeys(input_text);
                  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                  //'''self.driver.execute_script("document.body.style.zoom='60%'")
                  
                 // self.driver.execute_script("document.body.style.zoom='100%'")'''
          }
          
          public static void enter_data_in_textbox1(String object, String input_text) throws InterruptedException{
              //#self.driver.execute_script("document.body.style.zoom='60%'")	
        	  driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        	  Thread.sleep(3000);
              
                  driver.findElement(By.xpath(object)).sendKeys(input_text);
                  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                  //'''self.driver.execute_script("document.body.style.zoom='60%'")
                  
                 // self.driver.execute_script("document.body.style.zoom='100%'")'''
          }
          
                public static void main(String[] args) throws IOException, InterruptedException{

                                

                	Retransmission_Adding_User();

                }

 

}

 

 

 
