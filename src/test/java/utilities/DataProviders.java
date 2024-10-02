package utilities;

import java.io.IOException;
import java.util.HashMap;

import org.testng.annotations.DataProvider;

public class DataProviders 
{
	@DataProvider(name="data")
	public String [][] getdata() throws IOException
	{
		String path = ".\\testdata\\Opencart_LoginData.xlsx";
		
		ExcelUtility xls = new ExcelUtility(path);
		int rows= xls.getRowCount("Login");
		int cols = xls.getCellCount("Login", 1);
		
		String data[][] = new String [rows][cols];
		
		for (int i=1;i<=rows;i++)
		{
			for (int j=0;j<cols;j++)
			{
				data[i-1][j] = xls.getCellData("Login", i, j);
			}
		}
		
		return data;
		
	}
	
	
	

	
	
	
}
