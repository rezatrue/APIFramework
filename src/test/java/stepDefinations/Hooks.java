package stepDefinations;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
	@Before("@DeletePlace")
	public void befireSenario() throws IOException {
		StepDefination sd = new StepDefination();
		if(sd.place_id==null) {
			sd.add_place_payload("Book Store", "(+1) 983 893 3300", "5 North lane");
			sd.user_call_with_http_request("AddPlaceAPI", "post");
			sd.verify_place_id_created_maps_to_using("Book Store","GetPlaceAPI");
		}
	}
}
