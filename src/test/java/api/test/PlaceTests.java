package api.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.github.javafaker.Faker;

import api.endpoints.PlaceEndpoints;
import api.payload.Place;
import api.utilities.ExtentReportManager;
import files.Payload;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
//import utils.report.ExtentTestManager;

public class PlaceTests {

	Faker faker;
	Place placePayload;
	Response response;
	String placeId;
	
	@BeforeClass
	public void setup() {
//		placePayload=new Place();
		response = PlaceEndpoints.addPlace(Payload.AddPlace());
	    placeId = new JsonPath(response.asString()).getString("place_id");
	}
	
	@Test(priority = 0)
	public void testAddPlace() {
//	    response=PlaceEndpoints.addPlace(Payload.AddPlace());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		JsonPath js = new JsonPath(response.asString());
		placeId = js.getString("place_id");
	}
	
	@Test(priority=1)
	public void testGetPlace() {
		Response getresponse=PlaceEndpoints.getPlace(placeId);
		getresponse.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	@Test(priority=2)
	public void testUpdatePlace() {
		String newAddress ="50 winter walk, India";
		String key ="qaclick123";
	    response=PlaceEndpoints.updatePlace(Payload.UpdatePlace(placeId, newAddress, key));
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	@Test(priority=3)
	public void testDeletePlace() {
	    response=PlaceEndpoints.deletePlace(Payload.DeletePlace(placeId));
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
	}
}
