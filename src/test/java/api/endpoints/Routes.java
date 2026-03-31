package api.endpoints;

//This class used to capture all the URL's which we can capture form Swagger/Postman

public class Routes {

	public static String base_url = "https://rahulshettyacademy.com/maps/api/place";
	
	//Place API
	
	public static String get_place_url = base_url+"/get/json?{place_id}&key=qaclick123";
	public static String add_place_url = base_url+"/add/json";
	public static String update_place_url = base_url+"/update/json?place_id=bc6bda065b98b67f1ce62489a0b93c1d&key=qaclick123";
	public static String delete_place_url = base_url+"/delete/json?key=qaclick123";
}
