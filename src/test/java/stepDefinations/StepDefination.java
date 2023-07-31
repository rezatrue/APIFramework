package stepDefinations;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.jupiter.api.Assertions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

public class StepDefination extends Utils{
	RequestSpecification res;
	ResponseSpecification resspec;
	Response response;
	TestDataBuild data = new TestDataBuild();
	static String place_id;
	@Given("Add place Payload {string} {string} {string}")
	public void add_place_payload(String name, String phone, String address) throws IOException {
	    // Write code here that turns the phrase above into concrete actions
	   System.out.println("Add place Payload");
	   res = RestAssured.given().spec(requestSpecification()).body(data.addPlacePayload(name, phone, address));
	}
	@When("user call {string} with {string} http request")
	public void user_call_with_http_request(String resourse, String method) {
	    // Write code here that turns the phrase above into concrete actions
		System.out.println("user call "+ resourse +" with Post http request");
		
		APIResources resAPI = APIResources.valueOf(resourse);
		System.out.println(resAPI.getResource() + " - " + method);
		if(method.equalsIgnoreCase("POST")) {
			response = res.when().post(resAPI.getResource());
		}
		if(method.equalsIgnoreCase("GET")) {
			response = res.when().get(resAPI.getResource());
		}
		
	}
	@Then("the API call got success with status code {int}")
	public void the_api_call_got_success_with_status_code(Integer arg01) {
	    // Write code here that turns the phrase above into concrete actions
		System.out.println("the API call got success with status code "+arg01);
		resspec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		response = response.then().spec(resspec).extract().response();
		
		Assertions.assertEquals((Integer)response.getStatusCode(), (Integer)arg01);
		
	}
	@Then("{string} in responce body is {string}")
	public void in_responce_body_is(String arg01, String arg02) {
	    // Write code here that turns the phrase above into concrete actions
		System.out.println(arg01 +" in responce body is " +arg02);
		Assertions.assertEquals(getJsonPath(response, arg01), arg02);
	}
	
	@Then("verify place_id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String expectedName, String apiResourse) throws IOException {
	    // Write code here that turns the phrase above into concrete actions
		place_id = getJsonPath(response, "place_id");
		res = RestAssured.given().spec(requestSpecification()).queryParam("place_id", place_id);
		user_call_with_http_request(apiResourse, "GET");
		String actualName = getJsonPath(response, "name");
		Assertions.assertEquals(actualName, expectedName);
	}
	
	@Given("Delete Place Payload")
	public void delete_place_payload() throws IOException {
	    // Write code here that turns the phrase above into concrete actions
		   res = RestAssured.given().spec(requestSpecification()).body(data.deleatePlacePayload(place_id));
	}

}
