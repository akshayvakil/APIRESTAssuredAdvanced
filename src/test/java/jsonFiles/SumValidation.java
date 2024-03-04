package jsonFiles;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;

public class SumValidation {
	
	
	@Test
	
	public void validateSumOfAllCourses()
	{
		JsonPath jsn= new JsonPath(Payload.CoursePrice());
		int numberofCourse=jsn.getInt("courses.size()");
		int purchanseAmount=jsn.getInt("dashboard.purchaseAmount");
		
		System.out.println(numberofCourse);
		
		int TotalSum=0;
		for (int i=0; i<numberofCourse;i++)
		{

			int CourseCopies=jsn.getInt("courses["+i+"].copies");
			int price=jsn.getInt("courses["+i+"].price");
			int amount=CourseCopies*price;
			System.out.println(amount);
			TotalSum=TotalSum+amount;
			
			
			}
		System.out.println("Total Amount -->"+TotalSum);
		System.out.println("Total amount from json"+purchanseAmount);
		Assert.assertEquals(purchanseAmount, TotalSum);
		}
		
	}

