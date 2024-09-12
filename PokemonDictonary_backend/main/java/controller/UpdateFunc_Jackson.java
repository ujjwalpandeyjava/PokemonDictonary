package controller;

import java.io.File;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.Properties;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import enums.APIsGettingEndpoints;

public class UpdateFunc_Jackson {

	// This method is called inside the UpdateNewJSONData class.
	// @SuppressWarnings("unchecked")
	public static String fetchNewData() {
		long startTime = System.currentTimeMillis();
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

			// Code to iterate ENUMS for all 18 types of data
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

//				System.out.println(response.body());
				try {
					/*
					  // 1. create a map to add data 
					  Map<String, Object> jsonDataToPutInFiles = new HashMap<>(); 
					  jsonDataToPutInFiles.put("name", "John Deo");
					  jsonDataToPutInFiles.put("roles", new String[]{"Member", "Admin"});
					  jsonDataToPutInFiles.put("admin", true);
					 */

					// 2. create objects to add data
					ObjectMapper mapper = new ObjectMapper();
					ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());

					/*ObjectNode rootNode = mapper.createObjectNode();

					// Adding ObjectJSON object
					ObjectNode childNode1 = mapper.createObjectNode();
					childNode1.put("name1", "val1");
					childNode1.put("name2", "val2");
					rootNode.set("obj1", childNode1);

					// Adding ArrayJSON object
					ArrayNode childArrayNode1 = mapper.createArrayNode();
					ObjectNode arrayObjNode1 = mapper.createObjectNode();
					arrayObjNode1.put("name7", "val7");
					arrayObjNode1.put("name8", "val8");
					arrayObjNode1.put("name9", "val9");
					childArrayNode1.add(arrayObjNode1);
					ObjectNode arrayObjNode2 = mapper.createObjectNode();
					arrayObjNode2.put("name7", "val7");
					arrayObjNode2.put("name8", "val8");
					arrayObjNode2.put("name9", "val9");
					childArrayNode1.add(arrayObjNode2);
					rootNode.set("obj5", childArrayNode1);

					// JsonNode is abstractObjectJSON and arrayNode.
					JsonNode childAbstractNode1 = mapper.readTree(response.body().toString());
					rootNode.set("Full response body", childAbstractNode1);
					if (childAbstractNode1 instanceof ObjectNode)
						System.out.println("This is JSON Object");
					else if (childAbstractNode1 instanceof ArrayNode)
						System.out.println("This is JSON Array type");
					*/
				
					JsonNode responseNode = mapper.readTree(response.body().toString());
					JsonNode rootNode = null ;
					if (responseNode instanceof ObjectNode) {
						System.out.println("This is JSON Object");
						ObjectNode rootNodeTemp = mapper.createObjectNode();	//	Created {}
						Iterator<String> keySets = responseNode.fieldNames();
						while(keySets.hasNext()) {
							String currentKey = keySets.next();
//						}
//						keySets.forEachRemaining(currentKey -> {
							JsonNode tempJsonNode = responseNode.get(currentKey);
							if(tempJsonNode instanceof ObjectNode) {
//								System.out.println("\nObject Type with Key: - " +currentKey);
								rootNodeTemp.set(currentKey, tempJsonNode);	//	For JSON Object
//								rootNodeTemp.put("key 1", "val 1");	//For primitive data types
							}else if(tempJsonNode instanceof ArrayNode) {
//								System.out.println("\nArray Type with Key: - " +currentKey);
								rootNodeTemp.set(currentKey, tempJsonNode);
							}else if(tempJsonNode instanceof JsonNode) {
//								System.out.println("\nOther Type with Key: - " +currentKey);
								rootNodeTemp.set(currentKey, tempJsonNode);
							}
						}
//						);
						/* How to add in Object
						 ObjectNode childObjNode1 = mapper.createObjectNode();
						childObjNode1.put("key 1", "val 1");
						rootNodeTemp.set("Full response body", childObjNode1);
						ObjectNode childObjNode2 = mapper.createObjectNode();
						childObjNode2.put("key 2", "val 2");
						rootNodeTemp.set("Full response body 2", childObjNode2);*/
						rootNode = rootNodeTemp;
					}
					else if (responseNode instanceof ArrayNode) {
						System.out.println("This is JSON Array type");
						ArrayNode rootNodeTemp = mapper.createArrayNode();	//	Created []
						responseNode.forEach(s->{
							if(s instanceof ObjectNode) 
								rootNodeTemp.add(s);
							else if(s instanceof ArrayNode) 
								rootNodeTemp.add(s);
						});
						//	How to add in array
//						ObjectNode childObjNode1 = mapper.createObjectNode();
//						rootNodeTemp.add(childObjNode1);
//						ObjectNode childObjNode2 = mapper.createObjectNode();
//						rootNodeTemp.add(childObjNode2);
						rootNode = rootNodeTemp;
					}
					String jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(rootNode);
//				    mapper.writeValue(Paths.get(dirJson + "/" + fileData.name().toString() + ".json").toFile(), mapper.readTree(jsonString));
					writer.writeValue(Paths.get(dirJson + "/" + fileData.name().toString() + ".json").toFile(),
							mapper.readTree(jsonString));
					writer.writeValue(Paths.get(dirHistory + "/" + fileData.name().toString() + ".json").toFile(),
							mapper.readTree(jsonString));

				} catch (Exception ex) {
					ex.printStackTrace();
				}

				System.out.println("Thread on sleep for 2 seconds.");
				Thread.sleep(2000);
				System.out.println("Thread execution resumed\n||---------------------------------------------------||");
			}
		} catch (Exception e) {
			e.fillInStackTrace();
			e.printStackTrace();
			returnString = "Data not updated -- There is a problem in the update function code.";
		}
		long endTime = System.currentTimeMillis();
		System.out.println((endTime-startTime)/1000 +" Seconds");
		return returnString;
	}
}