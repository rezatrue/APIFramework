package resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {

	public static RequestSpecification reqs;
	public RequestSpecification requestSpecification() throws IOException {
		if(reqs==null) {
			PrintStream  log = new PrintStream(new FileOutputStream("logging.txt"));
			reqs = new RequestSpecBuilder().setBaseUri(this.getGlobalValue("baseUrl")).addQueryParam("key", "qaclick123")
					.addFilter(RequestLoggingFilter.logRequestTo(log))
					.addFilter(ResponseLoggingFilter.logResponseTo(log))
					.setContentType(ContentType.JSON).build();
			return reqs;
		}
		return reqs;
	}
	
	private String getGlobalValue(String key) throws IOException {
		Properties porp = new Properties();
		FileInputStream fis = new FileInputStream(new File("src/test/java/resources/global.properties"));
		porp.load(fis);
		return porp.getProperty(key);
	}
	
	public String getJsonPath(Response response, String key) {
		String output = response.asString();
		JsonPath jsPath = new JsonPath(output);
		return jsPath.get(key).toString();
	}
}
