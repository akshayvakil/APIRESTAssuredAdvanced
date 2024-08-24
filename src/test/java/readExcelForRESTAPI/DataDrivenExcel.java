package readExcelForRESTAPI;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataDrivenExcel {

	public static void main(String[] args) throws IOException {

		// Identify header column inside which your value is present e.g. TestCase
		// header in excel
		// scan entire colmun [this way if order of Test case column change , code will
		// not break]
		// after your grab exact row finally pull data from row e.g. purchase row

		/**
		 * Step0: Ready any file using File input stream: Step1:Create Object of class
		 * XSSFWorkBook Class ->Get hold level first Step2: Get access to sheet
		 * Step3:Get access to all rows of sheet Step4:Access to specific row [1st row]
		 * from all rows Step5:Get access to all cells of Row Step6: Access the data
		 * from excel into Arrays
		 */

		// TODO Auto-generated method stub
		// Step0: Ready any file using File input stream:
		FileInputStream InputFile = new FileInputStream("src/main/resources/ReadExcel.xlsx");

		// Step1:Create Object of class XSSFWorkBook Class ->Get hold level first

		XSSFWorkbook workbook = new XSSFWorkbook(InputFile);

		// Step2: Get access to desired sheet

		int NoofSheets = workbook.getNumberOfSheets();
		for (int i = 0; i < NoofSheets; i++) {
			if (workbook.getSheetName(i).equals("testData")) {
				XSSFSheet sheet = workbook.getSheetAt(i);

				// Step3:Get access to all rows of sheet; sheet is collection of rows
				Iterator<Row> rows = sheet.iterator();

				// Step4 and 5:Get access to all cells of Row
				// first time with next button control will be on first row
				Row firstrow = rows.next();
				// using above firstrow object iterate cell now, row is collection of cells

				Iterator<Cell> Coulmncellorbox = firstrow.cellIterator();
				// move till last or next next cells
				// Step6: Access the data from excel into Arrays

				int column = 0; // simplified way
				int k = 0;
				while (Coulmncellorbox.hasNext()) {

					Cell value = Coulmncellorbox.next();
					if (value.getStringCellValue().equalsIgnoreCase("Testcase")) {
						column = k;// desired column
						System.out.println(value);
						break;
					}
					k++;
				}
				// Now we have identified column i.e. column index to be iterated
				// Now iterate through all rows
				System.out.println("[above if condition]value of index where TestCase is present"+column);

				while (rows.hasNext()) {
					Row r = rows.next();
					if (r.getCell(column).getStringCellValue().equalsIgnoreCase("Purchase")) {
						// after your grab exact row finally pull data from row e.g. purchase row
						Iterator<Cell> RowCellorBox = r.cellIterator();
						while (RowCellorBox.hasNext()) {
							System.out.println(RowCellorBox.next().getStringCellValue());
						}

					}

				}

			}

		}

	}

}
