package api.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.PlaceEndpoints2;
import api.payload.Place;
import files.Payload;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
//import utils.report.ExtentTestManager;

public class PlaceTests2 {

	Faker faker;
	Place placePayload;
	Response response;
	String placeId;
	
	@BeforeClass
	public void setup() {
//		placePayload=new Place();
		response = PlaceEndpoints2.addPlace(Payload.AddPlace());
	    placeId = new JsonPath(response.asString()).getString("place_id");
	}
	
	@Test(priority = 0)
	public void testAddPlace() {
//	    response=PlaceEndpoints2.addPlace(Payload.AddPlace());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		JsonPath js = new JsonPath(response.asString());
		placeId = js.getString("place_id");
	}
	
	@Test(priority=1)
	public void testGetPlace() {
		Response getresponse=PlaceEndpoints2.getPlace(placeId);
		getresponse.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	@Test(priority=2)
	public void testUpdatePlace() {
		String newAddress ="50 winter walk, India";
		String key ="qaclick123";
	    response=PlaceEndpoints2.updatePlace(Payload.UpdatePlace(placeId, newAddress, key));
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	@Test(priority=3)
	public void testDeletePlace() {
	    response=PlaceEndpoints2.deletePlace(Payload.DeletePlace(placeId));
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
	}
}
