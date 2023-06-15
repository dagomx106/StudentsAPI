package api.utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class Dataproviders {
	
	@DataProvider(name="Data")
	public String[][] getAllData() throws IOException
	{
		String path=System.getProperty("user.dir")+"//testData//studentData.xlsx";
		XLUtility xl=new XLUtility(path);
		
		int rownum=xl.getRowCount("Hoja1");
		int colcount=xl.getCellCount("Hoja1",1);
		
		String apidata[][]=new String[rownum][colcount];
		
		for(int i=1;i<=rownum;i++)
		{
			for(int j=0;j<colcount;j++)
			{
				apidata[i-1][j]=xl.getCellData("Hoja1", i, j);
			}
		}
		return apidata;
	}
	

	@DataProvider(name="StudentId")
	public Integer[] getStudentId() throws IOException
	{
		String path=System.getProperty("user.dir")+"//testData//studentData.xlsx";
		XLUtility xl=new XLUtility(path);
		
		Integer rownum=xl.getRowCount("Hoja1");
		
		Integer apidata[]=new Integer[rownum];
		for(int i=1;i<=rownum;i++)
		{
			Integer num=Integer.parseInt(xl.getCellData("Hoja1", i, 0));  
			apidata[i-1]=num;
		}

		return apidata;

	}
}