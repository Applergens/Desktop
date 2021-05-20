package util;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;

public class RequestUtils {
	
	public static String api = "https://apilergens.herokuapp.com";
//	public static String api = "http://localhost:5000";
	
	public static String httpPostRequest(String endPoint, String jsonData) throws IOException, InterruptedException {
		
		URI uri = URI.create(api + endPoint);
		
		HttpClient client = HttpClient.newHttpClient();
		
		HttpRequest request = HttpRequest.newBuilder(uri)
				.header("Accept", "application/json")
				.header("Content-Type", "application/json")
				.POST(BodyPublishers.ofString(jsonData))
				.build();
		
		HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
		
		if (response.statusCode() == 200) {
			
			return response.body();
			
		} else {
			
			return "Invalid";
			
		}
		
	}
	
	public static String getAllRequest(String endpoint) throws IOException, InterruptedException {
		
		URI uri = URI.create(api + endpoint + "/getAll");
		
		HttpClient client = HttpClient.newHttpClient();
		
		HttpRequest request = HttpRequest.newBuilder(uri)
				.build();
		
		HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
		
		if (response.statusCode() == 200) {
			
			return response.body();
			
		} else {
			
			return "Invalid";
			
		}
		
	}
	
	
}
