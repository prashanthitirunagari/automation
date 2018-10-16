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



public class DataCreation {
	     
	public static WebDriver driver;
	            @Test	
                public static void DataCreation() throws IOException, InterruptedException {
                                  
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

                            /*  statuscolumn = row.createCell(5);
                              statuscolumn.setCellType(statuscolumn.CELL_TYPE_STRING);*/

                              

                             
                                                          
                                   
                                 /*   header_tab("Keys");
                                    Thread.sleep(5000);
                                    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                                    button_click("//*[@id='keyPagesForm:keyPageLeftPanel:newButton']");
                                    
                                    driver.manage().timeouts().implicitlyWait(300, TimeUnit.SECONDS);
                                    
                                    enter_data_in_textbox("//*[@id='keyPagesForm:keypageLabelId']/input", KeypageName.getStringCellValue().trim());
                                    driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
                                    enter_data_in_textbox("//*[@id='keyPagesForm:keypageNameId']", KeypageName.getStringCellValue().trim());
                                    driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
                                    
                                    //Add Iems keyPages
                                    Add_Items_to_keyPages(VLA.getStringCellValue().trim());
                                    //Add_Items_to_keyPages("229290");
                                    button_click("//*[@id='keyPagesForm:saveBtn']");
                                    Thread.sleep(10000);
                                    
                                    
                                  //Create taskhpane
                                    //header_tab("KeyPage Sets");
                                  
                                    button_click("//*[@id='keyPagesForm:keypagesSubMenu:keyPageSubMenu2']/ul/li[2]/a/span");
                                    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                                    button_click("//*[@id='keypagesetForm:leftPanel:newButton']");
                                    
                                    driver.manage().timeouts().implicitlyWait(300, TimeUnit.SECONDS);
                                    
                                    enter_data_in_textbox("//*[@id='keypagesetForm:centerPanel:nameId']",KeypageSetName.getStringCellValue().trim());
                                    driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
                                    //enter_data_in_textbox("//*[@id='keyPagesForm:keypageNameId']", "keyPag_new");
                                    //driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
                                    
                                    //Add taskpane to keyPages
                                    Add_taskpane_to_keyPages(KeypageName.getStringCellValue().trim());*/
                                    
                                    
                                    //Add taskpane to user
                                    //Add_keyPageset_to_user(KeypageSetName.getStringCellValue().trim(), UserName.getStringCellValue().trim(), VLA.getStringCellValue().trim());
                              Add_keyPageset_to_user("InternalConf_KeypgSet", UserName.getStringCellValue().trim());
                                   
                                  //*[@id="userConfigForm:userLeftPanel:leftDataTable_data"]/tr/td
                                    //((JavascriptExecutor)driver).executeScript("document.body.style.zoom='50%';");
                                    
                                    // 
                    }}

                
                
                public static void Add_Items_to_keyPages(String vla) throws InterruptedException{
                String search_text="";
                enter_data_in_textbox("//*[@id='keyPagesForm:keypageRightPanel:search_text']", vla);
                button_click("//*[@id='keyPagesForm:keypageRightPanel:search_go']");
                
              //*[@id="keyPagesForm:keypageRightPanel:vlaDataTable:0:vlaLabel"]
                int k,i=0,cnt=0;
                Thread.sleep(2000);
                for(int j=0;j<2;j++){
                search_text=get_text_of_element("//*[@id='keyPagesForm:keypageRightPanel:vlaDataTable:"+j+":vlaLabel']");
                System.out.println(search_text);
                if (search_text.equals(vla)){
                	k=j+1;
                	select_unselect_checkbox_radio("//*[@id='keyPagesForm:keypageRightPanel:vlaDataTable_data']/tr["+k+"]/td[1]/div/div[2]");
                	for(i=0;i<3;i++){
                    	button_click("//*[@id='keyPagesForm:keyPageTable:"+cnt+":resourceContactId:imageActionLinkId']");
                    	//enter_data_in_textbox("//*[@id='keyPagesForm:keyPageTable:"+i+":appearanceId_input']", "");
                    enter_data_in_textbox("//*[@id='keyPagesForm:keyPageTable:"+cnt+":appearanceId_input']", Integer.toString(i+1));
                    cnt++;
}}
                	
                	 if (search_text.equals("22"+vla)){
                     	k=j+1;
                     	select_unselect_checkbox_radio("//*[@id='keyPagesForm:keypageRightPanel:vlaDataTable_data']/tr["+k+"]/td[1]/div/div[2]");
                     	for(i=0;i<10;i++){
                         	button_click("//*[@id='keyPagesForm:keyPageTable:"+cnt+":resourceContactId:imageActionLinkId']");
                         	//enter_data_in_textbox("//*[@id='keyPagesForm:keyPageTable:"+i+":appearanceId_input']", "");
                         enter_data_in_textbox("//*[@id='keyPagesForm:keyPageTable:"+cnt+":appearanceId_input']", Integer.toString(i+1));
                         cnt++;
                     	}}
                 }
                
}

                public static void Add_taskpane_to_keyPages(String keyPage_name) throws InterruptedException{
                    String search_text="";
                    enter_data_in_textbox("//*[@id='keypagesetForm:rightPanel:search_text']", keyPage_name);
                    button_click("//*[@id='keypagesetForm:rightPanel:search_go']");
                    
                  //*[@id="keyPagesForm:keypageRightPanel:vlaDataTable:0:vlaLabel"]
                    int k,i=0;
                    Thread.sleep(2000);
                    for(int j=0;j<50;j++){
                    search_text=get_text_of_element("//*[@id='keypagesetForm:rightPanel:rightDataTable:"+j+":nameTextId']");
                    System.out.println(search_text);
                    if (search_text.equals(keyPage_name)){
                    	k=j+1;
                    	select_unselect_checkbox_radio("//*[@id='keypagesetForm:rightPanel:rightDataTable_data']/tr["+k+"]/td[1]/div/div[2]");
                    	//for(i=0;i<3;i++){
                        	button_click("//*[@id='keypagesetForm:rightPanel:assignIcon']");
                        	//enter_data_in_textbox("//*[@id='keyPagesForm:keyPageTable:"+i+":appearanceId_input']", "");
                        
    //}
                        	button_click("//*[@id='keypagesetForm:saveBtn']");
                        	Thread.sleep(1000);
                        	break;
                    } }
                    
    }

                //public static void Add_keyPageset_to_user(String keyPageset_name, String user_name, String vla_num) throws InterruptedException{
                public static void Add_keyPageset_to_user(String keyPageset_name, String user_name) throws InterruptedException{
                	
                	Thread.sleep(1000);
                	header_tab("Users");

                    driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
                    button_click("//*[@id='userForm:userSubMenu']/ul/li[3]/a/span");
                    driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
                   
                    
                    enter_data_in_textbox("//*[@id='userConfigForm:userLeftPanel:searchText']",user_name);
                    Thread.sleep(3000);
                    driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
                    button_click("//*[@id='userConfigForm:userLeftPanel:searchGoButton']/span");
                    
                 
                 /*   enter_data_in_textbox("//*[@id='userConfigForm:userConfigTabView:0:userProfileNameText']",user_name);
                    Thread.sleep(3000);
                    driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
                    button_click("//*[@id='userConfigForm:saveBtn']/span");
                    Thread.sleep(3000);*/
                    
                    
                    String search_text="";
                    
                    
                    int k,i=0;
                    Thread.sleep(2000);
                    for(int j=1;j<=20;j++){
                    search_text=get_text_of_element("//*[@id='userConfigForm:userLeftPanel:leftDataTable_data']/tr["+j+"]/td");
                    System.out.println(search_text);
                    if (search_text.equals(user_name)){
                    	
                    	select_unselect_checkbox_radio("//*[@id='userConfigForm:userLeftPanel:leftDataTable_data']/tr["+j+"]/td");
                    	//for(i=0;i<3;i++){
                    	Thread.sleep(5000);
                    	//*[@id="userConfigForm:userConfigTabView:1:settingsTabView"]/div[1]/ul/li[3]/a
                    	
                    	////////////////////////////////////////////////
                    	///Add default VLA
                    	/*button_click("//*[@id='userConfigForm:userConfigTabView:1:settingsTabView:generalParamToggler']");
                    	Thread.sleep(2000);
                    	enter_data_in_textbox("//*[@id='userConfigForm:userConfigTabView:1:settingsTabView:defaultDDIMenu_input']",vla_num);
                    	Thread.sleep(2000);
                    	button_click("//*[@id='userConfigForm:saveBtn']/span");
                    	Thread.sleep(5000);
                    	//break;
                    	
                    	////////////////////////////////////////
                    	 * 
                    	 */
                        	Thread.sleep(5000);
                        	button_click("//*[@id='userConfigForm:userConfigTabView:1:settingsTabView']/div[1]/ul/li[3]");
                    	//header_tab("Button Keypages");
                        	Thread.sleep(5000);
                        	//*[@id="userConfigForm:userConfigTabView:1:settingsTabView:buttonKeypageRightPanel:search_text"]
                        	enter_data_in_textbox("//*[@id='userConfigForm:userConfigTabView:1:settingsTabView:buttonKeypageRightPanel:search_text']",keyPageset_name);
                            driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
                            button_click("//*[@id='userConfigForm:userConfigTabView:1:settingsTabView:buttonKeypageRightPanel:search_go']");
                            Thread.sleep(1000);
                            //for(int m=0;m<=1;m++){
                            	///////////////////////////////////////////////
                            for(int m=0;m<=50;m++){
                                search_text=get_text_of_element("//*[@id='userConfigForm:userConfigTabView:1:settingsTabView:buttonKeypageRightPanel:keypageSetDataTable:"+m+":nameTextId']");
                              //*[@id="userConfigForm:userConfigTabView:1:settingsTabView:buttonKeypageRightPanel:keypageSetDataTable:1:nameTextId"]
                              //*[@id="userConfigForm:userConfigTabView:1:settingsTabView:buttonKeypageRightPanel:keypageSetDataTable:3:nameTextId"]
                                System.out.println(search_text);
                                if (search_text.contains(keyPageset_name)){
                                	int n=m+1;
                                	
                                	select_unselect_checkbox_radio("//*[@id='userConfigForm:userConfigTabView:1:settingsTabView:buttonKeypageRightPanel:keypageSetDataTable_data']/tr["+n+"]/td[1]/div/div[2]");
                                	//for(i=0;i<3;i++){
                                    	button_click("//*[@id='userConfigForm:userConfigTabView:1:settingsTabView:assignIcon3']");
                                    	//*[@id="userConfigForm:userConfigTabView:1:settingsTabView:buttonKeypageRightPanel:search_text"]
                                    	Thread.sleep(2000);
                                    	button_click("//*[@id='userConfigForm:saveBtn']");
                                    	//*[@id="userConfigForm:saveBtn"]/span
                                        
                                    	
                                    	
                                    	//enter_data_in_textbox("//*[@id='keyPagesForm:keyPageTable:"+i+":appearanceId_input']", "");
                                    
                //}
                                    	
                                    	Thread.sleep(1000);
                                    	/////////////////////////////////
                                    	break; 
                                } }
                        	
                        	//enter_data_in_textbox("//*[@id='keyPagesForm:keyPageTable:"+i+":appearanceId_input']", "");
                        
    //}
                    //    	button_click("//*[@id='keypagesetForm:saveBtn']");
                        	Thread.sleep(1000);
                        	break;
                  
                    	
                    } }
                    
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

                                

                	DataCreation();

                }

 

}

 

 

 
