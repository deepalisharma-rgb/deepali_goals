package api.test;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import pages.ReusableMethods;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.Test;

import files.Payload;

/**
 * given() - content type, set cookies, add auth, add param, set headers info  (prerequisites)
 * when() - request type - get, post, put, delete
 * then() - validation - validate status code, extract response, extract headers, extract cookies, response body
 */
public class AddPlace 
{
	
   @Test
   public void addAndUpdatePlace()
   {
    	//RestAssured uses hamcrest matchers to assert and validate response easily.
    	//Add Place 
        RestAssured.baseURI= "https://rahulshettyacademy.com";
        String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
        .body(Payload.AddPlace())
        .when().post("maps/api/place/add/json")
        .then().assertThat().statusCode(200).body("scope", equalTo("APP"))
        .header("Server", "Apache/2.4.52 (Ubuntu)").extract().response().asString();
        System.out.println(response);
        
       //JsonPath class - takes string as an input and convert that into json and help to parse json
	   JsonPath js = new JsonPath(response);
       String placeID = js.getString("place_id");
       System.out.println("Place ID is "+ placeID);
       
        //Update Place with new address - use PUT Http method
        //mentioning header if having body
        String newAddress ="70 winter walk, India";
        
        given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
        .body("{\r\n"
        		+ "\"place_id\":\""+placeID+"\",\r\n"
        		+ "\"address\":\""+newAddress+"\",\r\n"
        		+ "\"key\":\"qaclick123\"\r\n"
        		+ "}")
        .when().put("maps/api/place/update/json")
        //.body(Payload.UpdatePlace()).when().put("maps/api/place/update/json")
        .then().log().all().assertThat().statusCode(200).body("msg", equalTo("Address successfully updated"));
        
        //Get Place after updating address - use Get Http method
        String getPlaceResponse = given().log().all().queryParam("key", "qaclick123")
        .queryParam("place_id", placeID)
        .when().get("maps/api/place/get/json")
        .then().log().all().statusCode(200).extract().response().asString();
        
        JsonPath js1=ReusableMethods.rawToJson(getPlaceResponse);
        String actualAddress = js1.getString("address");
        System.out.println("Actaul Address is "+actualAddress);
        Assert.assertEquals(actualAddress, newAddress);
   }
   
   @Test
   public void readJsonFromFile() throws IOException
   {
   	//RestAssured uses hamcrest matchers to assert and validate response easily.
   	//Add Place 
       RestAssured.baseURI= "https://rahulshettyacademy.com";
       String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
       .body(new String(Files.readAllBytes(Paths.get("//pass file path here"))))
       .when().post("maps/api/place/add/json")
       .then().assertThat().statusCode(200).body("scope", equalTo("APP"))
       .header("Server", "Apache/2.4.52 (Ubuntu)").extract().response().asString();
       System.out.println(response);
   }
   
   //create post request payloads
   
   //@Test
   public void createPayloadUsingHashMap() {
	   HashMap map = new HashMap();
	   map.put("place_id", "12345");
	   map.put("address", "test hash map");
	   
	   given()
	   .contentType("application/json")
	   .body(map)
	   .when().post("https://rahulshettyacademy.com/maps/api/place/add/json")
	   .then().statusCode(201)
	   .body("place_id", equalTo("12345"))
	   .body("address", equalTo("test hash map"))
	   .header("Content-Type", "application/json")
	   .log().all();
	   
   }
   
  // @Test
   public void delete()
   {
	   given()
	   .when().delete("https://rahulshettyacademy.com/maps/api/place/delete/json")
	   .then().statusCode(200);
   }
}
   