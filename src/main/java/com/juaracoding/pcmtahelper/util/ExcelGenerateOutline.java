package com.juaracoding.pcmtahelper.util;//package com.juaracoding.pcmautomation.util;
//
//import org.apache.poi.ss.usermodel.Row;
//
//import java.util.Iterator;
//
//public class ExcelGenerateOutline {
//
//	public ExcelGenerateOutline(String excelPath,String sheetName) {
//		printExcel(excelPath,sheetName);
//	}
//
//	public void printExcel(String excelPath, String sheetName) {
//		StringBuilder sBuild = new StringBuilder();
//		ExcelReader excelReader = new ExcelReader(excelPath, sheetName);
//		Iterator<Row> rX = excelReader.getIter();
//		Object obj = null;
//		int intRows = excelReader.getRowCount();
//		int intCol  = excelReader.getColCount();
//		String[][] dDriven = new String[intRows][intCol];
//		int a=0;/** adjustment statement untuk melooping baris */
//		while(rX.hasNext())
//		{
//			/** ini jangan dihapus karena buat pointer mencegah biar gak keluar line */
//			Row rows = rX.next();
//			for(int j=0;j<intCol;j++)
//			{
//				obj = excelReader.getCellData(a,j);
//				dDriven[a][j] = obj==null?"null":obj.toString();
//
//				dDriven[a][j] = excelReader.getCellData(a,j).toString();
//			}
//			a++;
//		}
//
//		String generateOutline = "";
//		for(int i=0;i<dDriven.length;i++)
//		{
//			for(int j=0;j<intCol;j++)
//			{
//				sBuild.setLength(0);
//				if(j==0) {
//					generateOutline = sBuild.append(generateOutline).append("|").append(dDriven[i][j]).
//							append("|").toString();
//				}else {
//					generateOutline = sBuild.append(generateOutline).append(dDriven[i][j]).
//							append("|").toString();
//				}
//			}
//			sBuild.setLength(0);
//			generateOutline = sBuild.append(generateOutline).append("\n").toString();
//		}
//		System.out.println(generateOutline);
//	}
//}