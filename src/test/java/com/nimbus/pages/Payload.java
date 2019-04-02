package com.nimbus.pages;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.logging.Handler;

import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.log4j.Logger;
//import org.json.JSONObject;
import org.openqa.selenium.WebDriver;

import com.google.gson.JsonObject;

import utility.ConfigReader;
/*
 * Class Name : Payload

 * Description:	Using Payload for API
 */
public class Payload {
	WebDriver driver;
	Logger Log;
	ConfigReader config = new ConfigReader();

	public Payload(WebDriver ldriver) {
		this.driver = ldriver;
	}
	

	public Payload(Logger lLog) {
		this.Log = lLog;
	}

	public int payloadCase() throws Exception {

		int statusCode = casecreationfromPayload();
		return statusCode;
	}

	private int casecreationfromPayload() throws Exception {
		Log = Logger.getLogger("Payload.class");
		try {

			File file = new File(".//Json//jsonRequest.txt");
			FileInputStream fis = new FileInputStream(file);
			byte[] data = new byte[(int) file.length()];
			fis.read(data);
			fis.close();

			String str = new String(data, "UTF-8");
			
			str.replace("memberIdValue", new Date().getTime()+"");

			StringEntity entity = new StringEntity(str, ContentType.APPLICATION_JSON);

			HttpClient httpClient = HttpClientBuilder.create().build();
			
			String URI = null;
			
			if(GlobalValues.Environment.equalsIgnoreCase("SIT")){
				URI = "https://va33tlvnim301.wellpoint.com:8443/cmproduct/anthem/gbd/p/create_case/_new";
			}else if(GlobalValues.Environment.equalsIgnoreCase("DEV")){
				URI = "https://va33dlvnim301.wellpoint.com:8443/cmproduct/anthem/gbd/p/create_case/_new";
			}else if(GlobalValues.Environment.equalsIgnoreCase("SANDBOX")){
				URI = "https://va33dlvnim301.wellpoint.com:9443/cmproduct/anthem/gbd/p/create_case/_new";
			}else if(GlobalValues.Environment.equalsIgnoreCase("UAT")){
				URI = "https://va33tlvnim303.wellpoint.com:8443/cmproduct/anthem/gbd/p/create_case/_new";
			}
			Log.info("URI =" + URI );
			HttpPost request = new HttpPost(URI);
			request.setEntity(entity);
			
			HttpResponse response = httpClient.execute(request);
			int statusCode = response.getStatusLine().getStatusCode();
			ResponseHandler<String> handler = new BasicResponseHandler();
			String body = handler.handleResponse(response);
		   
			//JSONObject jsonObject = new JSONObject(body);
			System.out.println("The API Response Status Code is " + response.getStatusLine().getStatusCode());
			
			return statusCode;
			
		} catch (Exception e) {
			 e.printStackTrace();
			 
		}return 0;
	
	}

}