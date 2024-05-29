package com.juaracoding.pcmtahelper.util;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

public class ExcelReader {

	/** File excel yang akan dijadikan Object **/
	private XSSFWorkbook wBook ;
	/** Sheet yang akan diproses **/
	private XSSFSheet sheet ;
	/** Variabel untuk menampung data di dalam cell pada file excel*/
	private String values ;

	/** Variabel untuk menampung informasi banyaknya baris di dalam file excel*/
	private int intRowCount;
	/** Variabel untuk menampung informasi banyaknya kolom di dalam file excel */
	private int intColCount;
	/** object array untuk menampung semua data beserta header nya  */
	private String[][] strAllData;
	/** object array untuk menampung semua data tanpa header  */
	private String[][] arrWithoutHeader;
	private int loopRows;
	private FileInputStream excelFile;
	private BufferedInputStream inputBuff;
	private String[] exceptionString = new String[2];

	public ExcelReader(String excelPath, String sheetName) {
		exceptionString[0] = "ExcelReader";
		try {
			/** heavy step */
			excelFile = new FileInputStream(new File(excelPath));
			inputBuff = new BufferedInputStream(excelFile);
			wBook = new XSSFWorkbook(inputBuff);/**/
			sheet = wBook.getSheet(sheetName);
//			System.out.println(1/0);
			getRowCount();/** Memasukkan informasi jumlah baris ke dalam variabel intRowCount*/
			getColCount();/** Memasukkan informasi jumlah kolom ke dalam variabel intColCount*/
			setData();/*SET ALL DATA*/
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		finally {
			try {
				excelFile.close();
				inputBuff.close();
				wBook.close();
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	/**
	 * Mengambil seluruh data ke dalam Object Iterator dan melakukan looping secara manual di function pemanggil nya
	 */
	public Iterator<Row> getIter()
	{
		Iterator<Row> r = sheet.iterator();
		return r;
	}


	public void setData()
	{
		try
		{
			strAllData = new String[intRowCount][intColCount];
			arrWithoutHeader = new String[intRowCount-1][intColCount];/** jumlah barisnya  harus minus 1 karena tidak menyertakan header */
			loopRows =0;
			Iterator<Row> rX = sheet.iterator();

			while(rX.hasNext())
			{
				Row rows = rX.next();
				for(int j=0;j<intColCount;j++)
				{
					/** kondisi untuk memisahkan data , disini adalah data tanpa header */
					if(loopRows!=0)
					{
						/*BECAUSE OF remove a Header so Row for this object must be minus 1 */
						arrWithoutHeader [loopRows-1][j] = getCellData(loopRows,j).toString();
					}
					strAllData[loopRows][j] = getCellData(loopRows,j).toString();
				}
				loopRows++;
			}
		}catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		this.strAllData = strAllData;
		this.arrWithoutHeader = arrWithoutHeader;
	}

	/**
	 * Gunakan method ini Jika ingin mengambil seluruh data sekaligus headernya
	 */
	public String[][] getAllData()
	{
		return strAllData;
	}

	/**
	 * Gunakan method ini Jika hanya ingin mengambil data saja tanpa header
	 */

	public String[][] getDataWithoutHeader()
	{
		return arrWithoutHeader;
	}

	/** Mengambil data yang spesifik berdasarkan baris dan kolom nya */
	public Object getCellData(int rowNum, int colNum)
	{
		if(sheet.getRow(rowNum)==null){
			System.out.println("Rownum ke "+rowNum+" Null !!");
		}
		/** Passing value dari data di cell menjadi String di Java */
		values = new DataFormatter().formatCellValue(sheet.getRow(rowNum).getCell(colNum));
		return values;
	}

	/**
	 * Mengambil informasi jumlah baris data saja
	 */
	public int getRowCount()
	{
		intRowCount = sheet.getPhysicalNumberOfRows();
		return intRowCount;
	}

	/**
	 * Mengambil informasi jumlah kolom saja
	 */
	public int getColCount()
	{
		intColCount = sheet.getRow(0).getPhysicalNumberOfCells();

		return intColCount;
	}
}