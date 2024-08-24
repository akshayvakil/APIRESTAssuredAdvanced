package readExcelForRESTAPI;

import java.io.IOException;
import java.util.ArrayList;

public class testSample {

	public static void main(String[] args) throws IOException {
//Create Object of class as below
DataDrivenExcelwithArrayWithMethod d= new DataDrivenExcelwithArrayWithMethod();
// call method from object
 ArrayList<String> fetchedDatafromExcel=  d.getDatafromExcel("Purchase");
 
 System.out.println( fetchedDatafromExcel.get(0));
 System.out.println( fetchedDatafromExcel.get(1));
 System.out.println( fetchedDatafromExcel.get(2));
 System.out.println( fetchedDatafromExcel.get(3));
 

	}

}
