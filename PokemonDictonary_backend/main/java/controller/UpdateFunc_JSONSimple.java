package controller;

import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import enums.APIsGettingEndpoints;

public class UpdateFunc_JSONSimple {
	// This method is called inside the UpdateNewJSONData class.
	// Write logic here to update JSON data
	@SuppressWarnings("unchecked")
	public static String fetchNewData() {
		String returnString = "";
		try {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm_ss");
			String newDataDate = dtf.format(LocalDateTime.now());
			returnString = "Data Updated successfully. New Data saved in history directory of file named: "
					+ newDataDate;

			// Creating files for data
			String currentPath = new File("").getCanonicalPath();
			String dirJson = currentPath + "\\src\\main\\java\\jsonData"; // add new JSON file to use
			String dirHistory = currentPath + "\\src\\main\\java\\jsonData\\history\\" + newDataDate;
			// to create new directory for history
			File f = new File(dirHistory); // new directory for history
			boolean bool = f.mkdir();
			System.out.println(
					(bool) ? "New file directory created" : "Error in creating new directory for history JSON data");

			// Code to iterate enum for all 18 types of data
			APIsGettingEndpoints[] values = APIsGettingEndpoints.values();
			for (APIsGettingEndpoints fileData : values) {
				System.out.println(fileData.name() + " - - " + fileData.getAction());
				Properties props = new Properties();
//				FileReader reader = new FileReader("configs.properties");
//				props.load(reader);
				InputStream input = ClassLoader.getSystemClassLoader().getResourceAsStream("configs.properties");
				props.load(input);
				String APIUrl = fileData.getAction().toString();
				System.out.println(APIUrl);
				String defaultAppKey = props.getProperty("x_rapidapi_key__default_application_5408530").toString();
				System.out.println(defaultAppKey);
				String rapidapi_host = props.getProperty("x_rapidapi_host").toString();
				System.out.println(rapidapi_host);
				// Code to add JSON data in folder and history folder
				HttpRequest request = HttpRequest.newBuilder().uri(URI.create(APIUrl))
						.header("x-rapidapi-host", rapidapi_host).header("x-rapidapi-key", defaultAppKey)
						.method("GET", HttpRequest.BodyPublishers.noBody()).build();
				HttpResponse<String> response = HttpClient.newHttpClient().send(request,
						HttpResponse.BodyHandlers.ofString());
				// Creating JSON doc using a Java program
				final JSONObject jsonObject = new JSONObject();
// Insert the required key-value pairs using the put() method of the JSONObject class
				// Iterator this object on is values and insert them in jsonObject which will be
				// used to put data in files.
//				System.out.println(response.body());
				JSONParser jsonparserObj = new JSONParser();
				if (jsonparserObj.parse(response.body()) instanceof JSONObject) {
					final JSONObject jsonData = (JSONObject) jsonparserObj.parse(response.body());
					jsonData.keySet().forEach(s -> {
						System.out.println("\nKey is: - " + s);
						if (jsonData.get(s) instanceof JSONArray) {
							System.out.println("\nKey is: - " + s + ", data is JSONArray------- " + s.toString()
									+ "Not handling now.");
//						Object currentObj = jsonData.get(s);
							// Handle the data and put each object in an array
							// JSONArray newJSONArrayObj = new JSONArray();
//						jsonObject.put(s, newJSONArrayObj);
						} else if (jsonData.get(s) instanceof JSONObject) {
							System.out.println("\nKey is: - " + s + ", data is JSONObject------- " + s.toString());
							Object currentObj = jsonData.get(s);
							jsonObject.put(s, currentObj);
						} else
							System.out.println("This is neither JSON object nor JSON Array");
					});
				} else if (jsonparserObj.parse(response.body()) instanceof JSONArray) {
					final JSONArray jsonData = (JSONArray) jsonparserObj.parse(response.body());
					System.out.println(
							"We have got full response in JSON Array format, Will handel later. \nThe data is: "
									+ jsonData.toString());
				}

				FileWriter file = new FileWriter(dirJson + "/" + fileData.name().toString() + ".json");
				FileWriter file2 = new FileWriter(dirHistory + "/" + fileData.name().toString() + ".json");
				file.write(jsonObject.toJSONString());
				file.close();
				file2.write(jsonObject.toJSONString());
				file2.close();
//			System.out.println("JSON file created: " + jsonObject);
				System.out.println("Thread on sleep for 8 seconds.");
				Thread.sleep(8000);
				System.out.println("Thread execution resumed");
				System.out.println("||---------------------------------------------------||");
			}

		} catch (Exception e) {
			e.fillInStackTrace();
			e.printStackTrace();
			returnString = "Data not updated -- There is a problem in the update function code.";
		}
		return returnString;
	}
}
