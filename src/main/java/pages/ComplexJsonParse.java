package pages;

import files.Payload;
import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		JsonPath js = new JsonPath(Payload.CoursePrice());
		
		// Print No of courses returned by API
		int count = js.getInt("courses.size()");
		System.out.println("Size of the courses: "+ count);
		
		//Print Purchase Amount
		int totalAmount = js.getInt("dashboard.purchaseAmount");
		System.out.println("Total amount of Course: "+ totalAmount);
		
		//Print Title of the first course
		String titleFirstCourse = js.get("courses[0].title");
		System.out.println("First course title: "+ titleFirstCourse);

		// Print All course titles and their respective Prices
		for (int i = 0; i < count; i++) {
			String courseTitles = js.get("courses[" + i + "].title");
			System.out.println("Course title: "+courseTitles);
			System.out.println("Course Price: "+js.get("courses[" + i + "].price").toString());
		}
		
		// Print no of copies sold by RPA Course
		System.out.print("Print no of copies sold by RPA Course: ");
		for (int i = 0; i < count; i++) {
			String courseTitles = js.get("courses[" + i + "].title");
			if (courseTitles.equalsIgnoreCase("RPA")) {
				int copies = js.get("courses[" + i + "].copies");
				System.out.print(copies);
				break;
			}
		}
	}
}
