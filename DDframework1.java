
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class FrameworkDD {

	public static void main(String[] args) throws IOException {
		XSSFWorkbook ExcelWorkBook =null;
		XSSFSheet ExcelWorkSheet;
		//XSSFRow Row;
		//XSSFCell Cell;
		
		//Create an Object of File to open file
		File excelFile =new File("C:\\Users\\user\\Desktop\\Selenium WebDriver\\DataDrivenFrameWork.xlsx");
		FileInputStream inputStream=null;
		//Create an Object of FileInputstream to read data from file
		try {
			 inputStream =new FileInputStream(excelFile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        //Excel-->Workbook-->Sheet-->Row-->Cell
		ExcelWorkBook =new XSSFWorkbook(inputStream);
		//to access workbook sheet
		ExcelWorkSheet = ExcelWorkBook.getSheetAt(0);
		//get total row count
		int totalRows=ExcelWorkSheet.getLastRowNum()+1;
		//get total no. of cells in row
		int totalCells=ExcelWorkSheet.getRow(0).getLastCellNum();
		
		for(int currentRow=1;currentRow<totalRows;currentRow++) {
			//Launch Browser
			WebDriver driver=new ChromeDriver();
			driver.manage().window().maximize();  
			driver.get("https://www.saucedemo.com/v1/");//open URL
			driver.findElement(By.name("user-name")).sendKeys(ExcelWorkSheet.getRow(currentRow).getCell(0).toString());
			driver.findElement(By.name("password")).sendKeys(ExcelWorkSheet.getRow(currentRow).getCell(1).toString());
			driver.findElement(By.id("login-button")).click();
			
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			for(int currentCell=0;currentCell<totalCells;currentCell++) {
				System.out.print(ExcelWorkSheet.getRow(currentRow).getCell(currentCell).toString());
				System.out.print("\t");
			}
			System.out.println();
			driver.quit();
		}
		
		ExcelWorkBook.close();
		
	}

}
