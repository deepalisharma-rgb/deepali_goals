package api.utilities;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

public abstract class GenericMethods {

	public static int randomNumber() {
		//return new Random().nextInt(1000000000);
		Random random = new Random();
		return random.nextInt(1000000000);
	}

	public static String getProjectRootDirectory() {
		String currentDirectory = System.getProperty("user.dir");
		return currentDirectory;
	}

	public static String getCurrentLocalDate() {
//		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//		Date date = new Date();
//		return formatter.format(date).toString();
		return LocalDate.now().toString();
	}
	
	public static String getCurrentDate() {
		   SimpleDateFormat formatter = new SimpleDateFormat("MMM d, yyyy");  
		    Date date = new Date();  
		    return formatter.format(date); 
	}
	
	
}
