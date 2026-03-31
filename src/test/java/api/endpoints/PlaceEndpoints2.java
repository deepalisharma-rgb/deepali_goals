package api.endpoints;

import static io.restassured.RestAssured.given;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.ResourceBundle;

import api.payload.Place;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

//This class is created to maintain CURD operations for Google Place APIs

public class PlaceEndpoints2 {

	//method created for getting URL's from properties file
	public static ResourceBundle getURL(){
//		final String CONFIG_WEB_FILE_PATH = api.utilities.GenericMethods.getProjectRootDirectory()
//				+ "/routes.properties";
		//Resource Bundle class used to load properties file and read data from properties file
		ResourceBundle routes = ResourceBundle.getBundle("routes"); //in get bundle need to pass name of the property file
//		System.out.println(ResourceBundle.getBundle("Test"+"routes"));
//		try {
//			Properties prop = new Properties();
//			FileInputStream fis = new FileInputStream("src/test/resources/routes.properties");
//			prop.load(fis);	
//		}
//		catch(Exception e) {
//			System.out.println(e);
//		}

		return routes;
		
	}
	
	public static Response addPlace(String addPlacePayload) {
		String add_url = getURL().getString("add_place_url");
		Response response = given().contentType(ContentType.JSON).accept(ContentType.JSON)
				.queryParam("key", "qaclick123")
				.body(addPlacePayload)
				.when().post(add_url);
		return response;
	}

	public static Response getPlace(String placeId) {
		String get_url = getURL().getString("get_place_url");
		Response response = given().pathParam("place_id", placeId)
				.when().get(get_url);
		return response;
	}

	public static Response updatePlace(String updatePlacePayload)  {
		String update_url = getURL().getString("update_place_url");
		Response response = given().contentType(ContentType.JSON).accept(ContentType.JSON)
				.body(updatePlacePayload)
				.when().put(update_url);
		return response;
	}

	public static Response deletePlace(String deletePlacePayload) {
		String delete_url = getURL().getString("delete_place_url");
		Response response = given().contentType(ContentType.JSON).accept(ContentType.JSON)
				.body(deletePlacePayload)
//		.pathParam("place_id", placeId)
				.when().delete(delete_url);
		return response;
	}
}
