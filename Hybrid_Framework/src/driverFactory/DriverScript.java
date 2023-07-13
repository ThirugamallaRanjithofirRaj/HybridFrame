package driverFactory;

import org.testng.Reporter;
import org.testng.annotations.Test;

import commonFunctions.FunctionLibrary;
import utilities.ExcelFileUtil;

public class DriverScript extends FunctionLibrary {
String inputpath = "./FileInput/DataEngine.xlsx";
String outputpath = "./FileOutput/HybridResults.xlsx";
String TCSheet = "TestCases";
String TSSheet = "TestSteps";

@Test
public void startTest()throws Throwable
{
	boolean res = false;
	String tcres ="";
	ExcelFileUtil xl = new ExcelFileUtil(inputpath);
	//row count in TCSheet,TSSheet
	int TCCount = xl.rowCount(TCSheet);
	int TSSheet =xl.rowCount(TCSheet);
	Reporter.log(TCCount+"    "+TSSheet,true);
	//itreate all rows in TCSheet
	for(int i=1;i<=TCCount;i++)
	{
		//read module status cell
		String Module_Status = xl.getCellData(TCSheet, i, 2);
		if(Module_Status.equalsIgnoreCase("Y"))
		{
			//read tcid cell
			String tcid = xl.getCellData(TCSheet, i, 0);
			//iterate all rows in TSSheet
			for(int j=1;j<=TCCount;j++)
			{
				String tsid =xl.getCellData(TCSheet, j, 0);
				if(tcid.equalsIgnoreCase(tsid))
				{
					String keyword = xl.getCellData(TCSheet, j, 3);
					if(keyword.equalsIgnoreCase("adminLogin"))
					{
						String para1 = xl.getCellData(TCSheet, j, 5);
						String para2 = xl.getCellData(TCSheet, j, 6);
						res =FunctionLibrary.pbLogin(para1, para2);
						}
					else if(keyword.equalsIgnoreCase("branchcreation"))
					{
						String para1 = xl.getCellData(TCSheet, j, 5);
						String para2 = xl.getCellData(TCSheet, j, 6);
						String para3 = xl.getCellData(TCSheet, j, 7);
						String para4 = xl.getCellData(TCSheet, j, 8);
						String para5 = xl.getCellData(TCSheet, j, 9);
						String para6 = xl.getCellData(TCSheet, j, 10);
						String para7 = xl.getCellData(TCSheet, j, 11);
						String para8 = xl.getCellData(TCSheet, j, 12);
						String para9 = xl.getCellData(TCSheet, j, 13);
						FunctionLibrary.pbBranches();
						res =FunctionLibrary.pbBranchecrate(para1, para2, para3, para4, para6, para6, para7, para8, para9);
					}
					else if(keyword.equalsIgnoreCase("brancheUpdate"))
					{
						res =FunctionLibrary.pbLogout();
					}
					String tsres ="";
					if(res)
					{
						tsres="pass";
						xl.setCellData(TCSheet, j, 4, tsres, outputpath);
					}
					else
					{
						tsres="Fail";
						xl.setCellData(TCSheet, j, 4, tsres, outputpath);
					}
					tcres=tsres;
				}
				
				}
			xl.setCellData(TCSheet,i, 3, tcres, outputpath);
		}
		else
		{
			//write as blocked into TCSheet which test case flag to N
			xl.setCellData(TCSheet, i, 3, "Blocked", outputpath);
			}
	}
}

}
