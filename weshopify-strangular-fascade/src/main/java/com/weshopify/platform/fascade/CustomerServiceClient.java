package com.weshopify.platform.fascade;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;


public class CustomerServiceClient {

	public static String getAllCustomers() throws IOException, InterruptedException {
		HttpRequest request = HttpRequest.newBuilder()
		        .uri(URI.create("http://localhost:5001/customers"))
		        //.timeout(Duration.ofMinutes(2))
		        .header("Content-Type", "application/json")
		        .build();
		
		HttpResponse<String> response = HttpClient.newBuilder()
				  .build()
				  .send(request, BodyHandlers.ofString());
		System.out.println("response is:\t"+response.body());
		
		
		return response.body();
	}
}
