package com.test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import pages.ReusableMethods;

import static io.restassured.RestAssured.*;

import org.junit.experimental.theories.DataPoints;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import files.Payload;

public class DynamicJson {
	
	@Test(dataProvider = "BookData") //go and search for data provider having name "BookData"
	public void addBook(String isbn, String asile)
	{
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String response = given().log().all().header("Content-Type", "application/json")
		.body(Payload.addBook(isbn, asile))
		.when()
		.post("Library/Addbook.php")
		.then().log().all().assertThat().statusCode(200)
		.extract().response().asString();
		JsonPath js=ReusableMethods.rawToJson(response);
		String id = js.getString("ID");
		System.out.println(id);
	}
@DataProvider(name="BookData")
public Object [][] getData()
{
	return new Object[][] {{"abc","2525"}, {"xyz","1223"}, {"mno", "8558"}};
}
}
