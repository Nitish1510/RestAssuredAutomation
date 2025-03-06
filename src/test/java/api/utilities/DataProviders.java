package api.utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	@DataProvider(name="Data")
	public String [][] getAllDat() throws IOException{
		//String path = System.getProperty("user.dir")+"\\testData\\user_data.xlsx";
		String path =".\\testData\\user_data.xlsx";
		ExcelUtility xlutil = new ExcelUtility(path);
		
		int totalrows =xlutil.getRowCount("Sheet1");
		int totalcols = xlutil.getCellCount("Sheet1", 1);
		
		String apiData[][] =new String[totalrows][totalcols];
		
		for(int i=1;i<=totalrows;i++) {
			
			
			for(int j=0;j<totalcols;j++) {
				apiData[i-1][j] = xlutil.getCellData("Sheet1", i, j);
			}
		}
		
		return apiData;	
	}
	
	
	@DataProvider(name="UserNames")
	public String [] getUserNames() throws IOException {
		String path =".\\testData\\user_data.xlsx";
		ExcelUtility xlutil = new ExcelUtility(path);
		
		int rowNo = xlutil.getRowCount("Sheet1");
		//int colNo = xlutil.getCellCount("Shhet1", 1);
		
		String apiData [] = new String [rowNo];
		
		for(int r =1;r<=rowNo;r++) {	
				apiData[r-1]=xlutil.getCellData("Sheet1", r, 1);	
		}
		return apiData;	
	}

}
