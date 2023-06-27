package Common_API_Methods;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
public class Common_Utility_Method {
	
	public static void EvidenceCreator(String FileName, String RequestBody, String ResponseBody,int StatusCode) throws IOException {
		File TextFile = new File("F:\\MSSG\\Rest\\"+FileName+".txt");
		System.out.println("New blank text file of name : " + TextFile.getName());
		
		FileWriter dataWrite = new FileWriter(TextFile);
		
		dataWrite.write("Request Body is : " + RequestBody +"\n\n");
		dataWrite.write("StatusCode is : " + StatusCode+"\n\n");
		dataWrite.write("ResponseBody is : " + ResponseBody +"\n\n");
		
		dataWrite.close();
		System.out.println("Request Body and Response Body written in  :" + TextFile.getName());
	}
   public static ArrayList<String> ReadDataExcel(String SheetName,String TestCaseName) throws IOException
   {
	   //System.out.println("Old ReadDataExcel");
	   ArrayList<String> ArrayData = new ArrayList<String>();
	   //Create the Object of fileinputStream to locate the excel File
	   FileInputStream Fis = new FileInputStream("F:\\MSSG\\SEL\\AB.xlsx");
	   //Open the excel file by creating the object XSSFWorkBook
	   XSSFWorkbook WorkBook = new XSSFWorkbook(Fis);
	   //Open the desired sheet
	   int CountOfSheet = WorkBook.getNumberOfSheets();
	   for(int i=0 ; i<CountOfSheet ; i++) {
		   String Sheetname = WorkBook.getSheetName(i);
		   //Access the Desired Sheet
		   if(Sheetname.equalsIgnoreCase(SheetName))
		   {
			   //use XSSF Sheet to save the sheet into the variable
			  XSSFSheet Sheet = WorkBook.getSheetAt(i);
			  // Create iterator through rows and find out in which column the testcase names are found
			  Iterator<Row> Rows = Sheet.iterator();
			  Row FirstRow = Rows.next();
			  // Create the iterator through the cells of first row to find out whic cell contains testcasename
			  Iterator<Cell> CellsOfFirstRow = FirstRow.cellIterator();
			  int k =0;
			  int TC_column=0;
			  while(CellsOfFirstRow.hasNext())
			  {
				  Cell CellValue = CellsOfFirstRow.next();
				  if(CellValue.getStringCellValue().equalsIgnoreCase("TestCaseName"))
				  {
					  TC_column = k;
					  System.out.println("expected column for testcase name :" +k);
					 break;
				  }
				  k++;
			  }
			  //Verify the row where the desired testcase is found and fetch the entire row
			  while (Rows.hasNext()) {
				  Row DataRow = Rows.next();
				  String TCname = DataRow.getCell(TC_column).getStringCellValue();
				  if (TCname.equalsIgnoreCase(TestCaseName))
				  {
					  Iterator<Cell> Cellvalues = DataRow.cellIterator();
					  while(Cellvalues.hasNext())
					  {
						  String Data = Cellvalues.next().getStringCellValue();
						  ArrayData.add(Data);
					  }
					  break;
				  }
			  }
			  
		   }
	   }
	   return ArrayData;
   }
}
