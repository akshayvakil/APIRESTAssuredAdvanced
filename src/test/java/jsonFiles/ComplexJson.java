package jsonFiles;

import io.restassured.path.json.JsonPath;

public class ComplexJson {
	
	public static void main(String args[])
	{
		JsonPath jsn= new JsonPath(Payload.CoursePrice());
		int numberofCourse=jsn.getInt("courses.size()");
		
		System.out.println(numberofCourse);
		int totalpurchaseamount = jsn.getInt("dashboard.purchaseAmount");
		System.out.println(totalpurchaseamount);
		
		String firstCourseTitle= jsn.getString("courses[0].title");
		System.out.println(firstCourseTitle);
		System.out.println("========Printing all course titles====================");
		for (int i=0; i<numberofCourse;i++)
		{
			String CourseTitle= jsn.getString("courses["+i+"].title");
			System.out.println(CourseTitle);
		}
		System.out.println("Printing number of copies attribute from json for RPA");
		
		for (int i=0; i<numberofCourse;i++)
		{
			String CourseTitle= jsn.getString("courses["+i+"].title");
			if(CourseTitle.equalsIgnoreCase("RPA"))
			{
				int CourseCopies=jsn.getInt("courses["+i+"].copies");
			System.out.println("RPA Copies sold are -->"+CourseCopies);
			
			}
			break;
			
		}
	}
	
	

}
