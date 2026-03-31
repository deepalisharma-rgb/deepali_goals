package api.endpoints;

import static io.restassured.RestAssured.given;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

//This class is created to maintain CURD operations for Google Place APIs

public class PlaceEndpoints {

	public static Response addPlace(String addPlacePayload) {
		Response response = given().contentType(ContentType.JSON).accept(ContentType.JSON)
				.queryParam("key", "qaclick123")
				.body(addPlacePayload)
				.when().post(Routes.add_place_url);
		return response;
	}

	public static Response getPlace(String placeId) {
		Response response = given().pathParam("place_id", placeId)
				.when().get(Routes.get_place_url);
		return response;
	}

	public static Response updatePlace(String updatePlacePayload) {
		Response response = given().contentType(ContentType.JSON).accept(ContentType.JSON)
				.body(updatePlacePayload)
				.when().put(Routes.update_place_url);
		return response;
	}

	public static Response deletePlace(String deletePlacePayload) {
		Response response = given().contentType(ContentType.JSON).accept(ContentType.JSON)
				.body(deletePlacePayload)
//		.pathParam("place_id", placeId)
				.when().delete(Routes.delete_place_url);
		return response;
	}
}
